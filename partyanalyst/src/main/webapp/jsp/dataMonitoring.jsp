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
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/>             
</head> 
<body>  
<div class="container">  
	<div class="row">     
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block">
				<div class="row">
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select District<span style="color:red">*</span>&nbsp;&nbsp;<span style="color:red;" id="errMsgDivId"></span></label>
						<select class="select" id="districtId" onchange="getConstituencies(this.value)">
						    <option value="0">Select District</option>
                        </select>
					</div>
                	<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select Constituency</label><span style="color:red"> *</span>
						<select class="select" id="constituencyId" onchange="getUsers(this.value)">
						    <option value="0">Select Constituency</option>
                        </select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select User</label><span style="color:red"> *</span>
						<select class="select" id="cadreSurveyUserId">
						    <option value="0">Select User</option>
                        </select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select Date</label><span style="color:red"> *</span>
                        <div class="input-group inputGCustom">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control datePicker"/>
						</div>
                    </div>
                </div>
				<div class="row">
					<div class="col-md-11 col-xs-12 col-sm-6">
                    	<button class="btn btn-success  m_top25 text-capital pull-right" id="getRegStatusId">submit</button>
					</div>
				</div>
				<div class="row">
					<div id="verifiactionDivId" style="display:none;">	
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="block bg_F4 pad_20" style="margin-left:20px; margin-right:15px;margin-top:7px;" id="dataverificationId">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">total registrations : <span id="totalRegisteredId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verification pending : <span id="verfPendingId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verified passed : <span id="verPassedId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verifed - junck/rejected : <span id="verRejectedId"></span></h5>
									</div>
								</div>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">		
					<div class="col-md-12 col-xs-12 col-sm-12 m_top20 table-responsive" id="loggedInFieldUsersId"></div></div>
				</div>
           </div>
        </div> 
    </div>
</div>

<div class="modal fade" id="issuesDataMonitroingDashboardModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" id="mainModelCloseId" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p id="userId">User ID - 12345</p>
        <p id="userDescriptionId"><i>Rahul - 9984845464</i></p>
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
        <button class="btn btn-success" id="bulkApproveId">Approve</button>
        <button class="btn btn-danger" id="bulkRejectId">Reject</button><br> 
		<span id="globalErrId" style="color:red;" class="pull-right"></span>  
		<span id="globalSuccId" style="color:green;" class="pull-right"></span> 
      </div>
	   
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="rejectedModalId" role="dialog">
    <div class="modal-dialog" style="height:60px;">
      <div class="modal-content">
        <div class="modal-header">    
          <button type="button" class="close closeButtonCls" data-dismiss="modal">&times;</button>  
          <h4 class="modal-title"></h4>
        </div>  
        <div class="modal-body" style="padding-bottom: 98px; padding-right: 0px; padding-top: 7px;">
		 <div class="row">
		    <div class="col-md-4">
			   <select class="form-control" id="rsnSlctBxId">  
		       </select>
			   <span style="color:red;" class="reasonErrorCls"></span>
		   </div>
		   <div class="col-md-2">
		      <button class="btn btn-success" id="submitBtnReasonId">Submit</button>
			  <span style="color:green;" class="reasonSuccessCls"></span>
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
      <div class="modal-content" style="border: 3px solid #000;">  
		<div class="modal-header">    
          <button type="button" class="close closeButtonCls" data-dismiss="modal">&times;</button>  
          <h4 class="modal-title">Conform Message</h4>            
        </div> 
        <div class="modal-body" >
          <!--<div  style="text-align: center;"><img src="images/groupIcon.png" style="width: 100px;"/></div>-->
          <h4 style="text-align: center;">Are you sure want to approved.</h4> 
          <div style="text-align: center;">
            <button type="button" id="groupingApprovedYes" class="groupingConfirmCls btn btn-success " style="width: 27%; border-radius: 5px; height: 40px;">Yes</button>
            <button type="button" id="groupingApprovedNo"  class="groupingConfirmCls btn btn-danger " data-dismiss="modal"style="width: 27%; border-radius: 5px; height: 40px;" >No</button>
          </div>
		  <span id="successApprovedId" style="color:green;"></span>
		  <span id="errorApprovedId" style="color:red;"></span>  
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
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>  
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
<script type="text/javascript">
	$(".datePicker").daterangepicker();
	$('.select').chosen({width:'100%'});    
$("#cadreRegliId").hide();

getConstituencies(0);
getUsers(0);
getDistricts();
/*$(document).on('click','#imageId',function(){
	var jsObj={}    
	$.ajax({
		type:'GET',
		url: 'getTabUserImagesAction.action',           
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result == 'success')
			alert(123);
	});
});*/
</script>
</body>
</html>