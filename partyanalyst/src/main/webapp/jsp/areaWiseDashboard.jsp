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
<link href="coreApi/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="dist/slick/slick.css" type="text/less" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
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
				<h3>Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
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
							<!--<div id="leftSideCasteGroupWiseGraphId"></div>-->
						</div>
						
						<!--<div class="col-md-8 col-xs-12 col-sm-8">
							<div>
							  
							  <select class="form-control" role="tabListMobile">
								  <option tab_id="OC">OC</option>
								  <option tab_id="BC">BC</option>
								  <option tab_id="SC">SC</option>
								  <option tab_id="ST">ST</option>
							  </select>
							  <ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#OC" aria-controls="OC" role="tab" data-toggle="tab">OC</a></li>
								<li role="presentation"><a href="#BC" aria-controls="BC" role="tab" data-toggle="tab">BC</a></li>
								<li role="presentation"><a href="#SC" aria-controls="SC" role="tab" data-toggle="tab">SC</a></li>
								<li role="presentation"><a href="#ST" aria-controls="ST" role="tab" data-toggle="tab">ST</a></li>
							  </ul>
							  
							  <div class="tab-content">
								<div role="tabpanel" class="tab-pane active pad_10" id="OC">
									<h4 class="panel-title text-capitalize">Caste Wise - Voter & Cadre Information b/w age group</h4>
									<table class="table table-noborder">
										<thead class="text-capitalize">
											<th>Caste Name</th>
											<th>voters <span class="text-success">%</span></th>
											<th>cadres <span class="text-success">%</span></th>
											<th>Male(V) <span class="text-success">%</span></th>
											<th>Male(C) <span class="text-success">%</span></th>
											<th>FeMale(V) <span class="text-success">%</span></th>
											<th>FeMale(C) <span class="text-success">%</span></th>
										</thead>
										<tbody>
											<tr>
												<td><i class="glyphicon glyphicon-plus td-expand-icon" collapseid="td-expand-one"></i> Brahmin</td>
												<td>2 <span class="text-success">2%</span></td>
												<td>2 <span class="text-success">2%</span></td>
												<td>2 <span class="text-success">2%</span></td>
												<td>2 <span class="text-success">2%</span></td>
												<td>2 <span class="text-success">2%</span></td>
												<td>2 <span class="text-success">2%</span></td>
											</tr>
											<tr class="td-expand-body" collapseBodyId="td-expand-one">
												<td colspan="7" class="top-arrow">
													<i class="glyphicon glyphicon-remove td-expand-hide"></i>
													<h4 class="panel-title"><span class="text-capital">brahmin caste</span> - <span class="text-capitalize">voter and cadre information b/w age group</span></h4>
													<p class="text-muted text-right">
														<span class="f-11"><i class="glyphicon glyphicon-info-sign"></i> _(C) = Cadres ; _(V) = Voter</span>
													</p>
													<table class="table table-noborder m_top10">
														<thead class="text-capitalize">
															<th>Caste Name</th>
															<th>voters <span class="text-success">%</span></th>
															<th>cadres <span class="text-success">%</span></th>
															<th>Male(V) <span class="text-success">%</span></th>
															<th>Male(C) <span class="text-success">%</span></th>
															<th>FeMale(V) <span class="text-success">%</span></th>
															<th>FeMale(C) <span class="text-success">%</span></th>
														</thead>
														<tbody>
															<tr>
																<td>Brahmin</td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
															</tr>
															<tr>
																<td>Brahmin</td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
																<td>2 <span class="text-success">2%</span></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div role="tabpanel" class="tab-pane pad_10" id="BC">
									
								</div>
								<div role="tabpanel" class="tab-pane pad_10" id="SC">
									  
								</div>
								<div role="tabpanel" class="tab-pane pad_10" id="ST">
									  
								</div>
							  </div>

							</div>
							
						</div>-->
						
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="cadreInfor">
				<h3>Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div id="cadreInfoGraphDivId"></div>
					<div id="cadreInfoGraphBar"></div>
					<!--<table class="table table-noborder table-noborder-hover m_top10">
						<thead class="bg-E9">
							<th></th>
							<th>Total</th>
							<th><span class="text-success">%</span></th>
							<th>Male</th>
							<th><span class="text-success">%</span></th>
							<th>Female</th>
							<th><span class="text-success">%</span></th>
							<th>OC</th>
							<th><span class="text-success">%</span></th>
							<th>BC</th>
							<th><span class="text-success">%</span></th>
							<th>SC</th>
							<th><span class="text-success">%</span></th>
						</thead>
						<tbody>
							<tr>
								<td> &lt; 20 years</td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
							</tr>
							<tr>
								<td> &lt; 20 years</td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
							</tr>
							<tr>
								<td> &lt; 20 years</td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
								<td>2000</td>
								<td><span class="text-success">2%</span></td>
							</tr>
						</tbody>
					</table>-->
				</div>
				
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="news">
				<h3>News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>		
				<div class="block">
					<div id="newsMainBlockDivId"></div>
					<!--<div class="panel-group m_top10" id="newsCollapse" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
						<div class="panel-heading bg-fff" role="tab" id="headingOne">
							<a role="button" data-toggle="collapse" class="collapseIcon" data-parent="#newsCollapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
							  	<h4 class="panel-title">leaders in news</h4>
							</a>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						  	<div class="panel-body pad_0">
								<table class="table table-noborder f-12">
									<thead class="bg-E9 text-capitalize">
										<th>leader name</th>
										<th>designation</th>
										<th>positive</th>
										<th>negative</th>
									</thead>
									<tbody>
										<tr>
											<td>
												<img src="coreApi/img/profile.jpg" class="img-responsive img-circle" style="height: 30px;width: 30px;display: inline-block"/> praven tej
											</td>
											<td>secretary</td>
											<td>50%</td>
											<td>40%</td>
										</tr>
									</tbody>
								</table>
						  	</div>
						</div>
					  </div>
					</div>
					<div class="pad_10">
						<button class="btn btn-success btn-success-white">detailed news information</button>
					</div>-->
				</div>
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="problems">
				<h3>Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>						
				<div class="block pad_5">
					<div id="overAllAnalysisProbDivId"></div>
					
				</div>
			</div>
			
			<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="committees">
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<h3>Committees <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>						
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6 text-right">
						<select>
							<option>2015-2016</option>
							<option>2015-2016</option>
						</select>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div class="block">
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
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-md-8 col-xs-12 col-sm-8 m_top20" navbar-index="meetings">
				<h3>Meetings <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<p class="f-12 text-muted">Evertymonth: 9th/10th/11th</p>
				<div class="block pad_0">
					<table class="table table-bordered">
						<thead class="text-capitalize">
							<th></th>
							<th class="bg-E9">Village / Ward</th>
							<th class="bg-E9">mandal/town/division</th>
							<th class="bg-E9">constituency</th>
						</thead>
						<tbody>
							<tr>
								<td>total</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
						</tbody>
					</table>
					<div class="pad_10">
						<button class="btn btn-success btn-success-white text-capital">detailed information</button>
					</div>
				</div>
			</div>
			
			<div class="col-md-4 col-xs-12 col-sm-4 m_top20" navbar-index="tours">
				<h3>Tours <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<p class="f-12 text-muted">Last Month Jan 2017</p>
				<div class="block pad_0">
					<table class="table table-hover">
						<thead class="text-capitalize bg-E9">
							<th>Designation</th>
							<th>status</th>
						</thead>
						<tbody>
							<tr>
								<td>MP LOKSABHA</td>
								<td><span class="text-success">Compliance</span></td>
							</tr>
							<tr>
								<td>MP LOKSABHA</td>
								<td><span class="text-danger">Non-Compliance</span></td>
							</tr>
						</tbody>
					</table>
					<div class="block pad_10">
						<button class="btn btn-success btn-success-white text-capital">detailed information</button>
					</div>
				</div>
			</div>
			
			
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="benefits">
				<h3>Benefits</h3>
				<div class="block pad_0">
					<div class="row">
					
					  <div class="col-md-3 col-xs-12 col-sm-3 pad_right0">
						  <select class="form-control" role="tabListMobile">
							  <option tab_id="benefits1">OC (20)</option>
							  <option tab_id="benefits2">OC (20)</option>
							  <option tab_id="benefits3">OC (20)</option>
							  <option tab_id="benefits4">OC (20)</option>
						  </select>
						  <ul class="nav nav-tabs nav-tabs-horizontal" role="tablist">
							<li class="active"><a href="#benefits1" aria-controls="OC" role="tab" data-toggle="tab">CM Relief Fund <span class="pull-right">20</span></a></li>
							<li><a href="#benefits2" aria-controls="benefits2" role="tab" data-toggle="tab">Brahmin Corporation</a></li>
							<li><a href="#benefits3" aria-controls="benefits3" role="tab" data-toggle="tab">Muslim Minorities</a></li>
							<li><a href="#benefits4" aria-controls="benefits4" role="tab" data-toggle="tab">BC Corp Beneficiery</a></li>
						  </ul>
					  </div>
					 
					  <div class="col-md-9 col-xs-12 col-sm-9 pad_left0">
						  <div class="tab-content">
							<div role="tabpanel" class="tab-pane active pad_10" id="benefits1">
								<div class="row">
									<div class="col-md-6 col-xs-12 col-sm-6">
										<div id="benefitsGraph" style="height:200px"></div>
									</div>
									<div class="col-md-6 col-xs-12 col-sm-6">
										<table class="table table-noborder">
											<thead class="text-capitalize bg-E9">
												<th></th>
												<th>Total</th>
												<th>%</th>
											</thead>
											<tbody class="text-capitalize">
												<tr>
													<td><span class="chart-legend-color"></span>Population</td>
													<td>5000</td>
													<td>40%</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
										<table class="table table-noborder">
											<thead class="text-capitalize bg-E9">
												<th>mandal name</th>
												<th>population</th>
												<th>eligible</th>
												<th>benefited</th>
												<th>non-eligible benefited</th>
											</thead>
											<tbody class="text-capitalize">
												<tr>
													<td>allur mandal</td>
													<td>5000</td>
													<td>2000 <span class="text-success">40%</span></td>
													<td>2000 <span class="text-success">40%</span></td>
													<td>2000 <span class="text-success">40%</span></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
									<button class="btn btn-success btn-success-white text-capital">detailed caste information</button>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane pad_10" id="benefits2">
								a
							</div>
							<div role="tabpanel" class="tab-pane pad_10" id="benefits3">
								b
							</div>
							<div role="tabpanel" class="tab-pane pad_10" id="benefits4">
								c
							</div>
						  </div>
					  </div>

					</div>
				</div>
			</div>-->
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="activities">
				<h3 >Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
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
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="nominatedPosts">
				<h3>Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h5 class="m_left10">Posts</h5>
							<div class="row">
								<div class="col-md-6 col-xs-12 col-sm-6">
									<div id="posts" style="height:80px;"></div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<ul class="graph-legend">
                                    	<li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                    </ul>
								</div>
							</div>
							
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<h5 class="m_left10">Applications</h5>
							<div class="row">
								<div class="col-md-6 col-xs-12 col-sm-6">
									<div id="applications" style="height:120px;"></div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<ul class="graph-legend">
                                    	<li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                        <li><span class="statusBox"></span>Total Applications<span class="count">1200</span></li>
                                    </ul>
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
							<ul class="list-border list-inline">
								<li>
									<h3>2</h3>
									<p class="text-capitalize">central</p>
								</li>
								<li>
									<h3>2</h3>
									<p class="text-capitalize">central</p>
								</li>
								<li>
									<h3>2</h3>
									<p class="text-capitalize">central</p>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="block">
					<button class="btn btn-success btn-success-white">detailed information</button>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20"  navbar-index="grievance">
				<h3>Grievance & Insurance <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Grievance</h4>
							<div id="grievanceDetails0"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Insurance</h4>
							<div id="grievanceDetails1"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Trust Education</h4>
							<div id="grievanceDetails2"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top15">
							<button class="btn btn-success btn-success-white text-capital">detailed information</button>
						</div>
					</div>
				</div>
			</div>-->
		</div>
	</div>
</section>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="coreApi/js/custom.js" type="text/javascript"></script>
<script src="coreApi/js/constituencyPage.js" type="text/javascript"></script>
</body>
</html>
