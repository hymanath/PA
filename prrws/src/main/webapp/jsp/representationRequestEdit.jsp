<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> REPRESENTATION REQUEST EDIT FORM </title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<!-- for file uploader -->
<link href="Assests/Plugins/dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="Assests/Plugins/dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
<!-- for file uploader -->
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.css" media="screen" />
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
.error_colorCls,.ErrCls {
   color: red;
}
</style>
</head>
<body>
<header style="box-shadow:none;">
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3">
					<img src="Assests/images/aplogo.png" class="" style="width: 80px; height: 80px; padding: 10px;"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h3 class="text-capital"><span style="color: rgb(255, 255, 255); font-weight: bold; margin-right: 7px;font-size: 20px;">MINISTER</span> <span class="text-color">PETITION MANAGEMENT SYSTEM</span></h3>
					<p style="color: rgb(255, 255, 255);">Information Technology,Panchayathi Raj & </p>
					<p style="color: rgb(255, 255, 255);">Rural Development</p>
				</div>
				<div class="col-sm-5 col-xs-12 pull-right innerPageHeader">
					<i class="glyphicon glyphicon-th menu-cls pull-right" style="position:relative;color:#AA8440"></i>
					<div class="menuCls">
						<div class="arrow_box_top">
							<div class="row">
								<div style="padding:10px;">
									<!--<div class="col-sm-5" id="entryLinkId">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationRequestEntry" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">ADD PETITION</a></h4>
									</div>
									<div class="col-sm-6"  id="viewLinkId">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;" ><a href="representationRequestEntryViewMembers" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">VIEW PETITION</a></h4>
									</div>-->
								</div>
								<div id="menuId"></div>
							</div>
							<!--<div class="row"  id="dashboardLinkId">
								<div style="padding:10px;">
								<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationsDashboard" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">DASHBOARD</a></h4>
									</div>
								</div>
							</div>-->
							<div class="row">
								<div style="padding:10px;">
								<div class="col-sm-12">
									<a class="btn btn-primary btnSearch m_top5 pull-right" href="petitionsLogout" style="display:inline-block" style="cursor:pointer;"><i class="fa fa-sign-out" style="color:#FFBA00;"></i>&nbsp;&nbsp;Sign-out</a>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</nav>
</header>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel-group">
					<div class="panel panel-default panel-lightGreen">
						<div class="panel-heading padTopBottom">
							<h4 class="panel-title f_22" style="font-weight:normal !important;"> EDIT/UPDATE PETITION DETAILS </h4>
						</div>
						<form action="saveRepresentRequestDetails" id="adminProfileForm" name="adminProfileFormName" enctype="multipart/form-data" method="post">
						<input type="hidden"  name=""  value="100" class="form-control m_top10 height45" id="reffererCandidate0">
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<h3 class="panel-title f_22"><span id="petitionViewType"></span> REPRESENTEE DETAILS </h3>
									<div class="row m_top20">
										<div class="col-sm-3" id="endorsementDivId" >
											<h6> ENDORSMENT NO </h6>
											<div class=" inline-block m_top10">
												<input type="text" name="endorsmentNo" readOnly="true" class="form-control" id="endorsmentNo"/>
											</div>
										</div>
										<div class="col-sm-3" id="endorsementDateDivId" >
											<h6> ENDORSMENT DATE </h6>
											<div class="input-group inline-block m_top10">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text" name="endorsmentDate"  readOnly="true"   class="form-control" id="endorsmentDate"/>
											</div>
										</div>
										<div class="col-sm-3">
											<h6>REPRESENTATION DATE </h6>
											<div class="input-group inline-block m_top10">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text" name="representationdate"  readOnly="true" class="form-control" id="representationDate"/>
											</div>
										</div>

										<div class="col-sm-3">
											<h6>REPRESENTATION BY </h6>
											<div class=" inline-block m_top10">
												<input type="text"  value="" readOnly="true" name="representationType" id="representationType"  readOnly="true" class="form-control" />
											</div>
										</div>
									</div>
									<div class="row m_top10">
										<div id="viewCoveringLettersDivId"></div>
									</div>
									<div class="row m_top20 loadingCls">
									</div>
									<div class="row m_top20">
										<div class="col-sm-12">
											<div id="SELFDetailsDivId"></div>
										</div>
									</div>
									<div class="row m_top20">
										<div class="col-sm-12">
											<div id="REPRESENTEEDetailsDivId"></div>
										</div>
									</div>
									
								</div>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<div class="modal fade" id="candidateSearchModelDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="font-weight: bold">Candidate Search</h4> 
			 </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-sm-3">
					<select class="form-control chosen-select" id="designationsId">
						<option value="0">Select Designation</option>
					</select>
					 <div class="m_top10 error_colorCls" id="desiganationIErrDivd"></div>
				</div>
				<div class="col-sm-3" id="districtCandDivId">
					<select class="form-control chosen-select" id="districtCandId">
						<option value="0">Select District</option>
					</select>
				</div>
				<div class="col-sm-3" id="constituencyCandDivId">
					<select class="form-control chosen-select" id="constituencyCanId">
						<option value="0">Select Constituency</option>
					</select>
				</div>
				<div class="col-sm-3">
					<button type="button" class="btn btn-xs btn-success selectionSearchDetailsCls" style="border-radius: 0px; font-weight: bold;">Search</button>
				</div>
			</div>
			<div class="row">
				<div id="candidateDetailsDivId"></div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
		  </div>
	  </div>
	</div>
</div>
<div class="modal fade" id="docsModalDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:60%;margin:auto">
		<div class="modal-content">
		  <div class="modal-header">
				<button type="button" class="close modalCloseCls closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-capital" id="viewDocumentHeading" style="font-weight: bold"></h4> 
		  </div>
		  <div class="modal-body">
			<div class="row">
				<div id="docsViewModalId"></div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default modalCloseCls closeSecondModal" data-dismiss="modal">Close</button>
		  </div>
	   
	  </div>
	</div>
</div>
<div class="modal fade" id="endorseMentModalDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:60%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
		<div class="row">
			<div class="col-sm-12">
				<button type="button" class="close modalCloseCls closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-capital" id="endorseMentHeadingId"></h4> 
			</div>
		</div>		
	  </div>
	<form action="endorsingSubWorksAndAssigningToOfficer" id="endorsingSubWorksId" name="" enctype="multipart/form-data" method="post">
	  <div class="modal-body">
	   <div class="pad_light_yash_bg border_yash border_radius_5" id="totalWorkEditDivId" >
			<div class="row">
				<div class="col-sm-4">
					<h5>TOTAL WORKS : <span id="totalWorksId" class="font_weight">0</span></h5>
				</div>
				<div class="col-sm-4">
					<h5>SELECTED  WORKS : <span id="selectdWorksId" class="font_weight">0</span></h5>
				</div>
				<div class="col-sm-4">
					<h5>NOT SELECTED WORKS : <span id="notSeleWorksId" class="font_weight">0</span></h5>
				</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-4" id="statusChangeDivId">
				<label>Select Status<span style="color:red;">*</span></label>
				<select class="form-control chosen-select" id="statusChangeId">
					<option value="0">Select Status</option>
				</select>
			</div>
		</div>
		 <div class="row m_top10">
			<div class="col-sm-8" id="endorsentDivId" style="display:none;">
				<label>ENDORSMENT NO<span style="color:red;">*</span><span id="WorkEndorsementNoErr"></span></label>
				<input type="text" class="form-control" id="workEndorsmentNo" placeholder="Endorsment NO" name="endorsementNO"/>
			</div>
        </div>	
		<div class="row m_top10">
			<div class="col-sm-4" id="leadDivId" style="display:none;">
				<label>LEAD<span style="color:red;">*</span><span id="leadIdErr"></span></label>
				<select class="form-control chosen-select" id="leadId" name="leadId">
					<option value="1">SELECT LEAD</option>
				</select>
			</div>
			<div class="col-sm-4" id="grantDivId" style="display:none;">
				<label>GRANT UNDER<span  style="color:red;"></span><span id="grantIdErr"></span></label>
				<select class="form-control chosen-select" id="grantId" name="grantId">
					<option value="1">SELECT GRANT UNDER</option>
				</select>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-4" id ="assignDesignationDivId" style="display:none;">
				<label>ASSIGN TO<span id="assignToIdErr"></span></label>
				<select class="form-control chosen-select popUpChangesCls" id="assignToId" name="deptDesigId">
					<option value="0">SELECT DESIGNATION</option>
				</select>
			</div>
			<div class="col-sm-4" id ="assignOfficerDivId" style="display:none;">
				<label>OFFICER NAME<span id="officerIdErr"></span></label>
				<select class="form-control chosen-select" id="officerId" name="deptDesigOffcrId">
					<option value="0">SELECT OFFICER NAME</option>
				</select>
			</div>
		</div>
		<input type="hidden" class="form-control" id="nextStatusId"  name="statusId"/>
		<input type="hidden" class="form-control" id="petitionId"  name="petitionId"/>
		<div class="row m_top10" id="commentsDivId">
			<div class="col-sm-8">
				<label>Comment<span style="color:red;">*</span><span id="remarkIdErr"></span></label>
				<textarea class="form-control" rows="3" id="remarksId" name="remark"></textarea>
			</div>
		</div>
		<div class="row m_top20" id="fileUploadDiv" style="display:none;">
	      <div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;">
	        <h5 class="panel-title"> UPLOAD <span id="letterNameId"></span> LETTER <span id="fileUploadIdErr"></h5>
	        <span id="uploadFile"></span>
	      </div>
    	</div>
		 
		<!--<div class="row m_top20">
			<div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;">
				<button type="button" class="btn btn-success" id="coverLetterId" onclick="generateCoveringLetterForPetition()">Generate Cover Letter</button>
			</div>
		</div>
		<div id="coveringLetterGenerator"></div>-->
		<input type="hidden" id="coverLetterPath" name="coverLetterPath"/>
	  </div>
	  <div class="modal-footer">
		 <button type="button" class="btn btn-default closeSecondModal" data-dismiss="modal">Close</button>
		<button type="button" class="btn btn-success pull-right" id="endorsWorksId" onclick="endorsingSubWorksAndAssigningToOfficer()" ><span id="buttonNameId"></span></button>
	  </div>
	</form>
  </div>
</div>
</div>

<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/login/loginMenu.js"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<!-- for file uploader  -->              
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/jquery.filer.min.js"></script>
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/editUploadFile.js?v=1.0.5"></script>                
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/editUploadFile2.js?v=1.0.5"></script>   
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/singleFileuploadDocuments.js"></script>             
<!-- for file uploader -->
<script type="text/javascript" src="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.js"></script>
<script src="Assests/representationRequest/representationRequestEdit.js" type="text/javascript"></script>
</body>
<script>
var petitionId='${param.petitionId}';
var userId = '${sessionScope.USER.userId}';
$('#petitionId').val(petitionId);
if(userId==25 || userId==26 || userId==27){
	$('#statusChangeDivId').hide();
	$('#dashboardLinkId').hide();
}
</script>
</html>