<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assembly Constituency </title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="coreApi/css/custom.css" rel="stylesheet" type="text/css">
</head>
<body>
<aside>
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
</aside>
<div class="container">
	<section>
		<div class="row">
			<div id="candidateProfile"></div>
			<div id="consCountsId"></div>
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
							<div id="assemblyElectionDetailsView"></div>
						</div>
					</div>
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
			<!-- Teja -->

		<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="nominatedPosts">
			<h3>Nominated Post <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
			<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6">
							<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6 " >
									<div id="postsDiv" ></div>
							</div>
					
							<div id="nominatedPostDiv" style="padding-top:20%"></div>
							</div>
							
						</div>
						<div class="col-md-6 col-xs-12 col-sm-6" id="NominatedPostInfoChartDivId">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<hr class="m_0"/>
							<h4 class="text-capital text-center m_top-10"><span class="bg-fff pad_10">position level</span></h4>
						</div>
						<div id="nominatedMembersDiv"></div>
					</div>
				</div>
			<div class="block">
				<button class="btn btn-success btn-success-white">detailed information</button>
			</div>
		</div>
		
		<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="casteInfo">
			<h3>Caste Information <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
			<div class="block">
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-4">
						<h4 class="panel-title">Caste Group Wise Voters
							<ul class="switch-btn">
								<li class="voterCadreSwitchCls active" attr_type="voter">Voter</li>
								<li class="voterCadreSwitchCls" attr_type="cadre">Cadre</li>
							</ul>
						</h4>
						<div class="" id="casteGroupInfoChartDivId"></div>
						<div class="m_top20" id="casteGroupTableDivId"></div>
						
						<h5>Caste Wise Voters</h5>
						<div class="m_top20" id="casteWiseVoters"></div>
					</div>
					<div class="col-md-8 col-xs-12 col-sm-8">
						<div id="casteGroupsTabsDivId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12">
						<button class="btn btn-success btn-success-white text-capital">detailed caste information</button>
					</div>
				</div>
			</div>
		</div>
		<!--Committees starting -->
		<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="committees">
						<div class="block">
							<div class="row">
								<div class="col-md-6 col-xs-12 col-sm-6">
									<h4 class="panel-title text-capital">main committee formation</h4>
										<div class="row m_top15">
											<div class="col-md-6 col-xs-12 col-sm-6" id="mandalLevelId"></div>
											<div class="col-md-6 col-xs-12 col-sm-6" id="villageLevelId"></div>
										</div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-6">
									<h4 class="panel-title text-capital">affliated committee formation</h4>
									<div class="row m_top15">
										<div class="col-md-6 col-xs-12 col-sm-6" id="affMandalLevelId"></div>
										<div class="col-md-6 col-xs-12 col-sm-6" id="affVillageLevelId">
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
					<!--Committees ending -->
					
						<!-- alerts Starting -->
				<div id="alertsBlockDiv"></div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="nominatedPosts">
				<div class="block">
					<button class="btn btn-success btn-success-white">detailed information</button>
				</div>
			</div>
			<!-- alerts ending -->
					
                <!--Meetings & Tours Starting -->
		<div class="col-md-8 col-xs-12 col-sm-8 m_top20" navbar-index="meetings">
			<h3>Meetings <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
			<p class="f-12 text-muted">Evertymonth: 9th/10th/11th</p>
			<div class="block pad_0" id="meetingsId">
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
			<!--Meetings & Tours ending -->
			
			<!--benefits Starting -->
		<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="benefits">
			<div class="panel-heading">
			<h3>Benefits <!--<span class="benefitsRefresh pull-right"><i class="glyphicon glyphicon-refresh f-14" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
			</span>--></h3></div>
			<div class="block pad_0">
				<div class="row">
				  <!-- Nav tabs -->
				  <div class="col-md-3 col-xs-12 col-sm-3 pad_right0" id="benefitsId">
				  </div>
				  <!-- Tab panes -->
				  <div class="col-md-6 col-xs-12 col-sm-6">
					<div id="benefitsGraph"></div></div>
				  <div class="col-md-9 col-xs-12 col-sm-9 pad_left0">
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active pad_10" id="benefits1">
							
							 <!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
								<button class="btn btn-success btn-success-white text-capital">detailed caste information</button>
							</div>-->
						</div>
						
					  </div>
				  </div>

				</div>
			</div>
		</div>
			<!--benefits ending -->
			
			<!--Activities Starting -->
		<div class="col-md-12 col-xs-12 col-sm-12 m_top20" navbar-index="activities">
			<h3 >Activities <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
			<div class="block pad_0" id="activiteId">
				<div class="pad_10">
					<button class="btn btn-success btn-success-white text-capital">detailed information</button>
				</div>
			</div>
		</div>
			<!--Activities ending -->
			
			<!--grievance starting -->
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20"  navbar-index="grievance">
				<h3>Grievance & Insurance <span class="pull-right"><i class="glyphicon glyphicon-refresh f-14"></i></span></h3>
				<div class="block">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4"id="grivanceId">
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4" id="insuranceId">
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4" id="trustId">
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top15">
							<button class="btn btn-success btn-success-white text-capital">detailed information</button>
						</div>
					</div>
				</div>
			</div>
			<!--grievance ending -->
		

					
	</section>
</div>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="coreApi/js/handlebars-v4.0.5.js" type="text/javascript"></script>
<script src="coreApi/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="coreApi/js/custom.js" type="text/javascript"></script>
<!-- handlebars cons counts template -->

<script id="candidateProfileTemplate" type="text/x-handlebars-template">

<div class="col-sm-6">
	<div class="media media-profile">
		<div class="media-left">
			<img src="images/candidates/{{partyFlag}}.jpg" class="media-object profile-image img-border" alt="profile"/>
			<span class="border-image img-border">
				<img src="" alt="party"/>
			</span>
		</div>
		<div class="media-body">
			<h4 class="m_top20 text-success text-capital">{{candidateName}}</h4>
			<p class="text-muted">{{constituencyName}} {{constituencyType}}</p>
			<a  target="_blank" href="https://mytdp.com/candidateElectionResultsAction.action?candidateId={{candidateId}}" class="btn btn-success btn-success-white text-capital">view candidate profile</a>
		</div>
	</div>
</div>

</script>
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
<script id="assemblyElectionDetailsTable" type="text/x-handlebars-template">
<table class="table table-noborder m_top10">
	<thead class="text-capitalize f-12 bg-E9">
		<tr>
			<th>Year</th>
			<th style="width:10%"><i class="fa fa-thumbs-up" style="font-size:24px;color:green"></i></th>
			<th>won candidate</th>
			<th>majority of votes</th>
			<th><i class="fa fa-thumbs-down" style="font-size:24px;color:red"></i></th>
			<th>lost candidate</th>
		</tr>
	</thead>
	<tbody>
		{{#each this}}
		<tr>
			<td>{{electionYear}}</td>
			<td><img src="images/party_flags/{{candidateResultsVO.partyShortName}}.png"></td>
			<td>{{candidateResultsVO.candidateName}}</td>
			<td>{{candidateResultsVO.votesEarned}}</td>
			<td><img src="images/party_flags/{{candidateOppositionList.0.partyShortName}}.PNG"></td>
			<td>{{candidateOppositionList.0.candidateName}}</td>
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
<script src="coreApi/js/newConstituencyPage.js" type="text/javascript"></script>
</body>
</html>
