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
                        	<div class="row">
                            	<div class="col-md-3 col-xs-12 col-sm-6">
                                	<h4 class="text-capitalize">total registered</h4>
                                    <h2>1000</h2>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-6">
                                	<h4 class="text-capitalize">Verification Pending</h4>
                                    <h2>1000</h2>   
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-6">
                                	<h4 class="text-capitalize">Verified passed</h4>
                                    <h2>1000</h2>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-6">
                                	<h4 class="text-capitalize">Verified - Junk/Rejected</h4>
                                    <h2>1000</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                    	<h4 class="panel-title text-muted">Logged In FieldUsers</h4>
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
                                    <td><button class="btn btn-success issuesBtn">Verify Pending Records</button></td>
                                </tr>
                                <tr>
                                    <td class="issuePending">13124</td>
                                    <td>Ramesj</td>
                                    <td>98757585895</td>
                                    <td>10</td>
                                    <td>4</td>
                                    <td>12</td>
                                    <td>12</td>
                                    <td><button class="btn btn-success issuesBtn">Verify Pending Records</button></td>
                                </tr>
                            </table>
                        </div>
                        <ul class="pagination m_top20">
                            <li>
                              <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                              </a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                              <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                              </a>
                            </li>
                        </ul>
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
        <p>User ID - 12345</p>
        <p><i>Rahul - 9984845464</i></p>
      </div>
      <div class="modal-body">
        <div>
        
          <!-- Nav tabs -->
          <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active text-capital"><a href="#self" aria-controls="self" role="tab" data-toggle="tab">voter with self photo</a></li>
            <li role="presentation" class="text-capital"><a href="#relative" aria-controls="relative" role="tab" data-toggle="tab">registered with relative voter id</a></li>
         </ul>
        
          <!-- Tab panes -->
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="self">
            	<table class="table">
                	<thead class="text-capital">
                    	<th>Voter photo</th>
                        <th>captured photo</th>
                        <th>name</th>
                        <th>mobile number</th>
                        <th>gender</th>
                        <th><input type="checkbox"/></th>
                    </thead>
                    <tbody class="b_1">
                    	<tr>
                        	<td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td>
                            	Rama Krishna
                            </td>
                            <td>
                            	96325784656
                            </td>
                            <td>
                            	Male
                            </td>
                            <td>
                            	<input type="checkbox"/>
                            </td>
                        </tr> 
                        <tr>
                        	<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>
                            <td colspan="3">
                                <select>
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                        	<td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td>
                            	Rama Krishna
                            </td>
                            <td>
                            	96325784656
                            </td>
                            <td>
                            	Male
                            </td>
                            <td>
                            	<input type="checkbox"/>
                            </td>
                        </tr> 
                        <tr>
                        	<td>
                            	<button class="btn btn-success">Approve</button>
                                <button class="btn btn-danger">Reject</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="relative">
            	<table class="table">
                	<thead class="text-capital">
                    	<th>Voter photo</th>
                        <th>captured photo</th>
                        <th>name</th>
                        <th>mobile number</th>
                        <th>gender</th>
                        <th><input type="checkbox"/></th>
                    </thead>
                    <tbody class="b_1">
                    	<tr>
                        	<td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td rowspan="2">
                            	<img src="" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>
                            </td>
                            <td>
                            	Rama Krishna
                            </td>
                            <td>
                            	96325784656
                            </td>
                            <td>
                            	Male
                            </td>
                            <td>
                            	<input type="checkbox"/>
                            </td>
                        </tr> 
                        <tr>
                        	<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>
                            <td colspan="3">
                                <select>
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
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
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/dataMonitoring/dataMonitoring.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).on("click",".issuesBtn",function(){
		$("#issuesModal").modal('show');
	});
	$(".datePicker").daterangepicker();
	$('.select').chosen({width:'100%'});
</script>
</body>
</html>

      