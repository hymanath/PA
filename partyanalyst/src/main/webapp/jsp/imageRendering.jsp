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
<title>News Image Rendering</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,500,700,700italic,900,900italic,400italic,500italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script type="text/javascript" src="https://ff.kis.v2.scr.kaspersky-labs.com/8896ACD5-F0B9-0F4A-9DE1-BAFB8390D32D/main.js" charset="UTF-8"></script><script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js" integrity="sha256-dsOXGNHAo/syFnazt+KTBsCQeRmlcW1XKL0bCK4Baec="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-cloud/1.2.4/d3.layout.cloud.min.js" integrity="sha256-+U6evHIlf3gdG4NC/P4v3g4JpbLdSdYHAu/z0w2nZ4I="
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js" integrity="sha256-8E6QUcFg1KTnpEU8TFGhpTGHw5fJqB9vCms3OhAYLqw="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha256-eZrrJcwDc/3uDhsdt61sL2oOBY362qM3lon1gyExkL0="
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<style>
        body {
    font-family: "Lucida Grande", "Droid Sans", Arial, Helvetica, sans-serif;
    overflow-x: hidden;
}

text:hover {
    text-decoration: underline;
    cursor: pointer;
}

.parent-div {
    margin-top: 2vh;
    width: 100%;
}

.headline-div {
    width: 26vw;
    position: absolute;
    display: none;
    top: 40%;
    left: 30%;
    z-index: 10;
    height: 30%;
}

.headline-div-content {
    font-size: 3vh;
    overflow: auto;
    height: 80%;
}

ul li:nth-child(2n) {
    background-color: rgba(0, 0, 0, 0.03);
}

.close-button:hover {
    cursor: pointer;
}

.district-align {
    margin-left: 3.4vw !important;
}

.district-align2 {
    padding-left: 3.4vw !important;
}

.box-color {
    float: left;
    width: 20px;
    height: 20px;
    margin: 5px;
    border: 1px solid rgba(0, 0, 0, 0.2);
}

.positive {
    background-color: green;
}

.negative {
    background-color: red;
}

.neutral {
    background-color: grey;
}

.critical {
    background-color: purple;
}

.label-padding {
    margin-top: 0.4vh;
}

#chart {
    margin: 0 25vw 0 25vw;
}

.labels-row {
    outline: 1px solid darkgray;
}

.info {
    padding: 1% 1% 1% 1%;
}

.checkboxes {
    padding: 0 1% 0 1%;
    margin: 0 1vw 0 1vw;
}

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

.navbar {
    background: #E96840;
    opacity: 0.8;
}

.loader {
    border: 4px solid #f3f3f3;

    /* Light grey */
    border-top: 4px solid #3498db;

    /* Blue */
    border-radius: 50%;
    width: 2vw;
    height: 2vw;
    animation: spin 2s linear infinite;
}

.loader-2 {
    border: 4px solid #f3f3f3;

    /* Light grey */
    border-top: 4px solid green;

    /* Blue */
    border-radius: 50%;
    width: 2vw;
    height: 2vw;
    animation: spin 2s linear infinite;
}

@keyframes spin {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
}

.parameters-card {
    margin: 1vh 1vw 0 1vw;
    padding-bottom: 0;
}

.parameters-card-header {
    padding-bottom: 0;
}

.parameters-card-body {
    padding: 0;
    margin-bottom: 0;
}

.date-district-row {
    margin-bottom: 0;
}

.selectBox {
    position: relative;
}

.selectBox select {
    width: 100%;
    font-weight: bold;
}

.overSelect {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
}

#checkboxes,
#checkboxes1,
#checkboxes2 {
    display: none;
    border: 1px #dadada solid;
    position: absolute;
    z-index: 100;
    background-color: white;
    width: 90%;
    height: 20vh;
    overflow-y: auto;
    overflow-x: hidden;
}

#checkboxes label,
#checkboxes1 label {
    display: block;
}

#checkboxes label:hover {
    background-color: #1e90ff;
}

#checkboxes1 label:hover {
    background-color: #1e90ff;
}

.red {
    color: red;
}

.grey {
    color: grey;
}

.purple {
    color: purple;
}

.green {
    color: green;
}

.submit-button {
    margin-top: 1vh;
}

    </style>
</head>
<body >
<div class="container-fluid">
	<div class="col-sm-12" style="border: 2px solid #d7dae0; border-radius: 5px; padding: 10px 0px 10px 25px;">
			<div class="col-sm-12 responsive">
				<div id="chart">
					<svg id="svg">
					</svg>
				</div>
				<div class="row align-items-center" style="margin-left: 250px; margin-right: 0px;">
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
			</div>
			<div class="col-sm-12">
				<div class="card parameters-card">
					<div class="card-body parameters-card-body">
						<div class="info">
							<div class="form-group row date-district-row">
								<div class="row m_top10">	
									<div class="col-sm-6 offset-1">
										<label for="startDate">Start Date</label>
										<span class="input-group pull-right dateRangePickerCls" style="margin-right:23px;">
											<input type="text" id="startDate" class="form-control" />
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-calendar"></i>
											</span>
										</span>
									</div>
									<div class="col-sm-6">
										<label for="endDate">End Date</label>
											<span class="input-group pull-right dateRangePickerCls" style="margin-right:23px;">
												<input type="text" id="endDate" class="form-control" />
												<span class="input-group-addon">
												<i class="glyphicon glyphicon-calendar"></i>
											</span>
										</span>
									</div>
								</div>
								<div class="row m_top10">
									<div class="col-sm-6">
										<label for="district">District</label>
										<!--<div class="multiselect">
											<div class="selectBox" onclick="showCheckboxes()">
												<select id="district" class="form-control">
													<option>Select a district</option>
												</select>
												<div class="overSelect" id="overSelect"></div>
											</div>
											<div id="checkboxes"></div>
										</div>-->
										<select class="form-control chosen-select" multiple="multiple" id="district">
										</select>
									</div>
									<div class="col-sm-6">
										<label for="constituency">Constituency</label>
										<!--<div class="multiselect">
											<div class="selectBox" onclick="showCheckboxes1()">
												<select id="constituency" class="form-control">
													<option>Select a constituency</option>
												</select>
												<div class="overSelect"></div>
											</div>
											<div id="checkboxes1">
											</div>
										</div>-->
										<select class="form-control chosen-select" multiple="multiple" id="constituency">
										</select>
									</div>
								</div>
							<div class="row m_top10">
								<div class="col-sm-6">
									<label for="newspapers">Newspaper</label>
									<!--<div class="multiselect">
										<div class="selectBox" onclick="showCheckboxes2()">
											<select id="newspapers" class="form-control">
												<option>Select a Newspaper</option>
											</select>
											<div class="overSelect"></div>
										</div>
										<div id="checkboxes2"></div>
									</div>-->
									<label>News Paper</label>
								<select class="form-control chosen-select" multiple="multiple" id="newspapers">
									</select>
								</div>
								<div class="col-sm-6">
									<label>Edition Type</label>
									<select class="form-control chosen-select" multiple="multiple" id="editionType">
										<option> Main </option>
										<option> District </option>
										<option> Online </option>
									</select>
								</div>
							</div>
							<div class="row m_top10">
								<div class="col-sm-6">	
									<div class="submit-button">
										<div class="text-center">
											<button class="btn btn-primary" style="margin-top: 22px;" onclick="fetchDataForWordCloud()">Submit</button>
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

    <div class="card headline-div">
        <div class="card-header">
            <div class="row">
                <div class="col-6">
                    Headlines
                </div>
                <div class="col-1 offset-4">
                    <i class="fa fa-times close-button" onclick="closeDiv()"></i>
                </div>
            </div>
        </div>
        <ul class="list-group list-group-flush headline-div-content">
        </ul>
    </div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="js/imageRendering.js" type="text/javascript"></script>

</body>
</html>