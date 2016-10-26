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
        	<h4 class="panel-title text-capital">web monitoring dashboard</h4>
            <div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-9 col-xs-12 col-sm-9">
                        	<h4 class="panel-title"></h4>
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
                                    	<h4 class="text-capital panel-title"><b>data collectors</b></h4>
										<ul class="dashedB m_top10">
											<li>total data collectors<span id="totalDataCollectorsId">0</span></li>
											<li>active<span id="activeDataCollectorsId">0</span></li>
											<li>passive<span id="passiveDataCollectorsId">0</span></li>
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
                    	<div class="col-md-6 col-xs-12 col-sm-6">
                        	<h4 class="panel-title text-capital">open issues</h4>
                            <div id="openIssues" class="m_top20"></div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                        	<h4 class="panel-title text-capital">fixed issues</h4>
                            <div id="fixedIssues" class="m_top20"></div>
                        </div>
                    </div>
					<div class="row m_top20">
						<div id="districtWiseNewsIssuesReport" >
						</div>
					</div>
				</div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
        	<h4 class="panel-title text-capital">district wise detailed overview</h4>
            <div class="table-responsive m_top10">
				<div id="districtWiseOverviewDetailsId"></div>
            </div>
        </div>
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
	 onLoadCalls();
});
 
$(document).on("click",".applyBtn",function(){
	getLocationWiseOverAllDetails("state",0,"districtWiseOverviewDetailsId");
});
</script>
</body>
</html>
