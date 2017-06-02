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
		</div>
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
			<button class="btn btn-success btn-success-white text-capital">view candidate profile</button>
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
