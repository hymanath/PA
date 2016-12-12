<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cards Print Dashboard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/dropkick.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-xs-12 col-sm-3 col-md-offset-3 col-sm-offset-3 col-xs-offset-0">
				<label class="radio-inline">
					<input type="radio"/>AP
				</label>
				<label class="radio-inline">
					<input type="radio"/>TS
				</label>
				<label class="radio-inline">
					<input type="radio"/>ALL
				</label>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
					<s:select theme="simple" cssClass="select" id="vendorId" list="vendorList" listKey="id" listValue="name"/>
			</div>
			<div class="col-md-3 col-xs-12 col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control singleDate"/>
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title"><b>DISPATCH OF SMART CARD</b></h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital"><b>today(17 Nov 106) complete print overview</b></h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-4">
								<div class="pad_10">
									<p><b>Print Data Ready Constituencies</b></p>
									<h3>100</h3>
									<small>(100000 Records)</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital"><b>district wise contituencies cards print & dispatch status overview</b></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h4>Andhra Pradesh</h4>
								<div id="distWiseCards" style="height: 300px"></div>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<h4>Telangana</h4>
								<div id="distWiseCards1" style="height: 300px"></div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
					<div class="col-md-4 col-xs-12 col-sm-4">
						<table class="table bg_ff tablePrinting">
							<tr>
								<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
							</tr>
							<tr>
								<td>&ge; 1 Day</td>
								<td>04</td>
							</tr>
							<tr>
								<td>&ge; 2 Day</td>
								<td>01</td>
							</tr>
							<tr>
								<td>&ge; 3 Day</td>
								<td>5</td>
							</tr>
							<tr>
								<td>&ge; 5 Day</td>
								<td>10</td>
							</tr>
							<tr>
								<td>> 10 Day</td>
								<td>-</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital">constituencies wise cards print status</h4>
					</div>
					<div class="panel-body" style="padding: 1px;">
						<div class=" bg_EE" style="padding: 10px;">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>print Data Ready</label>
									<select class="select">
										<option>Select</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<table class="table bg_ff">
									<thead class="text-capital">
										<th>constituency name</th>
										<th>print data ready</th>
										<th>cards print status</th>
										<th>QA status</th>
										<th>Dispatched</th>
									</thead>
									<tr>
										<td class="text-capital" colspan="2">cards print delay lag constituencies</td>
									</tr>
									<tr>
										<td>&ge; 1 Day</td>
										<td>04</td>
									</tr>
									<tr>
										<td>&ge; 2 Day</td>
										<td>01</td>
									</tr>
									<tr>
										<td>&ge; 3 Day</td>
										<td>5</td>
									</tr>
									<tr>
										<td>&ge; 5 Day</td>
										<td>10</td>
									</tr>
									<tr>
										<td>> 10 Day</td>
										<td>-</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h4 class="panel-title text-capital">vendor wise print overview</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<table class="table table-bordered" style="border: 0px;">
									<tr>
										<td><h4>Vendor - 1</h4></td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Total Avg Per Day Print Capacity</small>
											<h4>10000</h4>
											<span class="text-success">View Day Wise</span>
										</td>
									</tr>
									<tr>
										<td class="bg_EE pos_relative"><div class="arrowTOp">Today <br/><small>17 Nov 2016</small></div></td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
									</tr>
									<tr>
										<td>Vendor - 1</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Print Data Ready Constituencies</small>
											<h4>10</h4>
										</td>
										<td>
											<small>Total Avg Per Day Print Capacity</small>
											<h4>10000</h4>
											<span class="text-success">View Day Wise</span>
										</td>
									</tr>
									<tr>
										<td class="bg_EE pos_relative"><div class="arrowTOp">Today <br/><small>17 Nov 2016</small></div></td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
										<td class="bg_EE">01</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
$(".singleDate").daterangepicker();
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
	$("#distWiseCards").highcharts({
		colors:['#B091BB','#9CCBCC','#C8CA92','#71F0CC','#FF6F9B'],
        chart: {
            type: 'column'
        },

        title: {
            text: null
        },

        xAxis: {
			 min: 0,
			 gridLineWidth: 0,
			 minorGridLineWidth: 0,
            categories: ['Srikakulam', 'Vijayanagaram', 'East Godavari', 'West Godavari', 'Guntur','Prakasham']
        },

        yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
            min: 0,
            title: {
                text: null
            },
			stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },

        tooltip: {
            formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '<br/>' +
                    'Total: ' + this.point.stackTotal;
            }
        },

        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },

        series: [{
            name: 'PDRC',
            data: [5, 3, 4, 7, 2,1],
            stack: 'PDRC'
        }, {
            name: 'PCC',
            data: [3, 4, 4, 2, 5, 8],
            stack: 'PCC'
        }, {
            name: 'DC',
            data: [2, 5, 6, 2, 1, 4],
            stack: 'DC'
        }, {
            name: 'QAPC',
            data: [3, 0, 4, 4, 3,4],
            stack: 'QAPC'
        }, {
            name: 'QAFC',
            data: [3, 0, 4, 4, 3,7],
            stack: 'QAFC'
        }]
    });
	$("#distWiseCards1").highcharts({
		colors:['#B091BB','#9CCBCC','#C8CA92','#71F0CC','#FF6F9B'],
        chart: {
            type: 'column'
        },

        title: {
            text: null
        },

        xAxis: {
			 min: 0,
			 gridLineWidth: 0,
			 minorGridLineWidth: 0,
            categories: ['Srikakulam', 'Vijayanagaram', 'East Godavari', 'West Godavari', 'Guntur','Prakasham']
        },

        yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
            min: 0,
            title: {
                text: null
            },
			stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },

        tooltip: {
            formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '<br/>' +
                    'Total: ' + this.point.stackTotal;
            }
        },

        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },

        series: [{
            name: 'PDRC',
            data: [5, 3, 4, 7, 2,1],
            stack: 'PDRC'
        }, {
            name: 'PCC',
            data: [3, 4, 4, 2, 5, 8],
            stack: 'PCC'
        }, {
            name: 'DC',
            data: [2, 5, 6, 2, 1, 4],
            stack: 'DC'
        }, {
            name: 'QAPC',
            data: [3, 0, 4, 4, 3,4],
            stack: 'QAPC'
        }, {
            name: 'QAFC',
            data: [3, 0, 4, 4, 3,7],
            stack: 'QAFC'
        }]
    });
</script>
</body>
</html>
