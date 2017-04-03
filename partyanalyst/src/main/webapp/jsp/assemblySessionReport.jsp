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
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/>
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
				<span id="errElecMsgId" style="color:red;"></span>
					<div class="row">
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Election Year</label>
							<select id="electionYear" class="chosen-select" onchange="getSessionYears(1);">
								<option value="0"> Select Election Year</option>
							</select>
							<!--<div id="electionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Session Year</label>
							<select id="sessionYear" class="chosen-select" onchange="getAllSessions(1);">
								<option value="0"> Select Session Year</option>
							</select>
							<span ><img src="images/ajaxImg2.gif" style="width:20px;display:none;" id="procesingImg1"></span>
							<!--<div id="sessionYear"></div>-->
						</div>
						<div class="col-md-2 col-xs-12 col-sm-6">
							<label>Assembly Session</label>
							<select id="assemblySession" class="chosen-select" onchange="getDates();">
								<option value="0"> Select Assembly Session</option>
							</select>
							<span ><img src="images/ajaxImg2.gif" style="width:20px;display:none;" id="procesingImg2"></span>
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
		<div class="col-md-12 col-xs-12 col-sm-12" style="display:none;" id="assmblySessionBlock">
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
									<select id="electionYearId" class="chosen-select" onchange="getSessionYears(2);">
										<option value="0"> Select Election Year</option>
									</select>
									<!--<select class="chosen-select">
										<option value="1">2014-2019</option>
									</select>-->
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6">
									<label>Session Year</label>
									<select id="sessionYearId" class="chosen-select" onchange="getAllSessions(2);">
										<option value="0"> Select Session Year</option>
									</select>
									<span ><img src="images/ajaxImg2.gif" style="width:20px;display:none;" id="procesingImg3"></span>
									<!--<select class="chosen-select">
										<option value="2017">2017-2018</option>
									</select>-->
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6">
									<label>Assembly Session</label>
									<select id="assemblySessionId" class="chosen-select" onChange="getDatesForSaving();">
										<option value="0"> Select Assembly Session</option>
									</select>
									<span ><img src="images/ajaxImg2.gif" style="width:20px;display:none;" id="procesingImg4"></span>
									<!--<select class="chosen-select" >
										<option value="21">Budget Session</option>
										<option value="22">Winter Session</option>
										<option value="23">Summer Session</option>
									</select>-->
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6">
									<label>Date Range</label>
									<select id="UpdateStartdateRange" class="chosen-select">
										<option value="0"> Select Session Dates</option>
									</select>
									<!--<div class="input-group">
										<input class="form-control" type="text" id="UpdateStartdateRange"/>
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
									</div>-->
								</div>
								<!--<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
									<button type="button" class="btn btn-info addObserversCls" style="border-radius: 16px;background:#0b95de;">Add Observer</button>
								</div>-->
							</div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
						<form name="submitAssemblySessionCanScore" id="submitApplication"  method="post">
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
										<select class="chosen-select partyCls partyCls0"  onchange="getCandidates('memberNameValApp0','partyCls');" >
											<!--<option value="1">Party</option>
											<option value="2">YSRC</option>-->
										</select>
									</div>
									<div class="col-md-10 col-xs-12 col-sm-12 m_top10">
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label class="f_10">Member&nbsp;Name:</label>
											<span class="glyphicon glyphicon-plus pull-right addMemberDetailsCls" id="addMemberDetailsId" style="margin-top: -10px;cursor:pointer;"></span>
											<select class="chosen-select memberNameCls memberNameValApp0" id="memberNameId" name="assemblySessionReportVO.membersList[0].memberId" attr_no="0">
												<option value="0">All</option>
												<!--<option value="1">Parthipati PullaRao</option>
												<option value="2">Parthipati PullaRao</option>-->
											</select>
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Subject:</label>
											<input type="text"  class="form-control subjectCls" id="subjectId0" name="assemblySessionReportVO.membersList[0].scalesList[0].score" attr_no="0">
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Presentation:</label>
											<input type="text"  class="form-control presentationCls" id="presentationId0"  name="assemblySessionReportVO.membersList[0].scalesList[1].score" attr_no="0">
										</div>
										<div class="col-md-1 col-xs-12 col-sm-6">
											<label class="f_10">Counter:</label>
											<input type="text"  class="form-control counterAttackCls" id="counterAttackId0"  name="assemblySessionReportVO.membersList[0].scalesList[2].score" attr_no="0">
										</div>
										<div class="col-md-2 col-xs-12 col-sm-6">
											<label class="f_10">Body&nbsp;Language:</label>
											<input type="text"  class="form-control bodyLanguageCls" id="bodyLanguageId0" name="assemblySessionReportVO.membersList[0].scalesList[3].score" attr_no="0">
										</div>
										<!--<div class="col-md-4 col-xs-12 col-sm-6">
											<label class="f_10">Summary:</label>
											<input type="text" class="form-control summaryCls" id="summaryId0" placeholder="Enter Summary" attr_count="0">
										</div>-->
										<div id="updatingClonedElements" class="col-md-12 col-xs-12 col-sm-12"></div>
										<div id="" class="col-md-12 col-xs-12 col-sm-12 totalIndividualClonedElements"></div>
									</div>
								</div>
								<input type="hidden"  name="assemblySessionReportVO.membersList[0].adminHouseSessionDayId" id="adminHouseSessionDayId"/>
								<div id="totalUpdatingClonedElements" class="col-md-12 col-xs-12 col-sm-12"></div>
								<button type="button" class="btn btn-info  pull-right m_top10" id="addTotalOneMoreBlockId" style="border-radius: 16px;;background:#0b95de">Add More</button>
							</div>
							<div id="errMsg" ></div>
							<button type="button" class="btn btn-info  " id="submitBtnId" style="border-radius: 16px;;background:#0b95de" onclick="savingApplication();">SUBMIT</button>
							</form>
						</div>
						
				</div>
			</div>
		</div>
	</div>
</div>
<!-- PopUp -->
<div class="modal fade" id="memberDetailsModalDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close " data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Party Wise Member Details</h4>
      </div>
	  <div class="row">
	  <div class="col-md-2 col-xs-12 col-sm-6" style="margin-left:942px;display:none;" id="partyListDivId">
				<label>Select Party:</label>
				<select id="partyId" class="chosen-select" onChange="updateMemberDetials(this.value);">
					<option value="0">All</option>
					<option value="872">TDP</option>
					<option value="1117">YSRC</option>
					<option value="163">BJP</option>
					<option value="886">TRS</option>
				</select> 
			</div>
		</div>
      <div class="modal-body m_top10" style="padding:0px 15px;">
       <div id="memberDetailsId"></div>
      </div>
    </div>
  </div>
</div>


	<div id="updateAppendHtml" class="m_top10" style="display:none;">
		<div class="row" class="memberRowCls">
		<i class="glyphicon glyphicon-remove  pull-right btn btn-default btn-xs updatingRemoveBtnCls" style="border-radius: 50%;"></i>
			
			<div class="col-md-3 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Member&nbsp;Name:</label>
				<select class="memberNameCls" style="width:100%" attr_no="">
					<option value="0">All</option>
					<!--<option value="1">Parthipati PullaRao</option>
					<option value="2">Parthipati PullaRao</option>-->
				</select>
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Subject:</label>
				<input type="text" class="form-control subjectCls"  attr_no="">
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Presentation:</label>
				<input type="text" class="form-control presentationCls" attr_no="" >
			</div>
			<div class="col-md-1 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Counter:</label>
				<input type="text" class="form-control counterAttackCls" attr_no="">
			</div>
			<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
				<label class="f_10">Body&nbsp;Language:</label>
				<input type="text" class="form-control bodyLanguageCls" attr_no="">
			</div>
			<!--<div class="col-md-4 col-xs-12 col-sm-6 ">
				<label class="f_10">Summary:</label>
				<input type="text" class="form-control summaryCls" placeholder="Enter Summary">
			</div>-->
		</div>
	</div>
		
	<div id="totalUpdateAppendHtml" class="m_top10" style="display:none;">
		<div class="row">
			<hr style="border-width: 2px 0px 0px; margin-top: 15px;"/>
			<i class="glyphicon glyphicon-remove  pull-right btn btn-default btn-xs totalUpdatingRemoveBtnCls" style="border-radius: 50%;"></i>
			<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
				<label>Party</label>
				<select class="partyCls" style="width:100%">
					
					<!--<option value="1">Party</option>
					<option value="2">YSRC</option>-->
				</select>
			</div>
			<div class="col-md-10 col-xs-12 col-sm-12">
				<div class="col-md-3 col-xs-12 col-sm-6 ">
					<label class="f_10">Member&nbsp;Name:</label>
					<span class="glyphicon glyphicon-plus pull-right addMemberDetailsCls" id="" style="margin-top: -10px;cursor:pointer;"></span>
					<select class=" memberNameCls" style="width:100%" attr_no="">
						<option value="0">All</option>
						<!--<option value="1">Parthipati PullaRao</option>
						<option value="2">Parthipati PullaRao</option>-->
					</select>
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Subject:</label>
					<input type="text" class="form-control subjectCls" attr_no="" >
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Presentation:</label>
					<input type="text" class="form-control presentationCls" attr_no="">
				</div>
				<div class="col-md-1 col-xs-12 col-sm-6 ">
					<label class="f_10">Counter:</label>
					<input type="text" class="form-control counterAttackCls" attr_no="">
				</div>
				<div class="col-md-2 col-xs-12 col-sm-6 ">
					<label class="f_10">Body&nbsp;Language:</label>
					<input type="text" class="form-control bodyLanguageCls" attr_no="">
				</div>
				<!--<div class="col-md-4 col-xs-12 col-sm-6 ">
					<label class="f_10">Summary:</label>
					<input type="text" class="form-control summaryCls" placeholder="Enter Summary">
				</div>-->
				<div id="" class="col-md-12 col-xs-12 col-sm-12 totalIndividualClonedElements"></div>
			</div>
		</div>
	</div>

<input type="hidden" id="sessionDayId"></input>

<script src="newCoreDashBoard/js/jquery-1.11.3" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script src="debate/js/assemblySessionReport.js" type="text/javascript"></script>
<script type="text/javascript">
	globalOnLoadCalls();
	onLoadInitialisations();
	$(".chosen-select").chosen({width:'100%'});
</script>
</body>
</html>