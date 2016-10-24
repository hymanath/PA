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
</style>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-9 col-xs-12 col-sm-9">
                        	<h4 class="panel-title">FIELD MONITORING DASHBOARD</h4>
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
                                    	<h4 class="text-capital panel-title"><b>data collectors</b></h4>
                                        <div class="row">
                                        	<div class="col-md-8 col-xs-12 col-sm-10">
                                            	<ul class="dashedB">
                                                    <li>total data collectors<span id="totalDataCollectorsId">0</span></li>
                                                    <li>active<span id="activeDataCollectorsId">0</span></li>
                                                    <li>passive<span id="passiveDataCollectorsId">0</span></li>
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
                    	<div class="col-md-6 col-xs-12 col-sm-6">
                        	<h4 class="panel-title text-capital">open issues</h4>
                            <div id="openIssues" class="m_top20"></div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                        	<h4 class="panel-title text-capital">fixed issues</h4>
                            <div id="fixedIssues" class="m_top20"></div>
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
            	<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select IssueType</label><span style="color:red"> *</span>
                    <select class="select" id="issueTypeId">
                    </select>
                </div>
                <div class="col-md-8 col-xs-12 col-sm-8">
                	<label>Issue Description</label><span style="color:red"> *</span>
                    <input type="text" class="form-control" id="descriptionId"/>
                </div>
				<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select Constituency</label><span style="color:red"> *</span>
                    <select class="select" id="issueConstituencyId">
                    	<option value="0">Select Constituency</option>
                    </select>
                </div>
				<div class="col-md-4 col-xs-12 col-sm-4">
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
$(document).on("click",".issuesBtn",function(){
	$("#issuesModal").modal('show');
});
$(".singleDate").daterangepicker({
	opens:'left',
	ranges: {
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
	onLoadCalls();
    $("#dtatusDivId").hide();
});

$(document).on("click",".ranges li",function(){
		getOverAllDataCollectorsCounts();
		getIssueStatusWiseCounts();
		getIssueTypeWiseCounts();
		getCadreRegIssueType();
	});

getCadreRegIssueType();
</script>
</body>
</html>
