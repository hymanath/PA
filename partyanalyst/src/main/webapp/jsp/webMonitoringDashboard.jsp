<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WEB MONITORING DASHBOARD</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="panel-title text-capital"></h4>
            <div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-7 col-xs-12 col-sm-9">
                        	<h4 class="panel-title">WEB MONITORING DASHBOARD</h4>
                        </div>
						<div class="col-md-2 col-xs-12 col-sm-9">
                        	<input type="radio" name="radio" value="1" checked="true" class="stateWiseCls" onclick ="stateWisePopulateWebMonitoringData(this.value);"> AP</input>
							<input type="radio" name="radio" value="36" class="stateWiseCls" onclick ="stateWisePopulateWebMonitoringData(this.value);"> TS</input>
							 <input type="radio" name="radio" value="0" class="stateWiseCls" onclick ="stateWisePopulateWebMonitoringData(this.value);"> All</input>
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
                                	<div class="col-md-4 col-xs-12 col-sm-6 b_right1">
                                    	<h4 class="text-capital panel-title"><b>data collectors</b><small>(Today)</small></h4>
										<ul class="dashedB m_top10">
											<li>total data collectors<span id="totalDataCollectorsId">0</span></li>
											<li>total registered<span id="totalRegisteredId">0</span></li>
											<li>today - active members<span id="todayActMembersId">0</span></li>
											<li>today - active since last one hour<span id="lastHrActiveId">0</span></li>
											<li>today - passive since last one hour<span id="passiveHrId">0</span></li>
											<li>today - not yet started<span id="notYetStartId">0</span></li>
										</ul>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-6 b_right1" id="statusCountDivId">
                                    	<!--<h4 class="text-capital panel-title"><b>field monitoring system</b></h4>
                                        <div class="row">
                                        	<div class="col-md-8 col-xs-12 col-sm-10">
                                            	<ul class="dashedB">
                                                    <li>open issues<span>1000</span></li>
                                                    <li>closed issues<span>4000</span></li>
                                                    <li>fixed issues<span>100</span></li>
                                                </ul>
                                            </div>
                                        </div>-->
                                    </div>
									<div class="col-md-4 col-xs-12 col-sm-6 b_right1">
                                    	<h4 class="text-capital panel-title"><b>data verification team</b></h4>
									     <div id="dataMonitoringOverviewTblId" class="m_top10"></div>
                                      </div>
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
					<div class="row m_top20">
						<div id="districtWiseNewsIssuesReport" >
						</div>
					</div>
				</div>
            </div>
        </div>
        <!--<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
        	<h4 class="panel-title text-capital">district wise detailed overview</h4>
            <div class="table-responsive m_top10">
				<div id="districtWiseOverviewDetailsId"></div>
            </div>
        </div>-->
    </div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/FieldMonitoring/webMonitoringDashboard.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript">
$(".singleDate").daterangepicker({
	opens:'left',
	ranges:{
		'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
});

 onLoadCalls();
 
$(document).on("click",".ranges li",function(){
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
		getOverAllDataCollectorsCounts(stateId)
		getIssueStatusWiseCounts(stateId);
		getIssueTypeWiseCounts(stateId);
		getDataMonitoringOverViewDetails(stateId);
		getDistrictWiseIssueTypesCount(stateId);
		getLocationWiseOverAllDetails("state",stateId,"districtWiseOverviewDetailsId");
});
 
$(document).on("click",".applyBtn",function(){
	var stateId = '';
	$('.stateWiseCls').each(function (index, value){
		stateId = $(":radio:checked").val();
	});
	getLocationWiseOverAllDetails("state",stateId,"districtWiseOverviewDetailsId");
});

$("#cadreRegliId").hide();
function stateWisePopulateWebMonitoringData(state)
{
	getOverAllDataCollectorsCounts(state);
	getIssueStatusWiseCounts(state);
	getIssueTypeWiseCounts(state);
	getDataMonitoringOverViewDetails(state);
	getDistrictWiseIssueTypesCount(state);
	getLocationWiseOverAllDetails("state",state,"districtWiseOverviewDetailsId");
}
</script>
</body>
</html>
