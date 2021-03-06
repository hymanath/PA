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
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<style type="text/css">
.pad_0
{
	padding:0px;
}
.arrowBox
{
	width:300px;
	display:none;
	position:absolute;
	top:-50px;
	background-color:#fff;
	padding:10px;
	z-index:99
}
.tableModalCls .completed
{
	background-color:#82B0E6;
	position:relative;
}
.tableModalCls .active
{
	background-color:#E3C05E !important;
}
.arrowOpen
{
	cursor:pointer;
}
.arrowOpen:hover .arrowBox
{
	display:block;
}
.table , .panel
{
	margin-bottom:0px !important;
}
#detailsTable_filter{
	float:right !important;
}
</style>
<div class="container m_top20">
  <h4> FIELD MONITORING </h4>
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block bg_ff">
				<div id="errorDivId"></div>
				<div class="row">
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select District</label><span style="color:red"> *</span>
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
						<select class="select" id="userId">
						    <option value="0">Select User</option>
                        </select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>Select Date</label><span style="color:red"> *</span>
                        <div class="input-group inputGCustom">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control singleDate" disabled/>
						</div>
                    </div>
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<button class="btn btn-success m_top25 text-capital" id="getDetails">submit</button>
					</div>
                </div>
				<!--<div class="row">
                	<div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select State</label><span style="color:red"> *</span>
						<span id="stateDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                        <select class="select" id="stateId">
						    <option value="0">Select State</option>
                        	<option value="1">Andhra Pradesh</option>
							<option value="36">Telangana</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Vendor</label><span style="color:red"> *</span>
						<span id="vendorDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                        <select class="select" id="vendorId">
                        	<option value="0">Select Vendor</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select District</label>
						<span id="districtDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                        <select class="select" id="districtId">
                        	<option value="0">Select District</option>
                        </select>
                    </div>
                    <div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Constituency</label>
						<span id="constituencyDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                        <select class="select" id="constituencyId">
                        	<option value="0">Select Constituency</option>
                        </select>
                    </div>
					<div class="col-md-4 col-xs-12 col-sm-6">
                    	<label>Select Date</label><span style="color:red"> *</span>
                        <div class="input-group inputGCustom">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control singleDate"/>
						</div>
                    </div>
					<div class="col-md-4 col-xs-12 col-sm-6">
                    	<button class="btn btn-success m_top25 text-capital" id="getDetails">submit</button>
					</div>
					
                </div>-->
            </div>
            <div class="block pad_20 m_top20" id="dataCollectorsDiv" style="display:none;">
            	<div class="row">
                	<div class="col-md-12 col-xs-12 col-sm-12">
                    	<div class="block bg_F4 pad_20" id="dataCollectionsId">
                        	<div class="row">
                            	<div class="col-md-4 col-xs-12 col-sm-3">
                                	<h5 class="text-capital">total data collectors : <span id="totalDataCollectorsId"></span></h5>
                                </div>
                                <div class="col-md-4 col-xs-12 col-sm-3">
                                	<h5 class="text-capital">total registered : <span id="totalRegId"></span></h5>
                                </div>
                                <div class="col-md-4 col-xs-12 col-sm-3">
                                	<h5 class="text-capital">today - active members : <span id="todayActMebrId"></span></h5>
                                </div>
								<div class="col-md-4 col-xs-12 col-sm-3" style="margin-top:20px;">
                                	<h5 class="text-capital">today - active since last one hour : <span id="lastOneHrActId"></span></h5>
                                </div>
								<div class="col-md-4 col-xs-12 col-sm-3" style="margin-top:20px;">
                                	<h5 class="text-capital">today - passive since last one hour : <span id="passOneHrId"></span></h5>
                                </div>
								<div class="col-md-4 col-xs-12 col-sm-3" style="margin-top:20px;">
                                	<h5 class="text-capital">today - not yet started : <span id="todayNotYetStrtId"></span></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-xs-12 col-sm-12 m_top20">
						<div id="tabUserDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
                    	<div class="table-responsive" id="tabUserDetailsDivId"></div>
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
        <button type="button" class="close closeIconCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
		  <div id="submitButId"></div>
        	<div class="row" id="showIssueTypeDivId">
            	<div class="col-md-3 col-xs-12 col-sm-4">
                	<label>Select IssueType</label>
                    <select class="select" id="issueTypeId" onchange="getIssues();">
                    </select>
                </div>
                <div class="col-md-7 col-xs-12 col-sm-8">
                	<label>Issue Description</label>
                    <input type="text" class="form-control" id="descriptionId"/>
                </div>
				<div id="leaderIssueDivId" style="display:none;">
					<div class="col-md-3 col-xs-12 col-sm-4">
						<label>Mandal/Muncipality</label>
						<input type="text" class="form-control" id="mndlOrmunpltyId"/>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-4">
						<label>Name</label>
						<input type="text" class="form-control" id="fmNameId"/>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-4">
						<label>Mobile Number</label>
						<input type="text" class="form-control" id="fmMbNoId"/>
					</div>
				</div>
				<!--<div class="col-md-4 col-xs-12 col-sm-4">
                	<label>Select Constituency</label>
                    <select class="select" id="issueConstituencyId">
                    	<option value="0">Select Constituency</option>
                    </select>
                </div>-->
				<div class="col-md-2 col-xs-12 col-sm-4">
					<button type="button" class="btn btn-success text-capital m_top25" id="submitId">Submit</button>
					<span id="savingDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
				</div>
				
            </div>
        </div>
		<!--<h4>TECHNICAL ISSUE</h4>-->
        <div id="issueDivId"></div>
      </div>
	
      <div class="modal-footer">
        <button type="button" class="btn btn-default closeIconCls" data-dismiss="modal">Close</button>
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

<div class="modal fade" id="userPerformanceModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content" style="width:80%">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>User ID - <span class="PerfmCadreUserName"></span></p>
        <p><i><span class="tabUserPerMblDetailsId"></span></i></p>
      </div>
      <div class="modal-body">
		<div id="userPerformanceImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
        <div id="userPerformanceBodyId"></div>
      </div>
	
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="performanceModalDivId">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close closeIconCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       <h4 class="modal-title">UPDATE USER PERFORMANCE</h4>
      </div>
      <div class="modal-body">
	  <div class="text-danger" id="modalErrDiv"></div>
	  <div class="row">
		<div class="col-md-4 col-xs-12 col-sm-4">
			<label>Category Type</label>
				<select class="select" id="porfmanceListId">
				</select>
		</div>
		  <label>Description:</label><br>
				<textarea class="col-md-7 col-xs-12 col-sm-8" id="commentId" style="padding-left:5px;"></textarea>
		</div>
      </div>
      <div class="modal-footer">
	   <button type="button" class="btn btn-success" style="margin-left:9px;" onclick="saveUserPerformanceDetails();">Save</button>
        <button type="button" class="btn btn-default closeIconCls" data-dismiss="modal">Close</button>
		<div id="savingErrMsgDivId"></div>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="performanceDetailsModalDivId">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="userNameDivId"></h4>
      </div>
      <div class="modal-body">
		
	    <div id="pefromanceTableDivId"></div>
		<div id="performnaceErrDivId"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<input type="hidden" id="hiddenCadreSurveyUserId"></input>
<input type="hidden" id="hiddenTabUserInfoId"></input>
<input type="hidden" id="hiddenIssueStatusId"></input>
<input type="hidden" id="hiddenConstituencyId"></input>
<input type="hidden" id="hiddenUserId"></input>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>
<script src="js/FieldMonitoring/fieldMonitoringNew.js" type="text/javascript"></script>

<script type="text/javascript">
	$(".singleDate").daterangepicker();
	$('.select').chosen({width:'100%'});
	
	onLoadCalls();
	
	$(document).on("click",".modalCloseCls",function(){
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 500);
	});
	
$("#cadreRegliId").hide();
</script>
</body>
</html>
