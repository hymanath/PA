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
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/> 
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
            	<h3 id="totalHeadingId"><span class="pull-right"><i class="glyphicon glyphicon-remove"></i></span></h3>
				<p class="m_top10 headingCls" style="display:none;">Logged In FieldUsers <span class="issuePending tableInfo pull-right">Passive User</span><span class="issueCmpltd tableInfo pull-right">Active User</span></p>
                <div class="table-responsive m_top10">
				 <div id="userWiseRegDivId"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="issuesDataMonitroingDashboardModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p id="userId"></p>
        <p id="userDescriptionId"></p>
      </div>
      <div class="modal-body">
        <div>
          <!-- Nav tabs -->
          <ul class="nav nav-tabs" id="issuesDataMonitroing" role="tablist">
            <li role="presentation" class="active text-capital"><a href="#self" aria-controls="self" role="tab" data-toggle="tab">voter with self photo</a></li>
            <li role="presentation" class="text-capital"><a href="#relative" aria-controls="relative" role="tab" data-toggle="tab">registered with relative voter id</a></li>
         </ul>
          <!-- Tab panes -->
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane activeCls active" id="self">
			  <div id="selfTblDivId"></div>
			  <div id="selfPaginationId"></div>
            </div>
	        <div role="tabpanel" class="tab-pane relativeCls" id="relative">
			  <div id="relativeDivId"></div>
			  <div id="relativePaginationId"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
      	<p><small>For Bulk Update</small></p>
        <button class="btn btn-success">Approve</button>
        <button class="btn btn-danger">Reject</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="rejectedModalId" role="dialog">
    <div class="modal-dialog" style="height:60px;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body" style="padding-bottom: 98px; padding-right: 0px; padding-top: 7px;">
		 <div class="row">
		    <div class="col-md-4">
			    <span style="color:red;" class="reasonErrorCls"></span>
			   <select class="form-control" id="rsnSlctBxId">
			   <option value="0">Select Reason</option>
			   <option value="1">Image Not Matched</option>
			   <option value="2">Improper Image</option>
			   <option value="3">No Image</option>
		       </select>
		   </div>
		   <div class="col-md-2">
		      <button class="btn btn-success" id="submitBtnReasonId">Submit</button>
           </div>
		 </div>
	    <!--<div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>-->
      </div>
     </div>
  </div>
 </div>
<div id="confirmModalId" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" style="top:70px">
      <div class="modal-dialog modal-sm" role="document" style="width: 30%;">
      <div class="modal-content">
        <div class="modal-body" style="border: 3px solid #000;">
          <!--<div  style="text-align: center;"><img src="images/groupIcon.png" style="width: 100px;"/></div>-->
          <h4 style="text-align: center;">Are you sure want to approved.</h4> 
          <div style="text-align: center;">
            <button type="button" id="groupingApprovedYes" class="groupingConfirmCls btn btn-success " style="width: 27%; border-radius: 5px; height: 40px;">Yes</button>
            <button type="button" id="groupingApprovedNo"  class="groupingConfirmCls btn btn-danger " data-dismiss="modal"style="width: 27%; border-radius: 5px; height: 40px;" >No</button>
          </div>
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
<script src="js/dataMonitoring/dataMonitoringDashboard.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
<script type="text/javascript">
$(document).on("click",".issuesBtn",function(){
	$("#issuesModal").modal('show');
});
$(".multiDateRangePicker").daterangepicker({
		opens: 'left',
	 	locale: {
		  format: 'MM/DD/YYYY'
		}		
});
getDataMonitoringOverViewDetails();
</script>
</body>
</html>
