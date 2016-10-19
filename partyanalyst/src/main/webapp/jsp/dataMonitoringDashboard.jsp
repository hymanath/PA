<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>New Workspace</title>
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
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-8 col-xs-12 col-sm-9">
                        	<h4 class="panel-title"></h4>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-3">
                        	<div class="input-group inputGCustom">
                            	<span class="input-group-addon">
                                	<i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                <input type="text" class="form-control multiDateRangePicker"/>
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
                                    	<h4 class="text-capital panel-title"><b>data verification team</b></h4>
									     <div id="dataMonitoringOverviewTblId" class="m_top10"></div>
                                      </div>
                                    <div class="col-md-4 col-xs-12 col-sm-6 b_right1">
                                    	<div id="dataMonitoringOverviewHighChartDivId" style="height:246px;"></div>
                                    </div>
									 <div class="col-md-4 col-xs-12 col-sm-6 b_right1">
                                    	<div id="webTabOnlineHighchartDivId" style="height:246px;"></div>
                                    </div>
							    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="block pad_20 m_top20">
            	<h4 class="panel-title">pending - 4000<span class="pull-right"><i class="glyphicon glyphicon-remove"></i></span></h4>
                <div class="table-responsive">
                	<table class="table b_1 m_top10">
                    	<thead>
                        	<th>User Id</th>
                            <th>Name</th>
                            <th>Contact Numb</th>
                            <th>Completed Registrations</th>
                            <th>Verified - Passed</th>
                            <th>Verified- Junk/Rejected</th>
                            <th>Pending</th>
                            <th></th>
                        </thead>
                        <tr>
                        	<td class="issueCmpltd">13124</td>
                            <td>Ramesj</td>
                            <td>98757585895</td>
                            <td>10</td>
                            <td>4</td>
                            <td>12</td>
                            <td>12</td>
                            <td><button class="btn btn-success">Verify Pending Records</button></td>
                        </tr>
                        <tr>
                        	<td class="issuePending">13124</td>
                            <td>Ramesj</td>
                            <td>98757585895</td>
                            <td>10</td>
                            <td>4</td>
                            <td>12</td>
                            <td>12</td>
                            <td><button class="btn btn-success">Verify Pending Records</button></td>
                        </tr>
                    </table>
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
        <p>User ID - 12345</p>
        <p><i>Rahul - 9984845464</i></p>
      </div>
      <div class="modal-body">
        <div class="block bg_F4 pad_20">
        	<div class="row">
            	<div class="col-md-3 col-xs-12 col-sm-3">
                	<h4 class="text-capital text-center">total issues - 4</h4>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
                	<h4 class="text-capital text-center">open issues - 4</h4>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
                	<h4 class="text-capital text-center">closed issues - 4</h4>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
                	<h4 class="text-capital text-center">fixed issues - 4</h4>
                </div>
            </div>
        </div>
        <div class="block m_top20">
        	<div class="row">
            	<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select IssueType</label>
                    <select>
                    	<option>Manpower issue</option>
                    </select>
                </div>
                <div class="col-md-8 col-xs-12 col-sm-8">
                	<label>Issue Description</label>
                    <input type="text" class="form-control"/>
                </div>
            </div>
        </div>
        <ul class="issuesUl">
        	<li>
            	<h4 class="text-capital">
                	technical issue
                    <button class="btn btn-success editBtn pull-right btn-sm">edit</button>
                </h4>
                <p>Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc </p>
                <p class="m_top10">
                	<span class="text-danger"><i>Issue Status : open</i></span>
                    <span class="pull-right text-muted"><i>Informed Time: 3:49 PM</i></span>
                </p>
            </li>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-success">Submit</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/dataMonitoring/dataMonitoringDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).on("click",".issuesBtn",function(){
	$("#issuesModal").modal('show');
});
$(".multiDateRangePicker").daterangepicker({
		opens: 'left',
	 	locale: {
		  format: 'DD/MM/YYYY'
		}		
});
 getDataMonitoringOverViewDetails();
$('#openIssues').highcharts({
	chart: {
		type: 'pie',
		options3d: {
			enabled: true,
			alpha: 45
		}
	},
	title: {
		text: null
	},
	subtitle: {
		text: null
	},
	plotOptions: {
		pie: {
			innerSize: 100,
			depth: 45
		}
	},
	series: [{
		name: 'Open Issues',
		data: [
			['leader issue', 8],
			['man power issue', 3],
			['technical issue', 1],
			['smart device issue', 6],
			['registration center', 8],
			['internet issue', 4],
			['apk issue', 4]
		]
	}]
});
$('#fixedIssues').highcharts({
	chart: {
		type: 'pie',
		options3d: {
			enabled: true,
			alpha: 45
		}
	},
	title: {
		text: null
	},
	subtitle: {
		text: null
	},
	plotOptions: {
		pie: {
			innerSize: 100,
			depth: 45
		}
	},
	series: [{
		name: 'Open Issues',
		data: [
			['leader issue', 8],
			['man power issue', 3],
			['technical issue', 1],
			['smart device issue', 6],
			['registration center', 8],
			['internet issue', 4],
			['apk issue', 4]
		]
	}]
});
</script>
</body>
</html>
