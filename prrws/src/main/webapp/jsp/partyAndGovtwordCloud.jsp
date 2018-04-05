<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WORD CLOUD ARTICLES</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/wordCloud.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="Assests/Plugins/Scroller/bootstrap-multiselect.css" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
</style>
</head>
<body>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-xs-12" style="margin-bottom: 3px; margin-top: 3px;">
					<h4 class="text-capital" style="text-align:center;font-size: 25px">WORD CLOUD NEWS</h4>
				</div>
			</div>
		</div>
	</nav>
	
</header>
<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
	<div class="white-block" style="padding: 20px;">
		<div class="row">
			<div class="col-sm-12">
				<div id="chart">
					<svg id="svg">
					</svg>
				</div>
			</div>
		</div>
		<div class="row align-items-center" style="margin-left: 250px;">
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
				<div class="col-sm-12" style="padding-bottom: 8px;">
					<ul class="list-inline switch-btn alertCategoryWiseCls pull-right m_top5" style=" background-color: #337ab7;">
					<li class="active" attr_type="N" >Party</li>
					<li attr_type="Y">Govt</li>
					<li attr_type="">ALL</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<label for="startDate">Start Date</label>
					<div class="input-group dateRangePickerCls" >
						<input type="text" id="startDate" class="form-control" style="width: 230px;"/>
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-3">
					<label for="endDate">End Date</label>
						<div class="input-group dateRangePickerCls" >
							<input type="text" id="endDate" class="form-control" style="width: 230px;"/>
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
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/Plugins/Scroller/bootstrap-multiselect.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js" integrity="sha256-dsOXGNHAo/syFnazt+KTBsCQeRmlcW1XKL0bCK4Baec="
        crossorigin="anonymous"></script>
		
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-cloud/1.2.4/d3.layout.cloud.min.js" integrity="sha256-+U6evHIlf3gdG4NC/P4v3g4JpbLdSdYHAu/z0w2nZ4I="
	crossorigin="anonymous"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js" integrity="sha256-8E6QUcFg1KTnpEU8TFGhpTGHw5fJqB9vCms3OhAYLqw="
	crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>

<script type="text/javascript" src="Assests/js/partywordCloud.js"></script>
<script type="text/javascript">
var globalDeptId = "${param.deptId}";
var globalfavName = "${param.name}";
</script>
</body>
</html>