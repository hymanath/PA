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
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
<!--<div class="background-head"></div>
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
</header>-->
<section>
	<div class="right-nav">
		<ul class="right-nav-list">
			<li index="election" class="active">election info</li>
			<li index="constituencyVoters">constituency voters</li>
			<li index="casteInfo">caste information</li>
			<li index="cadreInfor">cadre information</li>
			<li index="ruralLocal">rural local body elections</li>
			<li index="urbanLocal">urban local body elections</li>
			<li index="news">news</li>
			<li index="problems">problems</li>
			<li index="committees">committees</li>
			<li index="meetings">meetings</li>
			<li index="tours">tours</li>
			<li index="benefits">benefits</li>
			<li index="activities">activities</li>
			<li index="alerts">alerts</li>
			<li index="nominatedPosts">nominated posts</li>
			<li index="grievance">grievance & insurance</li>
		</ul>
	</div>
</section>
<section>
	<div class="container">
		<div class="row">
		 	<div class="col-sm-12">
				<h3 class="text-center text-capital"><span id="selectedMenuName" class="text-capital">Nellore District</span> Information</h3>
				<ol class="breadcrumb m_top10" id="getMenuLocations" menu-location-state="1" menu-location-district="" menu-location-constituency="" menu-location-mandal="" menu-location-panchayat="" menu-location-levelId="2" menu-location-parliament="" menu-location-levelName="state" >
					<li><i menu-name="state" class="fa fa-align-justify fa-1x"></i> <span menu-name="state" class="stateMenuName">Andhra Pradesh</span></li>
					<li style="display:none"><span menu-name="district" levelId="3" locationId="01" class="districtMenuName text-capital">Nellore</span></li>
					<li style="display:none"> <span menu-name="parliament" levelId="10" locationId="01" class="parliamentMenuName text-capital">Nellore(PC)</span></li>
					<li style="display:none"><span menu-name="constituency" levelId="4" locationId="01" class="constituencyMenuName text-capital"> Andhra Pradesh</span></li>
					<li style="display:none"><span menu-name="mandal" levelId="5" locationId="01" class="mandalsMenuName text-capital"> Andhra Pradesh</span></li>
					<li style="display:none"><span menu-name="panchayat" levelId="6" locationId="01" class="panchayatMenuName text-capital"> Andhra Pradesh</span></li>
				</ol>
				<div class="menu-dropdown" style="display:none;">
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
		</div>
		<div class="row">
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
				 
				 <div class="block">
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
				 </div>
				<div class="block m_top20 assemblyElectionBlockCls" style="display:none">
					<div class="row">
						<div class="col-sm-12">
							<h4 class="panel-title theme-title-color">Assembly Election Details</h4>
						</div>
					</div>
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
			</div>
			
			
			<div class="col-sm-12 m_top20">
				<div class="block">
					<div class="row">
						<div class="col-sm-12  m_top5" navbar-index="constituencyVoters">
							 <h4 class="panel-title theme-title-color">voters information</h4>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-3 pull-right">
								<label>Publication</label>
								<div id="publicationsDivId"></div>
							</div>
							<div class="col-sm-3 pull-right">
								<label>Enrollment Year</label>
								<select id="enrollmentvoterId" class="form-control chosen-select"></select>
							</div>
						</div>
					</div>
					<div class="row m_top20">
						<div class="col-sm-12">
							<div id="constituencyVoterInfo"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="casteInfo">
				
				<div class="block">
					<div class="row">
						<div class="col-sm-12">
							<h4 class="panel-title theme-title-color">Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="casteInfo"></i></span></h4>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-3 pull-right">
								<label>Publication</label>
								<select id="publicationCasteId" class="form-control chosen-select"></select>
							</div>
							<div class="col-sm-3 pull-right">
								<label>Enrollment Year</label>
								<select id="enrollmentCasteId" class="form-control chosen-select"></select>
							</div>
						</div>
					</div>
					<div class="row m_top20">
						<div id="casteOverViewDivId"></div>
					</div>
					
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="cadreInfor">
				<div class="block">
					<h4 class="panel-title theme-title-color"">Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="cadreInfor"></i></span></h4>
					<div class="row m_top10">
						<div class="col-sm-12">
							<div id="cadreInfoGraphDivId" class="m_top15"></div>
							<div id="cadreInfoGraphBar" style="height:200px;"></div>
							<div id="cadreInfoTableView"></div>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="col-sm-6 m_top20" navbar-index="news">
				
				<div class="block">
					<h4 class="panel-title theme-title-color">News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="news"></i></span></h4>
					<div id="newsMainBlockDivId" class="m_top15"></div>
					<div id="leadersMainBlockDivId" class="m_top15"></div>
				</div>
			</div>
			
			<div class="col-sm-6 m_top20" navbar-index="problems">
				<div class="block">
					<h4 class="panel-title theme-title-color">Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="problems"></i></span></h4>
					<div id="overAllAnalysisProbDivId" class="m_top15"></div>
				</div>
			</div>
			
			<div class="col-sm-12 m_top20" navbar-index="committees">
				<div class="block">
					<div class="row">
						<div class="col-sm-10">
							<h4 class="panel-title theme-title-color m_top10">Committees <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="committees"></i></span></h4>
						</div>
						<div class="col-sm-2">
							<label>Enrollment Year</label>
							<select id="enrolmentYears" class="form-control chosen-select">
								<option value="2">2016-2018</option>
								<option value="1">2014-2016</option>
							</select>
						</div>
					</div>
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
			<div class="col-sm-12 m_top20" navbar-index="meetings">
				<div class="block">
					<h4 class="panel-title theme-title-color">Meetings
					<span class="">
						<span class="pull-right" style="margin-top: 10px;">
							<i class="glyphicon glyphicon-refresh f-14" refresh="meetings"></i>
						</span>
						<div class="input-group pull-right dateRangePickerClsForMeetings" expand-block-date="meetings" style="width:200px;margin-right:12px;">
							<input type="text" id="dateRangeIdForMeetings" style="width:190px" class="form-control"/>
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</span></h4>
					<!--<p class="f-12 text-muted">Evertymonth: 9th/10th/11th</p>-->
					<div id="locationWiseMeetingsCount" class="m_top10"></div>
				</div>
			</div>
			
			<div class="col-sm-12 m_top20" navbar-index="tours">
				<div class="block">
					<h4 class="panel-title theme-title-color">Tours <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="tours"></i></span><small class="f-12" style="color:#777 !important;">Last Month Jan 2017</small></h4>
					<div id="locationWiseTourMembersComplainceDtls" class="m_top15"></div>
				</div>
			</div>
			
			
			<div class="col-sm-12 m_top20" navbar-index="benefits">
				<div class="block">
					<h4 class="panel-title theme-title-color">Benefits<span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="benefits"></i></span></h4>
					<div class="row">
						<div id="benefitsBlockId"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="activities">
				<div class="block">
					<h4 class="panel-title theme-title-color">Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="activities"></i></span></h4>
					<div id ="activitesId"></div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="alerts">
				<div class="block">
					<h4 class="panel-title theme-title-color">Alerts <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="alerts"></i></span></h4>
					<div class="row">
							<div class="col-sm-12">
								<ul class="list-inline switch-btn alertCategoryWiseCls pull-right m_top5">
									<li attr_type= "1,2" class="active">Both</li>
									<li attr_type= "1">Party</li>
									<li attr_type= "2">Govt</li>
									<!--<li attr_type= "3">Others</li>-->
								</ul>
							</div>
					</div>
					<div id="alertsBlockDivId" class="m_top20"></div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="nominatedPosts">
				<div class="block">
					<h4 class="panel-title theme-title-color">Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="nominatedPosts"></i></span></h4>
					<div class="row m_top15">
						<div class="col-sm-6">
							<h5 class="m_left10">Posts</h5>
							<div id="nominatedPostStatusWiseCount"></div>
						</div>
						<div class="col-sm-6">
							<h5 class="m_left10">Applications</h5>
							<div class="row">
								<div class="col-sm-12">
									<div id="nominatedPostApplicationDetails"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top20">
							<hr class="m_0"/>
							<h4 class="text-capital text-center m_top-10"><span class="bg-fff pad_10">position level</span></h4>
						</div>
						<div class="col-sm-12 m_top10">
							<div id="positionsWiseMemberCount"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top20"  navbar-index="grievance">
				<div class="block">
					<div class="row">
						<div class="col-sm-10">
							<h4 class="panel-title theme-title-color">Grievance Details <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="grievance"></i></span></h4>
						</div>
						<!--<div class="col-sm-2">
							<select id="enrolmentYearsGrievance">
								<option value="3">2014-2016</option>
								<option value="4">2016-2018</option>
							</select>
						</div>-->
					</div>
					<div class="row m_top15">
						<div class="col-sm-4">
							<h4 class="panel-title text-capital text-center">Grievance</h4>
							<p class="m_top10 text-capital text-center" id="totalGrievanceCount"></p>
							<div id="grivanceId0" style="height:300px;"></div>
						</div>
						<div class="col-sm-4">
							<h4 class="panel-title text-capital text-center">Insurance</h4>
							<p class="m_top10 text-capital text-center" id="insuranceTotalCount"></h4>
							<div id="insuranceDetails" style="height:300px;"></div>
						</div>
						<div class="col-sm-4">
							<h4 class="panel-title text-capital text-center">NTR TRUST</h4>
							<p class="m_top10 text-capital text-center" id="NtrTrustTotalCount"></h4>
							<div id="grivanceId1" style="height:300px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<div class="modal fade" id="casteCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999;">
	<div class="modal-dialog" role="document" style="width:70%;margin:auto">
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
		 
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="casteCategoryModalDivId"></div>
					</div>  
		  
				</div>
			</div>
		</div>
	</div>
</div>
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
<script src="coreApi/js/constituencyPage.js" type="text/javascript"></script>
</body>
</html>