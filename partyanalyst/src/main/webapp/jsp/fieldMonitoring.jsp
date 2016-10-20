<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FIELD MONITORING</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
</head>
<body>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block bg_ff">
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
                        	<option value="0">Select Vendor</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select District</label>
                        <select class="select" id="districtId">
                        	<option value="0">Select District</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Constituency</label>
                        <select class="select" id="constituencyId">
                        	<option value="0">Select Constituency</option>
                        </select>
                    </div>
					<div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Date</label>
                        <div class="input-group inputGCustom">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control singleDate"/>
						</div>
                    </div>
					<div class="col-md-4 col-xs-12 col-sm-6">
                    	<button class="btn btn-success m_top25 text-capital" id="getDetails">submit</button>
						<div id="errorDivId"></div>
                    </div>
					
                </div>
            </div>
            <div class="block pad_20 m_top20" id="dataCollectorsDiv" style="display:none;">
            	<div class="row">
                	<div class="col-md-12 col-xs-12 col-sm-12">
                    	<div class="block bg_F4 pad_20">
                        	<div class="row">
                            	<div class="col-md-5 col-xs-12 col-sm-5">
                                	<h4 class="text-capital">total data collectors - <span id="totalDataCollectorsId"></span></h4>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3">
                                	<h4 class="text-success text-capital">active users - <span id="activeDataCollectorsId"></span></h4>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3">
                                	<h4 class="text-danger text-capital">passive users - <span id="passiveDataCollectorsId"></span></h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
						<div id="tabUserDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
                    	<div id="tabUserDetailsDivId"></div>
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
        <p>User ID - <span class="modalCadreUserName"></span></p>
        <p><i><span class="tabUserMblDetailsId"></span></i></p>
      </div>
      <div class="modal-body">
        
		<div class="row">
			<div class="col-md-10 col-xs-12 col-sm-10">
				<div class="block bg_F4 pad_20">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">total issues - <span class="issueTypeCls text-info" attr_val="0" id="totalIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">open issues - <span class="issueTypeCls text-info" attr_val="1" id="openIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">fixed issues - <span class="issueTypeCls text-info" attr_val="2" id="fixedIssuesId" style="cursor:pointer;"></span></h4>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3">
							<h4 class="text-capital text-center">closed issues - <span class="issueTypeCls text-info" attr_val="3" id="closedIssuesId" style="cursor:pointer;"></span></h4>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-xs-12 col-sm-2">
				<button class="btn btn-success text-capitalize btn-lg btn-block m_top10" style="margin-top: 0px ! important; padding-top: 17px; padding-bottom: 17px;border-radius:3px" id="addNewIssueId">Add New Issue</button>
			</div>
			
		</div>
       
        <div class="block m_top20" id="issueTypeDivId" style="display:none;">
        	<div class="row">
            	<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select IssueType</label>
                    <select class="select" id="issueTypeId">
                    </select>
                </div>
                <div class="col-md-8 col-xs-12 col-sm-8">
                	<label>Issue Description</label>
                    <input type="text" class="form-control" id="descriptionId"/>
                </div>
				<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select Constituency</label>
                    <select class="select" id="issueConstituencyId">
                    	<option value="0">Select Constituency</option>
                    </select>
                </div>
				<div class="col-md-4 col-xs-12 col-sm-4">
					<button type="button" class="btn btn-success text-capital m_top25" id="submitId">Submit</button>
					<span id="savingDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
					<div id="submitButId"></div>
				</div>
				
            </div>
        </div>
		<!--<h4>TECHNICAL ISSUE</h4>-->
        <ul class="issuesUl" id="issueDivId">
        	<!--<li>
            	<h4 class="text-capital">
                	technical issue
                    <button class="btn btn-success editBtn pull-right btn-sm">edit</button>
                </h4>
                <p>Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc Issue Desc </p>
                <p class="m_top10">
                	<span class="text-danger"><i>Issue Status : open</i></span>
                    <span class="pull-right text-muted"><i>Informed Time: 3:49 PM</i></span>
                </p>
            </li>-->
        </ul>
      </div>
	
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-success">Submit</button>-->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="issueTrackingModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>User ID - <span class="modalCadreUserName"></span></p>
        <p><i><span class="tabUserMblDetailsId"></span></i></p>
      </div>
      <div class="modal-body">
		<div id="issueTrackingImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
        <div id="issueTrackingBodyId"></div>
      </div>
	
      <div class="modal-footer">
        <button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<input type="hidden" id="hiddenCadreSurveyUserId"></input>
<input type="hidden" id="hiddenTabUserInfoId"></input>
<input type="hidden" id="hiddenIssueStatusId"></input>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/FieldMonitoring/fieldMonitoring.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>

	<script type="text/javascript">
		
		$(".singleDate").daterangepicker();
		$('.select').chosen({width:'100%'});
		getCadreRegIssueType();
		getCadreRegIssueStatusType();
		
		$(document).on("click",".modalCloseCls",function(){
			setTimeout(function(){
				$('body').addClass("modal-open");
			}, 1000);
		});
		
	</script>
</body>
</html>
