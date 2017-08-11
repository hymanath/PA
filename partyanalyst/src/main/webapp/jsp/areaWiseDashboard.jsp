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
		 	<div class="col-md-12 col-xs-12 col-sm-12">
				<h2><span id="selectedMenuName" class="text-capitalize">Kavali Constituency</span> Information</h2>
				<ol class="breadcrumb" id="getMenuLocations" menu-location-state="1" menu-location-districts="19" menu-location-constituencys="232" menu-location-mandals="" menu-location-panchayats="" menu-location-levelId="3" menu-location-levelName="constituency" >
					<!--<li>State : <span menu-name="state" class="stateMenuName">Andhra Pradesh</span></li>-->
					<li>District : <span menu-name="districts" levelId="2" locationId="01" class="districtsMenuName">Nellore</span></li>
					<li style="display:none">Constituency :<span menu-name="constituencys" levelId="3" locationId="01" class="constituencysMenuName"> Andhra Pradesh</span></li>
					<li style="display:none">Mandal :<span menu-name="mandals" levelId="4" locationId="01" class="mandalsMenuName"> Andhra Pradesh</span></li>
					<li style="display:none">Panchayat :<span menu-name="panchayats" levelId="5" locationId="01" class="panchayatsMenuName"> Andhra Pradesh</span></li>
				</ol>
				<div class="menu-dropdown" style="display:none;">
					<div class="row">
						<div class="col-sm-3">
							<div id="districtsMenu"></div>
						</div>
						<div class="col-sm-3">
							<div id="constituencysMenu"></div>
						</div>
						<div class="col-sm-3">
							<div id="mandalsMenu"></div>
						</div>
						<div class="col-sm-3">
							<div id="panchayatsMenu"></div>
						</div>
						<div class="col-sm-12 text-right m_top20">
							<button class="btn btn-success" id="getLocationDetails">GET DETAILS</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row m_top20">
		 	<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div id="assemblyMemberId">
								<div class="media media-profile">
									<div class="media-left">
										<img src="coreApi/img/profile.jpg" class="media-object profile-image img-border" alt="profile"/>
										<span class="border-image img-border">
											<img src="coreApi/img/BJP.png" alt="party"/>
										</span>
									</div>
									<div class="media-body">
										<h4 class="m_top20 text-success text-capital"> - </h4>
										<p class="text-muted">MLA<span class="text-capitalize"> [Member of legislative assembly]</span></p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div id="parliamentMemberId">
								<div class="media media-profile">
									<div class="media-left">
										<img src="coreApi/img/profile.jpg" class="media-object profile-image img-border" alt="profile"/>
										<span class="border-image img-border">
											<img src="coreApi/img/BJP.png" alt="party"/>
										</span>
									</div>
									<div class="media-body">
										<h4 class="m_top20 text-success text-capital"> - </h4>
										<p class="text-muted">MP<span class="text-capitalize"> [Member of Parliament]</span></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		 	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div id="levelWiseCountDivId"></div>
			</div>
			<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="election">
				 <h3 class="text-capitalize">Election Information Assembly Constituency</h3>
				 <h6 class="text-capitalize text-muted">All Parties Performance in different elections</h6>
				 <div class="block">
					 <div class="row">
						 <div class="col-md-12 col-xs-12 col-sm-12">
							 <label class="text-capital m_left5">
							 	<input type="checkbox"/>ALL
							 </label>
							 <label class="text-capital m_left5">
							 	<input type="checkbox"/>assembly
							 </label>
							 <label class="text-capital m_left5">
							 	<input type="checkbox"/>parliament
							 </label>
							 
							 <div id="test"></div>
						 </div>
					 </div>
				 </div>
			</div>-->
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="constituencyVoters">
				 <h3 class="text-capitalize">constituency voters information</h3>
			</div>
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20 text-right">
				<label>Select Publication</label>
				<select>
					<option>2014-04-19</option>
				</select>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div id="constituencyVoterInfo"></div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="casteInfo">
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
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="cadreInfor">
				<h3>Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="cadreInfor"></i></span></h3>
				<div class="block">
					<div id="cadreInfoGraphDivId"></div>
					<div id="cadreInfoGraphBar" style="height:200px;"></div>
					<div id="cadreInfoTableView"></div>
				</div>
				
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="news">
				<h3>News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="news"></i></span></h3>		
				<div class="block">
					<div id="newsMainBlockDivId"></div>
					<div id="leadersMainBlockDivId"></div>
				</div>
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="problems">
				<h3>Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="problems"></i></span></h3>						
				<div class="block pad_5">
					<div id="overAllAnalysisProbDivId"></div>
					
				</div>
			</div>
			
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="committees">
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<h3>Committees <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="committees"></i></span></h3>						
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6 text-right">
						<select>
							<option>2015-2016</option>
							<option>2015-2016</option>
						</select>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="committees"></div>
						<!--<div class="block">
							<div class="row">
								<div class="col-md-6 col-xs-12 col-sm-6">
									<h4 class="panel-title text-capital">main committee formation</h4>
									<div class="row m_top15">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="panel-title text-capital">mandal/town/division level</h4>
											<div id="mandalLevelGraph"></div>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="panel-title text-capital">vilalge/ward level</h4>
											<div id="villageLevelGraph"></div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<h4 class="panel-title text-capital">affliated committee formation</h4>
									<div class="row m_top15">
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="panel-title text-capital">mandal/town/division level</h4>
											<div id="affMandalLevelGraph"></div>
										</div>
										<div class="col-md-6 col-xs-12 col-sm-6">
											<h4 class="panel-title text-capital">vilalge/ward level</h4>
											<div id="affVillageLevelGraph"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="block pad_0">
							<table class="table table-bordered">
								<thead>
									<tr class="text-capital">
										<th rowspan="2"></th>
										<th colspan="3">main committee</th>
										<th colspan="3"> affliated committee</th>
									</tr>
									<tr class="text-capitalize bg-E9">
										<th>total</th>
										<th>started</th>
										<th>completed</th>
										<th>total</th>
										<th>started</th>
										<th>completed</th>
									</tr>
								</thead>
								<tbody class="text-capitalize">
									<tr>
										<td>mandal/town/division</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
									</tr>
									<tr>
										<td>village/ward</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
										<td>50</td>
									</tr>
								</tbody>
							</table>
							<div class="pad_10">
								<button class="btn btn-success btn-success-white text-capital">detailed information</button>
							</div>
						</div>-->
						
					</div>
				</div>
			</div>
			<div class="col-md-8 col-xs-12 col-sm-8 m_top20" navbar-index="meetings">
				<h3>Meetings <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="meetings"></i></span></h3>
				<p class="f-12 text-muted">Evertymonth: 9th/10th/11th</p>
				<div class="block pad_0">
					<div id="locationWiseMeetingsCount"></div>
				</div>
			</div>
			
			<div class="col-md-4 col-xs-12 col-sm-4 m_top20" navbar-index="tours">
				<h3>Tours <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="tours"></i></span></h3>
				<p class="f-12 text-muted">Last Month Jan 2017</p>
				<div class="block pad_0">
					<div id="locationWiseTourMembersComplainceDtls"></div>
				</div>
			</div>
			
			
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="benefits">
				<h3>Benefits</h3>
				<div class="block pad_0">
					<div class="row">
						<div id="benefitsBlockId"></div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="activities">
				<h3 >Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="activities"></i></span></h3>
				<div class="block pad_0" id ="activitesId">
				</div>
			</div>
			<!--<div class="col-md-12 col-xs-12 col-ms-12 m_top20" navbar-index="alerts">
				<h3>Alerts <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="block">
								<ul class="list-inline list-border">
									<li>
										<h4>100</h4>
										<p>Total Alerts</p>
									</li>
									<li>
										<h4>100</h4>
										<p>Total Alerts</p>
									</li>
									<li>
										<h4>100</h4>
										<p>Total Alerts</p>
									</li>
								</ul>
							</div>
							<div class="row m_top15">
								<div class="col-md-5 col-xs-12 col-sm-5">
									<div id="overallAlerts" style="height:200px"></div>
								</div>
								<div class="col-md-7 col-xs-12 col-sm-7">
									
								  <select class="form-control" role="tabListMobile">
									  <option tab_id="alerts1">OC</option>
									  <option tab_id="alerts2">BC</option>
									  <option tab_id="alerts3">SC</option>
								  </select>
								  <ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">
									<li role="presentation" class="active"><a href="#alerts1" aria-controls="alerts1" role="tab" data-toggle="tab">OC</a></li>
									<li role="presentation"><a href="#alerts2" aria-controls="alerts1" role="tab" data-toggle="tab">BC</a></li>
									<li role="presentation"><a href="#alerts3" aria-controls="alerts1" role="tab" data-toggle="tab">SC</a></li>
								  </ul>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6 pad_left0">
							<div>
							  
							  <div class="tab-content">
								<div role="tabpanel" class="tab-pane active pad_10" id="alerts1">
									<table class="table table-noborder">
										<thead class="text-capitalize bg-E9">
											<th>alert status</th>
											<th>total</th>
										</thead>
									</table>
								</div>
								<div role="tabpanel" class="tab-pane pad_10" id="alerts2">
									
								</div>
								<div role="tabpanel" class="tab-pane pad_10" id="alerts3">
									  
								</div>
							  </div>

							</div>
						</div>
					</div>
				</div>
			</div>-->
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="nominatedPosts">
				<h3>Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14" refresh="nominatedPosts"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h5 class="m_left10">Posts</h5>
							<div id="nominatedPostStatusWiseCount"></div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h5 class="m_left10">Applications</h5>
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div id="nominatedPostApplicationDetails"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<hr class="m_0"/>
							<h4 class="text-capital text-center m_top-10"><span class="bg-fff pad_10">position level</span></h4>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div id="positionsWiseMemberCount"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20"  navbar-index="grievance">
				<h3>Grievance & Insurance <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4">
							<div id="grivanceId"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Insurance</h4>
							<div id="grievanceDetails1"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<div id="trustId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="coreApi/js/constituencyPage.js" type="text/javascript"></script>
</body>
</html>