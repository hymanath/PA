<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> REPRESENTATION REQUEST ENTRY FORM </title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
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
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
.error_colorCls,.ErrCls{
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
					<p style="color: rgb(255, 255, 255);">Information Technology,Panchayathi Raj,Rural Water Supply & </p>
					<p style="color: rgb(255, 255, 255);">Rural Development</p>
				</div>
				<div class="col-sm-5 col-xs-12 pull-right innerPageHeader">
					<i class="glyphicon glyphicon-th menu-cls pull-right" style="position:relative;color:#AA8440"></i>
					<div class="menuCls">
						<div class="arrow_box_top">
							<div class="row">
								<div style="padding:10px;">
									<!--<div class="col-sm-6">
										<h4><a href="representationRequestEntry">ADD PETITION</a></h4>
									</div>
									<div class="col-sm-6">
										<h4 class=""><a href="representationRequestEntryViewMembers">VIEW PETITION</a></h4>
									</div>-->
								</div>
								
								<div id="menuId"></div>
								
							</div>
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
<%@ page import="com.itgrids.dto.UserVO" %>
<% 
	UserVO userVo=(UserVO)session.getAttribute("User");
	out.println("<h4 class='pull-right' style='margin:6px 10px; color:green;'>&nbsp;&nbsp; "+userVo.getUserName()+"</h4>"); 
%>
</header>
<main>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel-group">
					<div class="panel panel-default panel-lightGreen">
						<div class="panel-heading padTopBottom">
							<h4 class="panel-title f_22" style="font-weight:normal !important;">REPRESENTATION REQUEST</h4>
						</div>
						<form action="saveRepresentRequestDetails" id="adminProfileForm" name="adminProfileFormName" enctype="multipart/form-data" method="post">
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<h3 class="panel-title f_22">REPRESENTEE DETAILS</h3>
									<div class="row m_top20">
										<div class="col-sm-2">
											<h5>REPRESENTATION DATE </h5>
											<div class="input-group inline-block m_top10">
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
												</span>
												<input type="text" name="representationdate" class="form-control" id="dateRangePickerMGNF"/>
											</div>
										</div>
										<div class="col-sm-3">
											<h5>REPRESENTATION BY </h5>
												<div class="row m_top10">
													<div class="col-sm-3">
														<div class="borederCss">
															<input type="radio" name="representationType" value="SELF" id="self" attr_name="selfMemberType" class="selfRepresenceCls defaultCheckCls" attr_type="self" >
															<label><h5>SELF</span></h5>
														</div>
													</div>
													<div class="col-sm-6">
														<div class="borederCss">
															<input type="radio" name="representationType"  value="REPRESENTEE"  id="Representee"  class="selfRepresenceCls" attr_type="represent" attr_name="representMemberType"  >
															<label><h5 class="text-capital">Representee</h5></label>
														</div>
													</div>
												</div>
										</div>
									</div>
									<div class="row m_top20">
										<div class="col-sm-12">
											<div id="selfDetailsDivId"></div>
										</div>
									</div>
									<div class="row m_top20">
										<div class="col-sm-12">
											<div id="representDetailsDivId"></div>
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
<div class="modal fade" id="petitionBasicModal" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel"> close
	  <div class="modal-dialog" role="document" style="width:40%;margin-top:10%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close modalCloseCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="font-weight: bold">PETITION ENTRY</h4> 
			 </div>
		  <div class="modal-body">
			<div class="row">
				<div class="col-sm-4">
					<input   name=""   onkeyUp="checkIsNumber('noofWorks',this.value)" type="text" class="form-control m_top5 height45 isNumberCls" id="noofWorks" placeholder="Enter No Of Work">
					<br><span class="ErrCls" id="noofWorksErr"></span>
				</div>
				<!--<div class="col-sm-4">
					<input   name=""     onkeyUp="checkIsNumber('workCosts',this.value)"   type="text" class="form-control m_top5 height45 isNumberCls" id="workCosts" placeholder="Enter Work Cost">
					<br><span class="ErrCls" id="workCostsErr"></span>
				</div>-->
				<div class="col-sm-4">
					<button type="button" class="btn btn-success" id="basicBtnId" style="border-radius: 5px; font-weight: bold;margin-top:10px;">GO</button>
				</div>
			</div>
		  </div>
		  
	  </div>
	</div>
</div>
<div class="modal fade" id="candidateSearchModelDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close modalCloseCls" data-dismiss="modal" title="Close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="font-weight: bold">CANDIDATE SEARCH : </h4> 
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
					<button type="button" class="btn btn-success selectionSearchDetailsCls" style="border-radius: 0px; font-weight: bold;">Search</button>
				</div>
			</div>
			<div class="row">
				<div id="candidateDetailsDivId" style="padding: 10px;"></div>
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
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/updateTourFile.js?v=1.0.5"></script>                
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/updateTourFile2.js?v=1.0.5"></script>
<script type="text/javascript" src="Assests/login/loginMenu.js"></script>                
<!-- for file uploader -->
<script src="Assests/representationRequest/representationRequestEntry.js" type="text/javascript"></script>
<script>
</script>
</body>
</html>