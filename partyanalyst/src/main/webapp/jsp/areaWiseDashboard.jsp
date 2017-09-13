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
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>

<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>
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
				<h2><span id="selectedMenuName" class="text-capitalize">Nellore District</span> Information</h2>
				<ol class="breadcrumb" id="getMenuLocations" menu-location-state="1" menu-location-district="19" menu-location-constituency="" menu-location-mandal="" menu-location-panchayat="" menu-location-levelId="3" menu-location-parliament="" menu-location-levelName="district" >
					<!--<li>State : <span menu-name="state" class="stateMenuName">Andhra Pradesh</span></li>-->
					<li>District : <span menu-name="district" levelId="3" locationId="01" class="districtMenuName text-capital">Nellore</span></li>
					<li style="display:none">Parliament : <span menu-name="parliament" levelId="10" locationId="01" class="parliamentMenuName text-capital">Nellore</span></li>
					<li style="display:none">Constituency :<span menu-name="constituency" levelId="4" locationId="01" class="constituencyMenuName text-capital"> Andhra Pradesh</span></li>
					<li style="display:none">Mandal :<span menu-name="mandal" levelId="5" locationId="01" class="mandalsMenuName text-capital"> Andhra Pradesh</span></li>
					<li style="display:none">Panchayat :<span menu-name="panchayat" levelId="6" locationId="01" class="panchayatMenuName text-capital"> Andhra Pradesh</span></li>
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
							<button class="btn btn-success" id="getLocationDetails">GET DETAILS</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row m_top20">
		 	<div id="parliamentMemberId"></div>
			<div id="assemblyMemberId"></div>
		 	<div class="col-sm-12 m_top20">
				<div id="levelWiseCountDivId"></div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="election">
				 <h3 class="text-capitalize">Election Information Assembly Constituency</h3>
				 <h6 class="text-capitalize text-muted">All Parties Performance in different elections</h6>
				 <div class="block">
					 <div class="row">
						<div id="electionTypeValuesId"></div>
						<div id="electionDetailsGraphWiseId"></div>
						<div id="electionDetailsTableWiseId"></div>
					 </div>
				 </div>
				<div class="block">
					<div class="row">
						<div class="col-sm-12">
							<h4 class="text-capitalize">Assembly Election Details</h4>
						</div>
						<div class="col-sm-6">
							<div id="assemblyElectionGraphDetails"></div>
						</div>
						<div class="col-sm-6">
							<div id="assemblyElectionDetails"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 m_top20" navbar-index="constituencyVoters">
				 <h3 class="text-capitalize">constituency voters information</h3>
			</div>
			<div class="col-sm-6 m_top20">
				<div class="row">
					<div class="col-sm-4 pull-right"><div id="publicationsDivId"></div></div>
				</div>
				
			</div>
			<div class="col-sm-12  m_top5">
				<div id="constituencyVoterInfo"></div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="casteInfo">
				<h3>Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="casteInfo"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4" style="border-right:1px solid #d3d3d3">
							<h4 class="panel-title"><b>Caste Group Wise Voters</b>
								<ul class="switch-btn" role="casteGrouplist">
									<li class="active" attr_type="voter">Voter</li>
									<li attr_type="cadre">Cadre</li>
								</ul>
							</h4>
							<div class="m_top20" id="casteGroupVoters" style="width:330px;height:260px"></div>
							<div id="leftSideCasteGroupWiseDivId"></div>
							<div id="leftSideCasteGroupWiseGraphId"></div>
						</div>
						<div class="col-sm-8">
							<div id="rightSideCasreGroupDtsDivId"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="cadreInfor">
				<h3>Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="cadreInfor"></i></span></h3>
				<div class="block">
					<div id="cadreInfoGraphDivId"></div>
					<div id="cadreInfoGraphBar" style="height:200px;"></div>
					<div id="cadreInfoTableView"></div>
				</div>
				
			</div>
			
			<div class="col-sm-6 m_top20" navbar-index="news">
				<h3>News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="news"></i></span></h3>		
				<div class="block">
					<div id="newsMainBlockDivId"></div>
					<div id="leadersMainBlockDivId"></div>
				</div>
			</div>
			
			<div class="col-sm-6 m_top20" navbar-index="problems">
				<h3>Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="problems"></i></span></h3>						
				<div class="block pad_5">
					<div id="overAllAnalysisProbDivId"></div>
					
				</div>
			</div>
			
			<div class="col-sm-12 m_top20" navbar-index="committees">
				<div class="row">
					<div class="col-sm-10">
						<h3>Committees <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="committees"></i></span></h3>						
					</div>
					<div class="col-sm-2">
						<select id="enrolmentYears">
							<option>2015-2016</option>
							<option>2015-2016</option>
						</select>
					</div>
					<div class="col-sm-12">
						<div class="block">
						
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital"><b>main committee formation</b></h4>
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
									<h4 class="panel-title text-capital"><b>affliated committee formation</b></h4>
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
						</div>
						<div class="block pad_0">
							<div id="committesTableDivId"></div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-sm-8 m_top20" navbar-index="meetings">
				<h3>Meetings <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="meetings"></i></span></h3>
				<p class="f-12 text-muted">Evertymonth: 9th/10th/11th</p>
				<div class="block pad_0">
					<div id="locationWiseMeetingsCount"></div>
				</div>
			</div>
			
			<div class="col-sm-4 m_top20" navbar-index="tours">
				<h3>Tours <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="tours"></i></span></h3>
				<p class="f-12 text-muted">Last Month Jan 2017</p>
				<div class="block pad_0">
					<div id="locationWiseTourMembersComplainceDtls"></div>
				</div>
			</div>
			
			
			<div class="col-sm-12 m_top20" navbar-index="benefits">
				<h3>Benefits<span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="benefits"></i></span></h3>
				<div class="block pad_0">
					<div class="row">
						<div id="benefitsBlockId"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="activities">
				<h3 >Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="activities"></i></span></h3>
				<div class="block pad_0" id ="activitesId">
				</div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="alerts">
				<h3>Alerts <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="alerts"></i></span></h3>
				<div id="alertsBlockDivId"></div>
			</div>
			<div class="col-sm-12 m_top20" navbar-index="nominatedPosts">
				<h3>Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="nominatedPosts"></i></span></h3>
				<div class="block">
					<div class="row">
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
				<div class="row">
					<div class="col-sm-10">
						<h3>Grievance & Insurance <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="grievance"></i></span></h3>
					</div>
					<div class="col-sm-2">
						<select id="enrolmentYearsGrievance">
							<option value="3">2014-2016</option>
							<option value="4">2016-2018</option>
						</select>
					</div>
				</div>
				<div class="block">
					<div class="row">
						<div class="col-sm-4">
							<h4 class="panel-title text-capital text-center">Grivance</h4>
							<div id="grivanceId0" style="height:300px;"></div>
						</div>
						<div class="col-sm-4">
							<h4 class="panel-title text-capital text-center">Insurance</h4>
							<div id="insuranceDetails" style="height:300px;"></div>
						</div>
						<div class="col-sm-4">
						<h4 class="panel-title text-capital text-center">NTR TRUST</h4>
							<div id="grivanceId1" style="height:300px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="coreApi/js/constituencyPage.js" type="text/javascript"></script>
</body>
</html>