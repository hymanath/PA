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
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select State</label>
                        <select class="select" id="stateId">
						    <option value="0">Select State</option>
                        	<option value="1">Andhra Pradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select Vendor</label>
                        <select class="select" id="vendorId">
                        	<option value="0">Select Vendor</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select District</label>
                        <select class="select" id="districtId">
                        	<option value="0">Select District</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select Constituency</label>
                        <select class="select" id="constituencyId">
                        	<option value="0">Select Constituency</option>
                        </select>
                    </div>
                </div>
            </div>
          </div>
        </div>  
      </div>        
      
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/dataMonitoring/dataMonitoring.js" type="text/javascript"></script>
</body>
</html>
      