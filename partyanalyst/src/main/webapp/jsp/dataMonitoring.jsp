<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Data Monitoring</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>     
</head> 
<body>
<div class="container">  
	<div class="row">     
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select State</label>
                        <select class="select" id="stateId">
							<option value="0">Select State</option>
                        	<option value="1">Andhra Pradesh</option>
							<option value="36">Telangana</option>   
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Vendor</label>
                        <select class="select" id="vendorId">
                        	<option>Andhra Pradesh</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select District</label>
                        <select class="select" id="districtId">
                        	<option>Andhra Pradesh</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Constituency</label>  
                        <select class="select" id="constituencyId">
                        	<option>Andhra Pradesh</option>      
                        </select>
                    </div>    
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Date</label>
                        <div class="input-group inputGCustom">
                            <span class="input-group-addon">
	                            <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                             <input type="text" class="form-control datePicker"/>
                        </div>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<button class="btn btn-success m_top25" id="getRegStatusId">submit</button>
                    </div>
                </div>
            </div>
            <div class="block pad_20 m_top20">
            	<div class="row">
                	<div class="col-md-12 col-xs-12 col-sm-12">
                    	<div class="block bg_F4 pad_20">
                        	<div class="row" id="totalCountId"></div>  
                        </div>
                    </div>       
                    <div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="loggedInFieldUsersId"></div>
                </div>
            </div>
        </div> 
    </div>
</div>
<div class="modal fade" id="issuesModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">  
      <div class="modal-header">
			<div id="tabUserId"></div>
      </div>
      <div class="modal-body">
			<div id="cadreValidateId"></div>      
      </div>
      <div class="modal-footer">
      	<p><small>For Bulk Update</small></p>
        <button class="btn btn-success" id="bulkApproveId">Approve</button>
        <button class="btn btn-danger" id="bulkRejectId">Reject</button>
      </div>      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/dataMonitoring/dataMonitoring.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>  
<script type="text/javascript">
	$(".datePicker").daterangepicker();
	$('.select').chosen({width:'100%'});    
</script>
</body>
</html>

      