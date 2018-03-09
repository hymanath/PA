<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Government  Word Cloud News</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="coreApi/css/bootstrap-multiselect.css" type="text/css">
<link href="newCoreDashBoard/css/wordCloud.css" rel="stylesheet" type="text/css">
<style>
.empty-alert,
.data-sent-alert,
.data-processing-alert,
.error-alert {
    display: none;
    margin: 1vh 1vw 0 1vw;
    position: absolute;
    top: 25vh;
    width: 20vw;
    right: 40vw;
}
.spinner {
	margin:auto;
	width: 40px;
	height: 40px;
	position: relative;
	text-align: center;
	-webkit-animation: sk-chasingDotsRotate 2s infinite linear;
    animation: sk-chasingDotsRotate 2s infinite linear; 
}
.spinner .dot1 , .spinner .dot2 {
    width: 60%;
    height: 60%;
    display: inline-block;
    position: absolute;
    top: 0;
    background-color: #1ABC9C;
    border-radius: 100%;
    -webkit-animation: sk-chasingDotsBounce 2s infinite ease-in-out;
    animation: sk-chasingDotsBounce 2s infinite ease-in-out; 
}
.spinner .dot2 {
    top: auto;
    bottom: 0;
    -webkit-animation-delay: -1s;
    animation-delay: -1s; 
}

@-webkit-keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }

@keyframes sk-chasingDotsBounce {
  0%, 100% {
    -webkit-transform: scale(0);
            transform: scale(0); }
  50% {
    -webkit-transform: scale(1);
            transform: scale(1); } }
@-webkit-keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

@keyframes sk-chasingDotsRotate {
  100% {
    -webkit-transform: rotate(360deg);
            transform: rotate(360deg); } }

</style>
</head>
<body >
<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
	<div class="white-block" style="padding: 30px;">
		
		<div class="row">
			<div class="col-sm-12">
				<div id="chart"  style="margin-left: 267px;">
					<svg id="svg">
					</svg>
				</div>
			</div>
		</div>
		<div class="row align-items-center m_top10" style="margin-left: 250px; margin-right: 0px;">
			<div class="col-sm-2 offset-2 labels-row">
				<label for="crit" class="label-padding">Critical</label>
				<div id="crit" class="box-color critical"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="neg" class="label-padding">Negative</label>
				<div id="neg" class="box-color negative"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="pos" class="label-padding">Positive</label>
				<div id="pos" class="box-color positive"></div>
			</div>
			<div class="col-sm-2 labels-row">
				<label for="neu" class="label-padding">Neutral</label>
				<div id="neu" class="box-color neutral"></div>
			</div>
		</div>
		<div class="bg_color m_top10" style="border:1px solid #ddd;border-radius:5px">
			<div class="row">
			
				<div class="col-sm-3">
					<label for="startDate">Start Date</label>
					<div class="input-group dateRangePickerCls" >
						<input type="text" id="startDate" class="form-control" />
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-3">
					<label for="endDate">End Date</label>
						<div class="input-group dateRangePickerCls" >
							<input type="text" id="endDate" class="form-control" />
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</div>
					</span>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudDistrict">District</label>
					<select class="" multiple="multiple" id="wordCloudDistrict">
						
					</select>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudConstituency">Constituency</label>
					<select class="" multiple="multiple" id="wordCloudConstituency">
						<option selected>Constituency</option>
					</select>
				</div>
				<div class="col-sm-3">
					<label for="newspapers">News Paper</label>
					<select class="" multiple="multiple" id="newspapers">
					</select>
				</div>
				<div class="col-sm-3">
					<label>Edition Type</label>
					<select class="" multiple="multiple" id="editionType">
						<option selected> Main </option>
						<option selected> District </option>
						<option selected> Online </option>
					</select>
				</div>
				<div class="col-sm-3">
					<label for="wordCloudDepartmentNames" >Department Name</label>
					<select class="" multiple="multiple" id="wordCloudDepartmentNames">
						<option selected>Department</option>
					</select>
				</div>
				<div class="col-sm-3">
					<button class="btn btn-primary" style="margin-top: 22px;" onclick="fetchDataForWordCloud('fromPage')">Submit</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="empty-alert">
				<div class="alert alert-danger" role="alert">
					Results are empty!
				</div>
			</div>
			<div class="error-alert">
				<div class="alert alert-danger" role="alert">
					Error in server!
				</div>
			</div>
			<div class="data-sent-alert">
				<div class="alert alert-success" role="alert">
					<div class="row">
						<div class="col-10">
							Request sent!
						</div>
						<div class="col-2">
							<div class="loader-2"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="data-processing-alert">
				<div class="alert alert-primary" role="alert">
					<div class="row">
						<div class="col-10">
							Data received and processing word cloud!
						</div>
						<div class="col-2">
							<div class="loader"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="card headline-div">
				<div class="card-header" style="background-color: blanchedalmond;">
					<div class="row">
						<div class="col-sm-11" style="padding-left: 33px;"> News Article Titles </div>
						<div class="col-1 offset-4">
							<i class="fa fa-times close-button" onclick="closeDiv()"></i>
						</div>
					</div>
				</div>
				<ul class="list-group list-group-flush headline-div-content">
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModalShowNews"></div> 
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js" integrity="sha256-dsOXGNHAo/syFnazt+KTBsCQeRmlcW1XKL0bCK4Baec="
        crossorigin="anonymous"></script>
		
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-cloud/1.2.4/d3.layout.cloud.min.js" integrity="sha256-+U6evHIlf3gdG4NC/P4v3g4JpbLdSdYHAu/z0w2nZ4I="
	crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js" integrity="sha256-8E6QUcFg1KTnpEU8TFGhpTGHw5fJqB9vCms3OhAYLqw="
	crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="coreApi/js/bootstrap-multiselect.js"></script>
<script src="js/wordCloud.js" type="text/javascript"></script>

</body>	
</html>