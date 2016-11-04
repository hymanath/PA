<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FIELD MONITORING DASHBOARD</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<style>
#issueStatusTableId_filter{
	float:right !important;
}
#detailsTable_filter{
	display:none !important;
}
#detailsTable_length{
	display:none !important;
}
.activeUlCls li {
    background: #fff none repeat scroll 0 0;
    border: 1px solid #ddd;
    border-radius: 4px;
    cursor: pointer;
    padding: 5px 8px;
    text-transform: capitalize;
}
.activeUlCls li.active {
    background-color: #4a5863;
    color: #fff;
}
</style>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-7 col-xs-12 col-sm-9">
                        	<h4 class="panel-title">FIELD MONITORING DASHBOARD</h4>
                        </div>
						<div class="col-md-2 col-xs-12 col-sm-9">
                        	<input type="radio" name="radio" value="1" checked="true" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> AP</input>
							<input type="radio" name="radio" value="36" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> TS</input>
							 <input type="radio" name="radio" value="0" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> All</input>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-3">
                        	<div class="input-group inputGCustom">
                            	<span class="input-group-addon">
                                	<i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                <input type="text" class="form-control singleDate"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="text-capital panel-title">overview</h4>
                        	<div class="block m_top20">
                            	<div class="row">
                                	<div class="col-md-6 col-xs-12 col-sm-6 b_right1">
                                    	<h4 class="text-capital panel-title"><b>data collectors</b><small>(Today)</small><button class="btn btn-sm btn-success btn-xs pull-right" type="button" onclick="getDataCollectorsPerformanceDetails();">Click to View DC Performances</button></h4>
										<div class="row">
                                        	<div class="col-md-8 col-xs-12 col-sm-10">
                                            	<ul class="dashedB">
                                                   <li>total data collectors<span id="totalDataCollectorsId">0</span></li>
                                                   <li>total registered<span id="totalRegisteredId">0</span></li>
                                                   <li>today - active members<span id="todayActId">0</span></li>
												  <li>today - active since last one hour<span id="lastOneHrId">0</span></li>
												  <li>today - passive since last one hour<span id="passOneHrId">0</span></li>
												  <li>today - not yet started<span id="notYetStartedId">0</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-xs-12 col-sm-6 b_right1" id="statusCountDivId"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row m_top20">
                    	<div class="col-md-4 col-xs-12 col-sm-4">
                        	<h4 class="panel-title text-capital" id="openIssuesCountId">open issues <span></span></h4>
                            <div id="openIssues" style="height:600px"></div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-4">
                        	<h4 class="panel-title text-capital" id="fixedIsuuesCountId">fixed issues <span></span></h4>
                            <div id="fixedIssues" style="height:600px" ></div>
                        </div>
						<div class="col-md-4 col-xs-12 col-sm-4">
                        	<h4 class="panel-title text-capital" id="closedIssuesCountId">Closed issues <span></span></h4>
                            <div id="closedIssues" style="height:600px"></div>
                        </div>
					</div>
                </div>
            </div>
            <div class="row m_top20">
				<div class="col-md-12 col-xs-12 col-sm-12">
                    <div class="block" id="dtatusDivId" style="display:none;">
						<h3 class="panel-title text-capital" id="issueTypeHeadingId"></h3>
						<div id="statusWiseDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
						<div id="statusWiseDetailsDivId"></div>
                    </div>
					<div class="block table-responsive" id="dataCollectorsDiv" style="display:none;">
						<div class="col-md-4 col-xs-12 col-sm-6" style="margin-top: 20px; font-weight: bold;">
							<h4 class="text-capital" id="totalDataCollectorsId" style="margin-top: 0px; font-weight: bold;">total data collectors <span></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6">
							<div  id="districtDiv" style="dispaly:none;">
								<label>Select District</label><span style="color:red"> *</span>
								<select class="select" id="districtId" onchange="getDataCollectorsPerformanceDetails();">
									<option value="0">Select District</option>
								</select>
							</div>
						</div>
						<div class="col-md-5 col-xs-12 col-sm-6" style="margin-top: 25px;" id="districtDiv" style="dispaly:none;">
							<ul class="activeUlCls list-inline pull-right">
								<li class="completedRegistrationsSorting active" attr_value="All">
									All</li>
								<li class="completedRegistrationsSorting" attr_value="verygood">&nbsp;Very Good</li>
								<li class="completedRegistrationsSorting" attr_value="good">&nbsp;Good</li>
								<li class="completedRegistrationsSorting" attr_value="poor">&nbsp;Poor</li>
								<li class="completedRegistrationsSorting" attr_value="verypoor">&nbsp;Very Poor</li>
								<li class="completedRegistrationsSorting" attr_value="notstarted">&nbsp;Not At Started</li>
								
							</ul>
						</div>
						<div id="dataCollectorsImgId" class="col-md-12 col-xs-12 col-sm-12" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
						<div id="dataCollectorsDivId" class="col-md-12 col-xs-12 col-sm-12"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="issuesModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>User ID - <span class="modalCadreUserName"></span></p>
        <p><i><span class="tabUserMblDetailsId"></span></i></p>
      </div>
      <div class="modal-body">
        
		<div class="row">
			<div class="col-md-10 col-xs-12 col-sm-10">
				<div class="block bg_F4 pad_20">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">total issues - <span class="issueTypeCls text-info" attr_val="0" id="totalIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">open issues - <span class="issueTypeCls text-info" attr_val="1" id="openIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">fixed issues - <span class="issueTypeCls text-info" attr_val="2" id="fixedIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">closed issues - <span class="issueTypeCls text-info" attr_val="3" id="closedIssuesId" style="cursor:pointer;"></span></h4>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-xs-12 col-sm-2">
				<button class="btn btn-success text-capitalize btn-lg btn-block m_top10" style="margin-top: 0px ! important; padding-top: 17px; padding-bottom: 17px;border-radius:3px" id="addNewIssueId">Add New Issue</button>
			</div>
			
		</div>
       
        <div class="block m_top20" id="issueTypeDivId" style="display:none;">
		<div id="submitButId"></div>
        	<div class="row">
            	<div class="col-md-3 col-xs-12 col-sm-4">
                	<label>Select IssueType</label><span style="color:red"> *</span>
                    <select class="select" id="issueTypeId">
                    </select>
                </div>
                <div class="col-md-7 col-xs-12 col-sm-8">
                	<label>Issue Description</label><span style="color:red"> *</span>
                    <input type="text" class="form-control" id="descriptionId"/>
                </div>
				<!--<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select Constituency</label><span style="color:red"> *</span>
                    <select class="select" id="issueConstituencyId">
                    	<option value="0">Select Constituency</option>
                    </select>
                </div>-->
				<div class="col-md-2 col-xs-12 col-sm-4">
					<button type="button" class="btn btn-success text-capital m_top25" id="submitId">Submit</button>
					<span id="savingDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
				</div>
				
            </div>
        </div>
        <div id="issueDivId">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
 </div><!-- /.modal -->
   <div class="modal fade" id="issueTrackingModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>User ID - <span class="modalCadreUserName"></span></p>
        <p><i><span class="tabUserMblDetailsId"></span></i></p>
      </div>
      <div class="modal-body">
		<div id="issueTrackingImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
        <div id="issueTrackingBodyId"></div>
      </div>
	
      <div class="modal-footer">
        <button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<input type="hidden" id="hiddenCadreSurveyUserId"></input>
<input type="hidden" id="hiddenTabUserInfoId"></input>
<input type="hidden" id="hiddenVendorId"></input>
<input type="hidden" id="hiddenConstituencyId"></input>
<input type="hidden" id="hiddenIssueStatusId"></input>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/FieldMonitoring/fieldMonitoringDashboard.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).on("click",".activeUlCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
$(document).on("click",".issuesBtn",function(){
	$("#issuesModal").modal('show');
});
$(".singleDate").daterangepicker({
	opens:'left',
	startDate : '11/01/2016',
	endDate:moment(),
	ranges: {
	   'Till Now': ['11/01/2016', moment()],
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});
$('.select').chosen({width:'100%'});

 onLoadCalls();

$(document).on("click",".applyBtn",function(){
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
		getOverAllDataCollectorsCounts(stateId);
		getIssueStatusWiseCounts(stateId);
		getIssueTypeWiseCounts(stateId);
    $("#dtatusDivId").hide();
});

$(document).on("click",".ranges li",function(){
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
		getOverAllDataCollectorsCounts(stateId);
		getIssueStatusWiseCounts(stateId);
		getIssueTypeWiseCounts(stateId);
		getCadreRegIssueType();
		$("#dtatusDivId").hide();
	});
	
$(document).on("click",".modalCloseCls",function(){
	setTimeout(function(){
		$('body').addClass("modal-open");
	}, 500);
});
$("#cadreRegliId").hide();
getCadreRegIssueType();

function stateWisePopulateData(state)
{
	getOverAllDataCollectorsCounts(state);
	getIssueStatusWiseCounts(state);
	getIssueTypeWiseCounts(state); 
    $("#dtatusDivId").hide();	
	$("#dataCollectorsDivId").html('');
	$("#statusWiseDetailsDivId").html('');
	$("#issueTypeHeadingId").html("");
}

</script>
</body>
</html>