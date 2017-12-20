<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> REPRESENTATION REQUEST ENTRY FORM </title>
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
.error_colorCls{
   color: red;
}
</style>
</head>
<body>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="bg_backGroundMain">
					<div class="container-fluid">
						<div class="row">
							<div class="text-center col-sm-11">
								<img src="Assests/images/Group 2.png" class="" alt="logo">
							</div>
							<div class="col-sm-1 m_top20">
								<i class="glyphicon glyphicon-th menu-cls pull-right" style="top:8px;"></i>
							
								<div class="menu-data-cls">
									<div class="arrow_box_top">
										<div class="row">
											<div style="padding:10px;">
												<div class="col-sm-6">
													<h4><a href="representationRequestEntry">ADD PETITION</a></h4>
												</div>
												<div class="col-sm-6">
													<h4 class=""><a href="representationRequestEntryViewMembers">VIEW PETITION</a></h4>
												</div>
											</div>
										</div>
										<div class="row">
											<div style="padding:10px;">
											<div class="col-sm-12">
												<a class="btn btn-primary btnSearch m_top5 pull-right" href="petitionsLogout" style="display:inline-block" style="cursor:pointer;">LOGOUT</a>
												
											</div>
											</div>
										</div>
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
						<div class="panel-heading">
							<h4 class="panel-title f_22" style="font-weight:normal !important;"> EDIT/UPDATE PETITION DETAILS </h4>
						</div>
						<form action="saveRepresentRequestDetails" id="adminProfileForm" name="adminProfileFormName" enctype="multipart/form-data" method="post">
						<input type="hidden"  name=""  value="100" class="form-control m_top10 height45" id="reffererCandidate0">
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<h3 class="panel-title f_22">REPRESENTEE DETAILS</h3>
									<div class="row m_top20">
										<div class="col-sm-3">
											<h6> ENDORSMENT NO </h6>
											<div class=" inline-block m_top10">
												<input type="text" name="endorsmentNo" readOnly="true" class="form-control" id="endorsmentNo"/>
											</div>
										</div>
										<div class="col-sm-3">
											<h6> ENDORSMENT DATE </h6>
											<div class="input-group inline-block m_top10">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text" name="endorsmentDate" readOnly="true"  class="form-control" id="endorsmentDate"/>
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
												<input type="text"  value="" readOnly="true" id="representationType"  readOnly="true" class="form-control" />
											</div>
										</div>
									</div>
									<div class="row m_top20 loadingCls">
									</div>
									<!--<div class="row m_top20">
										<div class="col-sm-12">
											<div id="SELFDetailsDivId"></div>
										</div>
									</div>
									-->
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

<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
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
<!-- for file uploader -->
<script type="text/javascript" src="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.js"></script>
<script src="Assests/representationRequest/representationRequestEdit.js" type="text/javascript"></script>
</body>
</html>