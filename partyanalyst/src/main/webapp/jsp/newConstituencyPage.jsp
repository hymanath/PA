 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.itgrids.partyanalyst.utils.IConstants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value='${constituencyDetails.constituencyName}'/>&nbsp;
	<c:out value='${constituencyDetails.constituencyType}'/> Constituency</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
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
						<c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="media media-profile">
								<div class="media-left">
									<img src="images/candidates/${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}.jpg" class="media-object profile-image img-border" alt="profile"/>
									<span class="border-image img-border">
										<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyFlag}" alt="party"/>
									</span>
								</div>
								<div class="media-body">
									<h4 class="m_top20 text-success text-capital">${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}</h4>
									<p class="text-muted">MLA<span class="text-capitalize">[Member of legislative assembly]</span></p>
									<button class="btn btn-success btn-success-white text-capital">view candidate profile</button>
								</div>
							</div>
						</div>
						</c:if>
						<c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="media media-profile">
								<div class="media-left">
									<img src="images/candidates/${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}.jpg" class="media-object profile-image img-border" alt="profile"/>
									<span class="border-image img-border">
										<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" alt="party"/>
									</span>
								</div>
								<div class="media-body">
									<h4 class="m_top20 text-success text-capital">${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</h4>
									<p class="text-muted">MP<span class="text-capitalize">[Member of Parliament]</span></p>
									<button class="btn btn-success btn-success-white text-capital">view candidate profile</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div id="consCountsId"></div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="election">
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
				<div class="block">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<h4 class="text-capitalize">Assembly Election Details</h4>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div id="assemblyElectionDetails"></div>
							<div id="assemblyElectionDetails1"></div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6">
							
						</div>
					</div>
				 </div>
			</div>
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
				<div class="block">
					<table class="table tableVoters">
						<thead>
							<th>
								<img src="coreApi/img/tableHead.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead1.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead1.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
							<th>
								<img src="coreApi/img/tableHead1.png" alt="voters"/>
								<h4 class="text-capitalize">total population</h4>
								<h3>12500</h3>
							</th>
						</thead>
						<tbody>
							<tr>
								<td>
									<b>&lt; 20</b>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<b>&lt; 20</b>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<b>&lt; 20</b>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<b>&lt; 20</b>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
								<td>
									2000
									<span class="pull-right text-success">
										2%
									</span>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row m_top20">
						<div class="col-md-12 col-xs-12 col-sm-12 text-right">
							<button class="btn btn-success btn-success-white text-capital">detailed information</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="casteInfo">
				<h3>Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title">Caste Group Wise Voters
								<ul class="switch-btn">
									<li class="active">Voter</li>
									<li>Cadre</li>
								</ul>
							</h4>
							<div class="m_top20" id="casteGroupVoters"></div>
							
							<table class="table table-bordered bg-E9">
								<thead>
									<th>OC</th>
									<th>BC</th>
									<th>SC</th>
									<th>ST</th>
									<th>MINORITY</th>
								</thead>
								<tbody>
									<tr>
										<td>25000</td>
										<td>25000</td>
										<td>25000</td>
										<td>25000</td>
										<td>25000</td>
									</tr>
								</tbody>
							</table>
							
							<h5>Caste Wise Voters</h5>
							<div class="m_top20" id="casteWiseVoters"></div>
						</div>
						<div class="col-md-8 col-xs-12 col-sm-8">
							<div>
							  <!-- Nav tabs -->
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
							  <!-- Tab panes -->
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
							
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<button class="btn btn-success btn-success-white text-capital">detailed caste information</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="cadreInfor">
				<h3>Cadre Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<ul class="list-inline cadre-info-list">
						<li class="bottom-arrow">
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<h4 class="text-capital">2016-2018 cadre</h4>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<div id="cadreInfoGraph0" class="graph"></div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<table class="table table-noborder">
										<tr>
											<td>6000 new <span class=""></span></td>
											<td>6000 new <span class=""></span></td>
											<td>=</td>
											<td>6000 new <span class=""></span></td>
										</tr>
									</table>
								</div>
							</div>
						</li>
						<li>
							<div class="row">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<h4 class="text-capital">2016-2018 cadre</h4>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<div id="cadreInfoGraph1" class="graph"></div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<table class="table table-noborder">
										<tr>
											<td>6000 new <span class=""></span></td>
											<td>6000 new <span class=""></span></td>
											<td>=</td>
											<td>6000 new <span class=""></span></td>
										</tr>
									</table>
								</div>
							</div>
						</li>
					</ul>
					<div id="cadreInfoGraphBar"></div>
					<table class="table table-noborder table-noborder-hover m_top10">
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
					</table>
				</div>
				<div class="block">
					<button class="btn btn-success btn-success-white text-capital">detailed caste information</button>
				</div>
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="news">
				<h3>News <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>		
				<div class="block">
					<div class="row">
						<div class="col-dm-6 col-xs-12 col-sm-6 pad_right0">
							<div class="block pad_5">
								<h4 class="text-capitalize"><img src="coreApi/img/news.png"/>print media</h4>
								<div id="printMediaCounts"></div>
							</div>
						</div>
						<div class="col-dm-6 col-xs-12 col-sm-6 pad_left0">
							<div class="block pad_5">
								<h4 class="text-capitalize"><img src="coreApi/img/electronic.png"/>electronic media</h4>
								<div id="electronicMediaCounts"></div>
</div>
						</div>
					</div>
					<div class="panel-group m_top10" id="newsCollapse" role="tablist" aria-multiselectable="true">
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
										<tr>
											<td>
												<img src="coreApi/img/profile.jpg" class="img-responsive img-circle" style="height: 30px;width: 30px;display: inline-block"/> praven tej
											</td>
											<td>secretary</td>
											<td>50%</td>
											<td>40%</td>
										</tr>
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
					</div>
				</div>
			</div>
			
			<div class="col-md-6 col-xs-12 col-sm-6 m_top20" navbar-index="problems">
				<h3>Problems <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>						
				<div class="block pad_5">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-12">
							<div id="probDetailedCounts"></div>
						</div>
						<div class="col-md-6 col-xs-12 col-sm-12 text-center">
							<div id="problemsDetailedGraph" style="height:200px"></div>
							<button class="btn btn-success btn-success-white">detailed information</button>
						</div>
					</div>
					<div class="panel-group m_top10" id="problemsCollapse" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
						<div class="panel-heading bg-fff" role="tab" id="headingProb">
							<a role="button" data-toggle="collapse" class="collapseIcon" data-parent="#problemsCollapse" href="#collapseProb" aria-expanded="true" aria-controls="collapseProb">
							  	<h4 class="panel-title text-capital">problems can be solved without money</h4>
							</a>
						</div>
						<div id="collapseProb" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingProb">
						  	<div class="panel-body">
								<div class="row">
									<div class="col-md-7 col-xs-12 col-sm-7">
										<div id="problemsSolveId"></div>
									</div>
									<div class="col-md-5 col-xs-12 col-sm-5">
										<div id="problemsSolveGraph" style="height:1060px;"></div>
									</div>
								</div>
							</div>
						</div>
					  </div>
					</div>
					<div class="pad_10">
						<button class="btn btn-success btn-success-white">detailed news information</button>
					</div>
				</div>
			</div>
			
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="committees">
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
						<div class="block pad_0" id="committees">
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
				<div class="block pad_0" id="meetingsId">
					<!-- <table class="table table-bordered">
						<thead class="text-capitalize">
							<th></th>
							<th class="bg-E9">Village / Ward</th>
							<th class="bg-E9">mandal/town/division</th>
							<th class="bg-E9">constituency</th>
						</thead>
						<tbody>
							<tr>
								<td>Total</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Yes</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
							<tr>
								<td>No</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
							<tr>
								<td>MayBe</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Not Updated</td>
								<td>1600</td>
								<td>80</td>
								<td>1</td>
							</tr>
						</tbody>
					</table> -->
					<div class="pad_10">
						<button class="btn btn-success btn-success-white text-capital">detailed information</button>
					</div>
				</div>
			</div>
			
			<div class="col-md-4 col-xs-12 col-sm-4 m_top20" navbar-index="tours">
				<h3>Tours <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<p class="f-12 text-muted">Last Month Jan 2017</p>
				<div class="block pad_0" id="toursId">
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
					  <!-- Nav tabs -->
					  <div class="col-md-3 col-xs-12 col-sm-3 pad_right0">
						  <select class="form-control" role="tabListMobile">
							  <option tab_id="benefits1">OC (20)</option>
							  <option tab_id="benefits2">OC (20)</option>
							  <option tab_id="benefits3">OC (20)</option>
							  <option tab_id="benefits4">OC (20)</option>
						  </select>
						  <ul class="nav nav-tabs nav-tabs-horizontal" role="tablist" id="benefitsId">
							<!-- <li class="active"><a href="#benefits1" aria-controls="OC" role="tab" data-toggle="tab">CM Relief Fund <span class="pull-right">20</span></a></li>
							<li><a href="#benefits2" aria-controls="benefits2" role="tab" data-toggle="tab">Brahmin Corporation<span class="pull-right">20</span></a></li>
							<li><a href="#benefits3" aria-controls="benefits3" role="tab" data-toggle="tab">Muslim Minorities<span class="pull-right">20</span></a></li>
							<li><a href="#benefits4" aria-controls="benefits4" role="tab" data-toggle="tab">BC Corp Beneficiery<span class="pull-right">20</span></a></li> -->
						  </ul>
					  </div>
					  <!-- Tab panes -->
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
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="activities">
				<h3 >Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block pad_0" id="activiteId">
					<div class="pad_10">
						<button class="btn btn-success btn-success-white text-capital">detailed information</button>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-ms-12 m_top20" navbar-index="alerts">
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
									 <!-- Nav tabs -->
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
							  <!-- Tab panes -->
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
							<div id="grivanceId"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Insurance</h4>
							<div id="insuranceId"></div>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
							<h4 class="panel-title text-capital text-center">Trust Education</h4>
							<div id="trustId"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top15">
							<button class="btn btn-success btn-success-white text-capital">detailed information</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
			<div class="modal-content customModal">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Candidates affidavit summary</h4>
			  </div>
			  <div class="modal-body">
			   <div id="buildPoupupImage"></div>
			  </div>
			</div>
		  </div>
	</div>
</section>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/js/handlebars-v4.0.5.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<!-- handlebars cons counts template -->
<script id="cons-counts" type="text/x-handlebars-template">
	<table class="table table-bordered block">
		<tr>
			<td>
				<h4 class="text-capitalize text-muted">Mandals</h4>
				<h3>{{totalmandals}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
			<td>
				<h4 class="text-capitalize text-muted">Muncipalities</h4>
				<h3>{{noOfLocalBodies}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
			<td>
				<h4 class="text-capitalize text-muted">Panchayats</h4>
				<h3>{{totalPanchayats}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
			<td>
				<h4 class="text-capitalize text-muted">Wards</h4>
				<h3>{{totalNoOfWards}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
			<td>
				<h4 class="text-capitalize text-muted">Booths</h4>
				<h3>{{totalBooths}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
			<td>
				<h4 class="text-capitalize text-muted">Hamlets</h4>
				<h3>{{totalNoOfHamlets}}</h3>
				<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>
			</td>
		</tr>
	</table>
</script>
<script id="prob-counts" type="text/x-handlebars-template">
<table class="table table-noborder f-12">
	<thead class="bg-E9 text-capitalize">
		<th>status</th>
		<th>total</th>
		<th>%</th>
	</thead>
	<tbody>
	{{#each this}}
		<tr>
			<td>{{name}}</td>
			<td>{{count}}</td>
			<td>{{positivePerc}}%</td>
		</tr>
	{{/each}}
	</tbody>
</table>
</script>
<script id="media-counts" type="text/x-handlebars-template">
<table class="table table-noborder m_top10">
	<thead class="text-capitalize f-12 bg-E9">
		<tr>
			<th>total articles</th>
			<th>positive</th>
			<th>negative</th>
		</tr>
	</thead>
	<tbody>
		{{#each this}}
		<tr>
			<td>{{count}}</td>
			<td>{{positiveCountMain}} <small class="m_left5 text-success">{{positivePerc}}%</small></td>
			<td>{{negativCountMain}} <small class="m_left5 text-danger">{{negativePerc}}%</small></td>
		</tr>
		{{/each}}
	</tbody>
</table>
</script>
<script id="problem-graph" type="text/x-handlebars-template" >
<ul class="textAlignDepartment">
{{#each this}}
		<li><span class="problemsMinimiseText">{{name}}</span> <span class="pull-right">{{count}}</span></li>
{{/each}}									
</ul>
</script>
<script src="coreApi/js/custom.js" type="text/javascript"></script>
<script src="coreApi/js/newConstituencyPage.js" type="text/javascript"></script>

</body>
</html>