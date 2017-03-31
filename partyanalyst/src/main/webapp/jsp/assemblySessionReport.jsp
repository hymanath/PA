<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assembly Session</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.expand-icon
{
	cursor:pointer;
	font-size:8px;
	color:#4cae4c;
	border:1px solid #4cae4c;
	border-radius:3px;
	padding:1px;
	margin-right:8px;
}
.f_10{font-size:10px;}
.form-control
{
	border-radius:0px;border-left:0px;
}
</style>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default panelNew">
				<div class="panel-heading">
					<h4 class="panel-title">Assembly Session View</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Election Year</label>
							<select id="electionYear" class="form-control" onchange="getSessionYears();">
								<option value="0"> Select Election Year</option>
							</select>
							<!--<div id="electionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Session Year</label>
							<select id="sessionYear" class="form-control" onchange="getAllSessions();">
								<option value="0"> Select Session Year</option>
							</select>
							<!--<div id="sessionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Assembly Session</label>
							<select id="assemblySession" class="form-control" onchange="getDates();">
								<option value="0"> Select Assembly Session</option>
							</select>
							<!--<div id="assemblySession"></div>-->
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
							<label>Date Range</label>
							<div class="input-group">
								<input class="form-control" type="text" id="dateRange" />
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
							</div>
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
							<button type="button" class="btn btn-success" onClick="getSessionDetails();">VIEW</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default panelNew">
				<div class="panel-heading">
					<h4 class="panel-title">Assembly Session View</h4>
				</div>
				<div class="panel-body pad_0">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="sessionDetails"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default panelNew">
				<div class="panel-heading">
					<h4 class="panel-title">Update Assembly Session Information</h4>
				</div>
				<div class="panel-body pad_0">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="row">
								<div class="col-md-2 col-xs-12 col-sm-6">
									<label>Election Year</label>
									<select class="chosen-select">
										<option value="1">2014-2019</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6">
									<label>Session Year</label>
									<select class="chosen-select">
										<option value="2017">2017-2018</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6">
									<label>Assembly Session</label>
									<select class="chosen-select" >
										<option value="21">Budget Session</option>
										<option value="22">Winter Session</option>
										<option value="23">Summer Session</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6">
									<label>Date Range</label>
									<div class="input-group">
										<input class="form-control" type="text" id="UpdateStartdateRange"/>
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
									</div>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
									<button type="button" class="btn btn-info addObserversCls" style="border-radius: 16px;background:#0b95de;">Add Observer</button>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="row">
								<div id="observerNamesId"></div>
							</div>
							<hr style="margin-left:10px;margin-right:10px;border-width: 2px 0 0;margin-bottom: 0px;"/>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div class="row">
									<h4>Participant Details and performance</h4>
									<hr style="border-width: 2px 0px 0px; margin-top: 15px;"/>
									
									<div class="col-md-2 col-xs-12 col-sm-6">
										<label >Party</label>
										<select class="chosen-select partyCls" id="partyId0" attr_count="0">
											<option value="0">Select Party</option>
											<option value="1">Party</option>
											<option value="2">YSRC</option>
										</select>
									</div>
									<div class="col-md-10 col-xs-12 col-sm-12 m_top10">
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label class="f_10">Member&nbsp;Name:</label>
											<span class="glyphicon glyphicon-plus pull-right " id="addMemberDetailsId" style="margin-top: -10px;cursor:pointer;"></span>
											<select class="chosen-select memberNameCls" id="memberNameId0" attr_count="0">
												<option value="0">Select Member Name</option>
												<option value="1">Parthipati PullaRao</option>
												<option value="2">Parthipati PullaRao</option>
											</select>
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Subject:</label>
											<input type="text" class="form-control subjectCls" id="subjectId0" attr_count="0">
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Presentation:</label>
											<input type="text" class="form-control presentationCls" id="presentationId0" attr_count="0">
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Counter:</label>
											<input type="text" class="form-control counterAttackCls" id="counterAttackId0" attr_count="0">
										</div>
										<div class="col-md-2 col-xs-12 col-sm-6">
											<label class="f_10">Body&nbsp;Language:</label>
											<input type="text" class="form-control bodyLanguageCls" id="bodyLanguageId0" attr_count="0">
										</div>
										<div class="col-md-4 col-xs-12 col-sm-6">
											<label class="f_10">Summary:</label>
											<input type="text" class="form-control summaryCls" id="summaryId0" placeholder="Enter Summary" attr_count="0">
										</div>
										<div id="updatingClonedElements" class="col-md-12 col-xs-12 col-sm-12"></div>
									</div>
								</div>
								<div id="totalUpdatingClonedElements" class="col-md-12 col-xs-12 col-sm-12"></div>
								<button type="button" class="btn btn-info  pull-right m_top10" id="addTotalOneMoreBlockId" style="border-radius: 16px;;background:#0b95de">Add More</button>
							</div>
						</div>
						
				</div>
			</div>
		</div>
	</div>
</div>


	<div id="updateAppendHtml" class="m_top10" style="display:none;">
		<div class="row">
		<i class="glyphicon glyphicon-remove  pull-right btn btn-default btn-xs updatingRemoveBtnCls" style="border-radius: 50%;"></i>
			
			<div class="col-md-3 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Member&nbsp;Name:</label>
				<select class="chosen-select memberNameCls" >
					<option value="0">Select Member Name</option>
					<option value="1">Parthipati PullaRao</option>
					<option value="2">Parthipati PullaRao</option>
				</select>
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Subject:</label>
				<input type="text" class="form-control subjectCls"  >
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Presentation:</label>
				<input type="text" class="form-control presentationCls" >
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Counter:</label>
				<input type="text" class="form-control counterAttackCls">
			</div>
			<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Body&nbsp;Language:</label>
				<input type="text" class="form-control bodyLanguageCls">
			</div>
			<div class="col-md-4 col-xs-12 col-sm-6 ">
				<label class="f_10">Summary:</label>
				<input type="text" class="form-control summaryCls" placeholder="Enter Summary">
			</div>
		</div>
	</div>
		
	<div id="totalUpdateAppendHtml" class="m_top10" style="display:none;">
		<div class="row">
			<hr style="border-width: 2px 0px 0px; margin-top: 15px;"/>
			<i class="glyphicon glyphicon-remove  pull-right btn btn-default btn-xs totalUpdatingRemoveBtnCls" style="border-radius: 50%;"></i>
			<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
				<label >Party</label>
				<select class="chosen-select partyCls">
					<option value="0">Select Party</option>
					<option value="1">Party</option>
					<option value="2">YSRC</option>
				</select>
			</div>
			<div class="col-md-10 col-xs-12 col-sm-12">
				<div class="col-md-3 col-xs-12 col-sm-6 ">
					<label class="f_10">Member&nbsp;Name:</label>
					<span class="glyphicon glyphicon-plus pull-right " id="totalAddMemberDetailscls" style="margin-top: -10px;cursor:pointer;"></span>
					<select class="chosen-select memberNameCls" >
						<option value="0">Select Member Name</option>
						<option value="1">Parthipati PullaRao</option>
						<option value="2">Parthipati PullaRao</option>
					</select>
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Subject:</label>
					<input type="text" class="form-control subjectCls"  >
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Presentation:</label>
					<input type="text" class="form-control presentationCls" >
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Counter:</label>
					<input type="text" class="form-control counterAttackCls">
				</div>
				<div class="col-md-2 col-xs-12 col-sm-6 ">
					<label class="f_10">Body&nbsp;Language:</label>
					<input type="text" class="form-control bodyLanguageCls">
				</div>
				<div class="col-md-4 col-xs-12 col-sm-6 ">
					<label class="f_10">Summary:</label>
					<input type="text" class="form-control summaryCls" placeholder="Enter Summary">
				</div>
				<div id="totalIndividualClonedElements" class="col-md-12 col-xs-12 col-sm-12"></div>
			</div>
		</div>
	</div>
							
<script src="newCoreDashBoard/js/jquery-1.11.3" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="debate/js/assemblySessionReport.js" type="text/javascript"></script>
<script type="text/javascript">
	globalOnLoadCalls();
	onLoadInitialisations();
	$(".chosen-select").chosen({width:'100%'});
</script>
</body>
</html>