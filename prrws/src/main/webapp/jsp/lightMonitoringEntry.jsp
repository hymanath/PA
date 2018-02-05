<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LED ENTRY DASHBOARD</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>

<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj & RD & RWS</h4>
					<p>LED - Dashboard</p>
				</div>
				<div class="col-sm-2 col-sm-offset-4 m_top20">
					<a class="btn btn-success m_top5 pull-right" href="lightMonitoringLogout" style="display:inline-block" style="cursor:pointer;">LOGOUT</a>
				</div>
			</div>
		</div>
	</nav>
</header>

<main>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="white_block">
						<h4 class="font_weight">${sessionScope.User.vendorName} - Vendor View</h4>
						<div class="pad_border m_top10">
							<div class="row">
								<div class="col-sm-1">
									<img src="Assests/icons/${sessionScope.User.vendorName}.jpg"/>
								</div>
								<div class="col-sm-10">
									<div class="row">
										<div class="col-sm-3">
											<label>District</label><span style="color:red;">*</span><span id="districtErrMsgId" style="color:red;"></span>
											<select class="form-control chosen-select" id="districtId">
												<option value="0">select District</option> 
											</select>
										</div>
										<div class="col-sm-3">
											<label>Mandal</label><span style="color:red;">*</span><span id="mandalLoading"></span><span id="mandalErrMsgId"style="color:red;"></span>
											<select class="form-control chosen-select" id="mandalId">
												<option value="0">select Mandal</option> 
											</select>
										</div>
										<div class="col-sm-3">
											<label>Village</label><span style="color:red;">*</span><span id="villageLoading"></span><span id="villageErrMsgId"style="color:red;"></span>
											<select class="form-control chosen-select" id="villageId">
												<option value="0">select Village</option> 
											</select>
										</div>
										<div class="col-sm-3">
											<label>Date</label><span style="color:red;">*</span>
											<div class="input-group inline-block">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text" class="form-control" id="dateRangePickerMGNF"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-3 m_top10">
											<label>Lights Fitted</label><span style="color:red;">*</span><span id="lightFittedErrMsgId"style="color:red;"></span>
											<input type="text" class="form-control" value="" id="lightFitedId"/>
										</div>
										<div class="col-sm-3 m_top10">
											<label>Teams Worked</label><span style="color:red;">*</span><span id="teamWorkErrMsgId"style="color:red;"></span>
											<input type="text" class="form-control" value="" id="teamWorkId"/>
										</div>
										<div class="col-sm-3 m_top30">
											<button type="button" class="btn btn-primary" id="submitId">Submit</button>
											<span id="submitLoading"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row m_top10">
							<div class="col-sm-3 pull-right">
								<div class="input-group inline-block">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
									</span>
									<input type="text" class="form-control" id="dateRangePickerRanges"/>
								</div>
							</div>
						</div>
						<div class="pad_border m_top10" style="border:none;">
							<div class="row">
								<div class="col-sm-8">
									<div class="panel-group" id="accordionCampus" role="tablist" aria-multiselectable="true">
										<div class="panel panel-default panel-black">
											<div class="panel-heading" role="tab" id="headingOneCampus">
												<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordionCampus" href="#collapseOneCampus" aria-expanded="true" aria-controls="collapseOneCampus">
													<h4 class="panel-title">Today's Overview</h4>
												</a>
											</div>
											<div id="collapseOneCampus" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneCampus">
												<div class="panel-body">
													<div id="todayOverViewId"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
										<div class="panel panel-default panel-black">
											<div class="panel-heading" role="tab" id="headingOne">
												<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
													<h4 class="panel-title">Date wise Summary</h4>
												</a>
											</div>
											<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
												<div class="panel-body" style="padding-top:0px;padding-bottom:0px;">
													<div id="dateWiseSummaryDivId"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="pad_border m_top10" style="border:none;">
							<div id="levelWiseLedEntryId"></div>
						</div>
					</div>
				</div>
			</div>
				
		</div>
	</section>
</main>

<div class="modal fade" id="dateModalId" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width:60%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-capital" id="dateModalTitleId"><b></b></h4>
			</div>
			<div class="modal-body">
				<div id="dateModalDivId"></div>
			</div>
		</div>
	</div>
</div>


<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script src="Assests/lightMonitoringDashBoard/ledEntry.js" type="text/javascript"></script>
<script>
	var vendorName = '${sessionScope.User.vendorName}';
	var vendorId = '${sessionScope.User.vendorId}';
</script>
</body>
</html>
