<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Constituency Page</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="coreApi/css/responsive.css" rel="stylesheet" type="text/css">
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
<link href="dist/slick/slick.css" type="text/less" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
<div class="background-head"></div>
<header>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<img src="coreApi/img/logo.png" class="img-responsive"/>
			</div>
			<div class="col-sm-6">
				<img src="coreApi/img/right_side_head.png" class="img-responsive pull-right"/>
			</div>
		</div>
	</div>
</header>
<div class="black-border">
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<ol class="breadcrumb m_top10" id="getMenuLocations" menu-location-state="1" menu-location-district="" menu-location-constituency="" menu-location-mandal="" menu-location-panchayat="" menu-location-levelId="2" menu-location-parliament="" menu-location-levelName="state" menu-location-name="">
					<li><i class="fa fa-align-left fa-1x " id="menuHeaderId"></i> <span menu-name="state" levelId="2" locationId="1" class="stateMenuName">Andhra Pradesh</span></li>
					<li style="display:none"><span menu-name="district" levelId="3" locationId="01" class="districtMenuName text-capitalize"></span></li>
					<li style="display:none"> <span menu-name="parliament" levelId="10" locationId="01" class="parliamentMenuName text-capitalize"></span></li>
					<li style="display:none"><span menu-name="constituency" levelId="4" locationId="01" class="constituencyMenuName text-capitalize"></span></li>
					<li style="display:none"><span menu-name="mandal" levelId="5" locationId="01" class="mandalsMenuName text-capitalize"></span></li>
					<li style="display:none"><span menu-name="panchayat" levelId="6" locationId="01" class="panchayatMenuName text-capitalize"></span></li>
				</ol>
				<div class="menu-dropdown" style="display:none;">
					<i class="glyphicon glyphicon-remove menu-close"></i>
					<div class="row">
						<div class="col-sm-3">
							<div id="districtMenu"></div>
							<div id="parliamentMenu" class="m_top20"></div>
						</div>
						<div class="col-sm-3">
							<div id="constituencyMenu"></div>
						</div>
						<div class="col-sm-3">
							<div id="mandalMenu"></div>
						</div>
						<div class="col-sm-3">
							<div id="panchayatMenu"></div>
						</div>
						<div class="col-sm-12 text-right" style="border-top:1px solid #ddd;padding-top:10px;margin-top:10px;">
							<span class="text-capital pull-left" style="cursor:pointer;margin-top:10px" id="resetMenuOptions">reset options</span>
							<button class="btn btn-success" id="getLocationDetails">GET DETAILS</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
			<h5 style="text-align: right" class="m_top10">
				<a href="newlogoutAction.action" style="color:#fff"><i class="fa fa-sign-out floatLeft"></i><span>&nbsp;&nbsp;Sign-out</span></a>
			</h5>
			</div>
		</div>
	</div>
</div>
<section>
	<div class="right-nav">
		<ul class="right-nav-list">
			<li index="election" class="active">election info</li>
			<li index="constituencyVoters">constituency voters</li>
			<li index="casteInfo">caste information</li>
			<li index="cadreInfor">cadre information</li>
			<!--<li index="ruralLocal">rural local body elections</li>-->
			<!--<li index="urbanLocal">urban local body elections</li>-->
			<!--<li index="news">news</li>
			<li index="problems">problems</li>-->
			<li index="committees">committees</li>
			<li index="meetings">meetings</li>
			<li index="tours">tours</li>
			<li index="benefits">benefits</li>
			<!--<li index="activities">activities</li>-->
			<li index="alerts">alerts</li>
			<li index="nominatedPosts">nominated posts</li>
			<li index="grievance">grievance</li>
			<li index="insurance">insurance</li>
			<li index="ntrTrust">ntrTrust</li>
		</ul>
	</div>
</section>
<section>
	<div class="container">
		<!--<h3 class="text-left text-capital"><span id="selectedMenuName" class="text-capital">Andhra Pradesh</span> Information</h3>-->
		<div class="row m_top15">
		 	<div id="parliamentMemberId"></div>
			<div id="assemblyMemberId"></div>
			<div id="representativeMembersId"></div>
			<div id="statelevelMembersId"></div>
			<div id="statelevelMPMLAId"></div>
		 	<div class="col-sm-12 m_top20">
				<div id="levelWiseCountDivId"></div>
				<div id="statelevelWiseCountDivId"></div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="election">
				<h4 class="panel-title theme-title-color">Election Information Assembly Constituency
					<span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="election"></i></span>
				</h4>
				<h6 class="text-capitalize text-muted">All Parties Performance in different elections</h6>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<div id="electionTypeValuesId"></div>
							<div class="electionDetailsGraphHeight">
								<div id="electionDetailsGraphWiseId"></div>
							</div>
						</div>
					</div>
					<div class="row m_top10">
						 <div class="col-sm-12">
							<div id="electionDetailsTableWiseId" class=""></div>
						 </div>
					</div>
				
				<div class="m_top20 assemblyElectionBlockCls" style="display:none">
					<div class="row m_top20">
						<!--<div class="col-sm-6">
							<div id="assemblyElectionGraphDetails"></div>
						</div>
						<div class="col-sm-6">
							<div id="assemblyElectionDetails"></div>
						</div>-->
						<div class="col-sm-12">
							<div id="assemblyElectionDetails"></div>
						</div>
					</div>
				</div>
					<div class="row m_top10">
							<div class="col-sm-12">
								<button class="btn btn-success btn-success-white text-capital" detailed-block="election">detailed information</button>
							</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="constituencyVoters">
				<h4 class="panel-title theme-title-color">voters information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="constituencyVoters"></i></span></h4>
				<div class="block m_top10">
					<div class="row">
						 <div class="col-sm-3">
							<label>Publication</label>
							<div id="publicationsDivId"></div>
						</div>
						<div class="col-sm-3">
							<label>Enrollment Year</label>
							<select id="enrollmentvoterId" class="form-control chosen-select"></select>
						</div>
					</div>
					<div class="row m_top20">
						<div class="col-sm-12">
							<div id="constituencyVoterInfo"></div>
						</div>
					</div>
					
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="casteInfo">
				<h4 class="panel-title theme-title-color">Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="casteInfo"></i></span></h4>
				<div class="block m_top10">
					<div class="row">
						<!-- <div class="col-sm-3">
							<label>Publication</label>
							<select id="publicationCasteId" class="form-control chosen-select"></select>
						</div>-->
						<div class="col-sm-3">
							<label>Enrollment Year</label>
							<select id="enrollmentCasteId" class="form-control chosen-select"></select>
						</div>
					</div>
					<div class="row m_top20">
						<div id="casteOverViewDivId"></div>
					</div>
					
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="cadreInfor">
				<h4 class="panel-title theme-title-color">Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="cadreInfor"></i></span></h4>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<div id="cadreInfoGraphDivId" class="m_top15"></div>
							<div id="cadreInfoGraphBar" style="height:200px;"></div>
							<div id="cadreInfoTableView"></div>
						</div>
					</div>
				</div>
				
			</div>
			
			<!--<div class="col-sm-6 m_top30" navbar-index="news">
				<h4 class="panel-title theme-title-color">News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="news"></i></span></h4>
				<div class="block m_top10">
					<div id="newsMainBlockDivId" class="m_top15"></div>
					<div id="leadersMainBlockDivId" class="m_top15"></div>
				</div>
			</div>
			
			<div class="col-sm-6 m_top30" navbar-index="problems">
				<h4 class="panel-title theme-title-color">Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="problems"></i></span></h4>
				<div class="block m_top10">
					<div id="overAllAnalysisProbDivId" class="m_top15"></div>
				</div>
			</div>-->
			
			<div class="col-sm-12 m_top30" navbar-index="committees">
				<div class="row">
					<div class="col-sm-10">
						<h4 class="panel-title theme-title-color m_top30">Committees <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="committees"></i></span></h4>
					</div>
					<div class="col-sm-2">
						<label>Enrollment Year</label>
						<select id="enrolmentYears" class="form-control chosen-select">
							<option value="2">2016-2018</option>
							<option value="1">2014-2016</option>
						</select>
					</div>
				</div>
				<div class="block m_top10">
					<div class="row m_top15">
						<div class="col-sm-6">
							<p class="text-capitalize"><b>main committee formation</b></p>
							<div class="row m_top15">
								<div class="col-sm-6">
									<p class="panel-title text-capitalize">mandal / town / division level</p>
									<div id="mandalLevelGraph" style="height:250px;"></div>
								</div>
								<div class="col-sm-6">
									<p class="panel-title text-capitalize">village / ward level</p>
									<div id="villageLevelGraph" style="height:250px;"></div>
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="border-left:1px solid #ddd">
							<p class="text-capitalize"><b>affliated committee formation</b></p>
							<div class="row m_top15">
								<div class="col-sm-6">
									<p class="panel-title text-capitalize">mandal / town / division level</p>
									<div id="affMandalLevelGraph" style="height:250px;"></div>
								</div>
								<div class="col-sm-6">
									<p class="panel-title text-capitalize">village / ward level</p>
									<div id="affVillageLevelGraph" style="height:250px;"></div>
								</div>
							</div>
						</div>
					</div>
					<div id="committesTableDivId" class="m_top10"></div>
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="meetings">
				<!--<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Meetings</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="meetings"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForMeetings" expand-block-date="meetings" style="width:200px;margin-right:12px;">
							<input type="text" id="dateRangeIdForMeetings" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div id="locationWiseMeetingsCount" class="m_top10"></div>
				</div>-->
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Meetings</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="meetings"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForMeetings" expand-block-date="meetings" style="width:200px;margin-right:12px;">
							<input type="text" id="dateRangeIdForMeetings" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div id="areaWiseMeetingsId" class="block m_top10" style="display:none;">
				</div>
				<div class="block m_top10">
					<div class="row">
						<div id="stateMeetingsBlockId" class="m_top20"></div>
						<div class="col-sm-8" id="committeeMeetingsBLock">
							<h3 class="theme-title-color">Committee Meetings</h3>
							<div id="committeeMeetingsBlockId" class="m_top10"></div>
						</div>
					</div>
					<div class="row m_top20">
						<div id="specialMeetingsBlockId" class="m_top10"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="tours">
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Tours</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="tours"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForMeetings" expand-block-date="tours" style="width:200px;margin-right:12px;">
							<input type="text" id="tourNewDateRangePickerId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div id="locationWiseTourMembersComplainceDtls" class="m_top15"></div>
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="benefits">
				<h4 class="panel-title theme-title-color">Benefits<span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="benefits"></i></span><span>(Amount in crores) </span></h4>
				<div class="block m_top10">
					<div class="row">
						<div id="benefitsBlockId"></div>
					</div>
					<div class="row m_top10 detailedBenefitBlockCls">
						<div class="col-sm-12">
							<button class="btn btn-success btn-success-white text-capital" detailed-block="benefits">detailed information</button>
						</div>
					</div>	
				</div>
			</div>
			<!--<div class="col-sm-12 m_top30" navbar-index="activities">
				<h4 class="panel-title theme-title-color">Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="activities"></i></span></h4>
				<div class="block m_top10">
					<div id ="activitesId"></div>
				</div>
			</div>-->
			<div class="col-sm-12 m_top30" navbar-index="alerts">
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Alerts</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 7px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="alerts"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForMeetings" expand-block-date="alerts" style="width:200px;margin-right:12px;">
							<input type="text" id="alertNewDateRangePickerId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<ul class="list-inline switch-btn alertCategoryWiseCls pull-right m_top5">
								<li attr_type="1,2">Both</li>
								<li attr_type="1" class="active">Party</li>
								<li attr_type="2">Govt</li>
								<!--<li attr_type= "3">Others</li>-->
							</ul>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div id="alertsBlockDivId" class="m_top20"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div id="alertsDeignBlockDivId" class="m_top20"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30" navbar-index="nominatedPosts">
				<h4 class="panel-title theme-title-color">Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="nominatedPosts"></i></span></h4>
				<div class="block m_top10">
					<div class="row m_top15">
						<div class="col-sm-6" style="border-right:2px dashed #777">
							<h5 class="m_left10">Posts Status</h5>
							<div id="nominatedPostStatusWiseCount"></div>
						</div>
						<div class="col-sm-6">
							<h5 class="m_left10">Applications Status</h5>
							<div class="row">
								<div class="col-sm-12">
									<div id="nominatedPostApplicationDetails"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top10">
							<div id="levelWiseNominatedPosts"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top20">
							<hr class="m_0"/>
							<h4 class="text-capital text-center m_top-10"><span class="bg-fff pad_10">G.O ISSUED POSITIONS LEVEL WISE</span></h4>
						</div>
						<div class="col-sm-12 m_top10">
							<div id="positionsWiseMemberCount"></div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<button class="btn btn-success btn-success-white text-capital" detailed-block="nominatedPosts">detailed information</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30"  navbar-index="grievance">
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Grievance</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="grievance"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForGrievance" expand-block-date="tours" style="width:200px;margin-right:12px;">
							<input type="text" id="grievanceNewDateRangePickerId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<div id="grievanceMainBlockId"></div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<button class="btn btn-success btn-success-white text-capital" detailed-block="grievance">detailed information</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30"  navbar-index="insurance">
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Insurance</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="insurance"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForInsurance" expand-block-date="tours" style="width:200px;margin-right:12px;">
							<input type="text" id="insuranceNewDateRangePickerId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<div id="insuranceMainBlockId"></div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<button class="btn btn-success btn-success-white text-capital" detailed-block="insurance">detailed information</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top30"  navbar-index="ntrTrust">
				<div class="row">
					<div class="col-sm-6">
						<h4 class="panel-title theme-title-color m_top10">Trust Education</h4>
					</div>
					<div class="col-sm-6">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="ntrTrust"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForNtrTrust" expand-block-date="tours" style="width:200px;margin-right:12px;">
							<input type="text" id="ntrTrustNewDateRangePickerId" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="block m_top10">
					<div class="row">
						<div class="col-sm-12">
							<div id="ntrTrustMainBlockId"></div>
						</div>
					</div>
					<div class="row m_top10">
						<div class="col-sm-12">
							<button class="btn btn-success btn-success-white text-capital" detailed-block="ntrTrust">detailed information</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<div class="modal fade" id="casteCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header">
				<div class="row">
					<div class="col-md-8">
						<h4 class="modal-title text-capitalize" id="headingTitle">TITLE</h4>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="casteCategoryModalDivId"></div>
					</div>  
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!----- Tours Modal Start ---->
<div class="modal" tabindex="-1" role="dialog" id="tourDetailsModalId" style="top: 45px;">
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
<!----- Tours Modal End ---->
<div class="modal fade" id="openModalDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:95%">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#CCCCCC">
				<div class="row" >
					<div class="col-md-8">
						<h4 class="theme-title-color text-capital" id="TitleId" >Title</h4>
						<p id="subTitleId"></p>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="openPostDetailsModalDivId"></div>
					</div>  
				</div>
				<div class="row paginationCls">
					<div class="col-md-12 m_top20">
						<div class="paginationId"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="departmentPostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#CCCCCC">
				<div class="row" >
					<div class="col-md-8">
						<h3 class="modal-title text-capitalize" id="deptHeadingId" >Title</h3>
					</div>
					<div class="col-sm-3 pull-right">
						<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
				</div>
			</div>
			<div class="modal-body">        
				<div class="row">
					<div class="col-sm-12">
						<div id="departmentDetailsModalDivId"></div>
					</div>  
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Model for Debate Start-->           
		<div class="modal" tabindex="-1" role="dialog" id="cdrModelDivId" style="z-index:9999;">
		  <div class="modal-dialog modal-lg">       
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#999999">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!--end-->
<div class="modal fade" id="myModalShowNew" style="z-index:9999;">
	<div class="modal-dialog modal-lg" role="document" style="width:90%">
		<div class="modal-content">
			<div id="myModalShowNewId"></div>
		</div>
	</div>  
</div>
<div class="modal fade" tabindex="-1" id="meetingMemDetailsId" role="dialog" style="z-index:9999;">
 <div class="modal-dialog" role="document" style="width:85%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="">Member Details</h4>      
		</div>
		<div class="modal-body" id="">   
          <div id="meetingMemDetailsBodyId"></div>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="coreApi/js/bootstrap-multiselect.js"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="coreApi/js/constituencyPage.js" type="text/javascript"></script>
<script src="coreApi/js/tourConstituencyPage.js" type="text/javascript"></script>
<script src="coreApi/js/alertPopupDetails.js" type="text/javascript"></script>
</body>
</html>