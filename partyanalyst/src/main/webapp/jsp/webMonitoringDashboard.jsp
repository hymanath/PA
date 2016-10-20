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
					<div class="" >
								<div  id="cadreSearchDtls" ></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
        	<h4 class="panel-title text-capital">district wise detailed overview</h4>
            <div class="table-responsive m_top20">
            	<table class="table table-condensed b_1">
                	<thead>
                    	<th>District Name</th>
                        <th>Registered</th>
                        <th>Data Verified</th>
                        <th>Data Verification Pending</th>
                        <th>Open Issues</th>
                        <th>Closed Issues</th>
                        <th>Fixed Issues</th>
                    </thead>
                    <tbody>
                    	<tr>
                        	<td>Srikakulam</td>
                            <td>5000</td>
                            <td>2000</td>
                            <td>1000</td>
                            <td>5000</td>
                            <td>2000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                        	<td>Srikakulam</td>
                            <td>5000</td>
                            <td>2000</td>
                            <td>1000</td>
                            <td>5000</td>
                            <td>2000</td>
                            <td>1000</td>
                        </tr>
                        <tr>
                        	<td colspan="7">
                            	Ananthapur Dist - Constituency Wise Detailed Overview
                            	<table class="table table-condensed b_1">
                                	<thead>
                                    	<th></th>
                                    </thead>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
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
<script type="text/javascript">
$(".singleDate").daterangepicker({
	opens:'left'
});
$(function () {
    $('#cadreSearchDtls').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: null
        },
        subtitle: {
            text: null
        },
        xAxis: {
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: null
            }
        },
		legend:{
			enabled:false
		},
        tooltip: {
			enabled:false,
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tokyo',
            data: [49.9],
            color:'#ddd'

        }, {
            name: 'New York',
            data: [83.6],

        }, {
            name: 'London',
            data: [48.9]

        }, {
            name: 'Berlin',
            data: [42.4]

        }]
    });
	$('#cadreSearchDtls').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: null
        },
        subtitle: {
            text: null
        },
        xAxis: {
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
			labels:{
				enabled:false
			},
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: null
            },
			labels:{
				enabled:false
			}
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
				legend:{
					enabled:false
				}
            },
			
        },
        series: [{
            name: 'Tokyo',
            data: [49.9],
            color:'#ddd'

        }, {
            name: 'New York',
            data: [83.6],

        }, {
            name: 'London',
            data: [48.9]

        }, {
            name: 'Berlin',
            data: [42.4]

        }]
    });
});
 onLoadCalls();
</script>
</body>
</html>
