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

</head>
<body >
<div class="panel-body">
	<div class="col-sm-12" style="border: 2px solid #d7dae0; border-radius: 5px;  padding: 10px 0px 10px 25px;">
		<div class="row">
			<div class="col-md-6 col-xs-6 col-sm-6">
				<div class="row m_top10">
					<div class="col-sm-6">
						<label>From Date</label>
						<span class="input-group pull-right dateRangePickerCls" style="margin-right:23px;">
							<input type="text" id="dateRangeFromDateNewsId" class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
					<div class="col-sm-6">
						<label>To Date</label>
						<span class="input-group pull-right dateRangePickerCls" style="margin-right:23px;">
							<input type="text" id="dateRangeToDateNewsId" class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
					
				</div>
				<div class="row">
					<div class="col-sm-6">
						<label>State</label>
						<select class="form-control chosen-select" id="newsLetterStateId">
							<option value="Andhra Pradesh" selected> Andhra Pradesh </option>
						</select>
					</div>
					<div class="col-sm-6">
						<label>District</label>
						<select class="form-control chosen-select" id="newsLetterDistrictId">
							
						</select>
					</div>
					
				</div>
				<div class="row m_top10">
					<div class="col-sm-6">
						<label>Constitency</label>
						<select class="form-control chosen-select" id="newsLetterConstituencyId">
							
						</select>
					</div>
					<!--<div class="col-sm-4">
						<label>Parliament</label>
						<select class="form-control chosen-select" id="newsLetterParliamentId">
							<option value="" selected>Parliament</option>
							<option> Rajahmundry </option>
							<option> Visakhapatnam </option>
							<option> Vijayawada </option>
							<option> Rajampet </option>
							<option> Warangal </option>
						</select>
					</div>-->
					<div class="col-sm-6">
						<label>News Paper</label>
						<select class="form-control chosen-select" multiple="multiple" id="newsLetternewsPaperId">
							
						</select>
					</div>
					
				</div>
				<div class="row m_top10">
					<div class="col-sm-6">
						<label>Edition Type</label>
						<select class="form-control chosen-select" multiple="multiple" id="newsLetterEditionId">
							<option> Main </option>
							<option> District </option>
							<option> Online </option>
						</select>
					</div>
					<div class="col-sm-6" style="border-top-width: 0px; padding-top: 23px; padding-left: 73px;">
						<button type="button" id="submitId" class="btn btn-default btn-md m_top10">Submit</button>
					</div>
				</div>
				<div class="row m_top20">
					<div id=""></div>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 col-sm-6 img-responsive">
				<div class="img-responsive" id ="imageId">
				</div>
			</div>
		</div>
	</div>
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