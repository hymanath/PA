<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> VIEW REPRESENTATIONS DETAILS </title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/slick/slick.css" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<!-- for file uploader -->
<link href="Assests/Plugins/dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="Assests/Plugins/dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
<!-- for file uploader -->
<link rel="stylesheet" type="text/css" href="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.css" media="screen" />
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<!--<link href="Assests/Plugins/sliderbar/bootstrap-slider.css" rel="stylesheet" type="text/css"/>-->
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="Assests/Plugins/Less/less.js"></script>

 <link rel="stylesheet" type="text/css" href="Assests/SimplePagination/simplePagination.css"/>
 <style>
	.bg_light-Color{
		background-color: #EBEBEB;
	}
	.block_padding_10{
		padding: 10px;
		border: 1px solid lightgrey;
	}
	.error_colorCls{
		color: red;
	}
	body{
		font-family: 'Montserrat', sans-serif;;
	}
	.slick-prev::before, .slick-next::before {
		color: #000 !important;
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
								<!--<div style="padding:10px;">
									<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationRequestEntry" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">ADD PETITION</a></h4>
									</div>
									<div class="col-sm-6">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;" ><a href="representationRequestEntryViewMembers" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">VIEW PETITION</a></h4>
									</div>
								</div>-->
								
								
							</div> 
							<div class="row">
							
								<div style="padding:10px;">
								<!--<div class="col-sm-5">
										<h4 style="border-radius: 5px; background-color: rgb(51, 51, 51); padding: 6px;"><a href="representationsDashboard" style="color: rgb(255, 255, 255) ! important; font-size: 14px;">DASHBOARD</a></h4>
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
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel-group">
						<div class="panel panel-default panel-lightGreen">
							<div class="panel-heading" style="padding-top:10px;padding-bottom:10px;">
								<h4 class="panel-title f_22" style="font-weight:normal !important;">VIEW REPRESENTATIONS DETAILS</h4>
							</div>
								<div class="panel-body">								
										<div class="row">
											
											<div class="col-sm-1 pull-right">
												<div class="row m_top10">
												<div class="col-sm-11">
												<i class="fa fa-gears menu-cls-table pull-right" title="settings"  style="position:relative;"></i></h4>
													<div class="menuCls-table">
														<div class="arrow_box_top">
															<div class="row">
															<div class="col-sm-12">
															
																<ul style="padding-top:0px;padding-left: 20px;padding-bottom:0px;padding-right:0px;" class="list-inline">
																	<li>
																		<label class="checkbox-inline">
																		<input value="selectAll" class="getColumnAllCls" id="" type="checkbox">
																			<div style="margin-top: 3px;"><h5 class="text-capital font_weight" style="color:#54616C;">Select All</h5></div>
																		</label>
																	</li>
																</ul>
															
															</div>
															</div>
															<div class="row">
															<div class="col-sm-12">
															
																<div class="scrollTableColCls">
																<ul style="padding-top:0px;padding-left: 20px;padding-bottom:0px;padding-right:0px;" class="line_heightCls m_top10 list-inline">
																<li class="">
																	<label class="checkbox-inline">
																		<input id="checkbox1" name="name1" type="checkbox" class="getColumnCls" value="RepresentationDate" checked/>	
																		<div style="margin-top: 3px;"><h5 class="text-capital font_weight" style="color:#54616C;"> Representation Date</h5></div>
																	</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="EndorsmentNo" checked> Endorsment No</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="EndorsmentDate" checked> Endorsment Date</h5>	
																</label class="checkbox-inline">
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="RepresenteeType" checked> Representee Type</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="RepresenteeName" checked> Representee Name</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="RepresenteeDesignation" checked> Representee DESIGNATION</h5>	
																</label>
																</li>
															<!--	<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="ReferrerName" checked>Referrer Name</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="ReferreerDesignation" checked>Referreer Designation</h5>	
																</label>
																</li>-->
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="WorkDescription" checked>Work Description</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="EstimationCost" checked>ESTIMATION COST (in Lakhs)</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="noofWorks" checked>NO OF WORKS</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="Status" checked>STATUS</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="Department">DEPARTMENT</h5>	
																</label>
																</li>
																
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="Subject">SUBJECT</h5>	
																</label>
																</li>
																<li>
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="SubSubject">SUB SUBJECT</h5>	
																</label>
																</li>

																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="GrantName">GRANT NAME</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="WorkType">WORK TYPE</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="LeadName">LEAD NAME</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasReprRefDocs"> Repr Ref DOCS</h5>	
																</label>'
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasWorkCopy"> WORK DOCS</h5>	
																</label>'
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasCoveringLtr"> COVERING LTR</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasActionCopy"> ACTION COPY</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasDetailedReport"> DETAILED REPORT</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasFinalCopy"> FINAL COPY</h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="HasOthersCopy"> OTHERS COPY</h5>	
																</label>
																</li>
																
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="WithWhome" checked>PRESENTLY WITH </h5>	
																</label>
																</li>
																<li class="specialFieldCls">
																<label class="checkbox-inline">
																	<h5 class="font_weight text-capital"><input type="checkbox" class="getColumnCls" value="LastUpdatedTime">LAST UPDATED TIME</h5>	
																</label>
																</li>
																</ul>'
															</div>
															</div>
															</div>
															
															<div class="row">
																<div class="col-sm-12">
																	<button type="button" class="btn btn-primary btn-sm selectedColumnsTableCls">APPLY FILTERS</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											</div>
											<div class="col-sm-3 pull-right">	
												<div class="input-group inline-block" >
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-calendar clearDataCls" aria-hidden="true"></span>
													</span>
													<input type="text"  class="form-control" id="dateRangePicker"/>
												</div>
											</div>
											
											<div class="col-sm-3 pull-right" id="statusDiv">
												 <select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT STATUS "  id="statusId"  multiple >
														<!--<option value="0">Select Status</option>-->
												</select>
											</div>
											
											<div class="col-sm-3 pull-right" id="deptsDiv">
												 <select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT DEPARTMENT "  id="departmntId"  multiple >
														<!--<option value="0">Select Status</option>-->
												</select>
											</div>	
											
												
											<!--<div class="col-sm-2 pull-right" id="advancedSearchButtonDivId">
											  <label class="text-capitalize">
											  <span class="btn btn-success btn-md"><input type="checkbox"  id="advanceSearchBtnId">Advanced Search</input></span>
											 </label>
										   </div>-->
										</div>
										
										<div style="border:1px solid lightgrey;padding-top:10px;padding-bottom:10px;margin-top:10px;">
											<div class="row">
											<div class="col-sm-12">
											<div class="error_colorCls" id="errMsgId"></div>
												<div class="col-sm-3" id="locationDivlId">	
													<label> SEARCH BY : </label>
													<select class="form-control chosen-select clearDataCls" id="locationSelId" >
														<option value="all">All</option>
														<option value="endorsmentNO" > Endorsment No </option>
														<option value="work"> Work Location wise</option>
														<option value="representee"> Representee Location wise </option>
														<option value="referral"> Referral Location wise</option>
														<option value="referrelDesignation"> Referral Designation wise </option>
														<option value="representeeDesignation"> Representee Designation wise </option>
														<!--<option value="department">Department wise </option>-->
														<option value="subject"> Subject wise</option>
														<option value="lead"> Lead wise</option>
														<option value="name"> Name</option>
														<option value="mobile"> Mobile No</option>
														<option value="email"> Email</option>
													</select>
													<div class="error_colorCls" id="locationErrDivlId"></div>
												</div>
											<span id="parametersList" >
												<div class="col-sm-3" id="designationDiv" style="display:none;">
													<label> DESIGNATION </label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT DESIGNATION "  id="designationsId" multiple>
														<!--<option value="0">Select Designation</option>-->
													</select>
													<div class="error_colorCls" id="designationErrDiv"></div>
												</div>
												<div class="col-sm-3" id="referralNameDiv" style="display:none;">
												<label> REFERRAL NAME</label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT REFERRAL"  id="referralNameId" multiple>
														<!--<option value="0">Select Department</option>-->
													</select>
													<div class="error_colorCls" id="referralNameErrDiv"></div>
												</div>
												<div class="col-sm-3" id="departMentsDiv" style="display:none;">
												<label> DEPARTMENT</label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT DEPARTMENT "  id="departmentId" multiple>
														<!--<option value="0">Select Department</option>-->
													</select>
													<div class="error_colorCls" id="departMentsErrDiv"></div>
												</div>
												<div class="col-sm-3" id="subjectDivId" style="display:none;">
												<label> SUBJECT</label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT SUBJECT "  id="subjectId" multiple>
														<!--<option value="0">Select Department</option>-->
													</select>
													<div class="error_colorCls" id="subJErrMsg"></div>
												</div>	
												<div class="col-sm-3" id="selectLeadDivId" style="display:none;">
													<label> LEAD </label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT LEAD "  id="selectLeadId" multiple>
														<!--<option value="0">Select Department</option>-->
													</select>
													<div class="error_colorCls" id="leadErrMsg"></div>
												</div>													
												<div class="col-sm-3" id="nameDivid" style="display:none;">
												<label> NAME </label>
													<input type="text" class="form-control clearCls" style="height:40px" onkeyUp="clearData()" id="nameId" placeholder="Please enter Name">
													<div class="error_colorCls" id="nameErrDivId"></div>
												</div>	
												<div class="col-sm-2" id="mobileDivid" style="display:none;">
												<label>MOBILE NO </label>
													<input type="text" class="form-control  clearCls " style="height:40px"  onkeyUp="clearData();checkIsNumber(this.id,this.value);" maxLength="10" id="mobileId" placeholder="Please enter mobile number">
													<div class="error_colorCls" id="mobileErrDivId"></div>
												</div>	
												<div class="col-sm-3" id="emailDivid"  onkeyUp="clearData()" style="display:none;">
												<label> EMAIL-ID</label>
													<input type="text" class="form-control  clearCls" style="height:40px" id="emailId" placeholder="Please enter email">
													<div class="error_colorCls" id="emailErrDivId"></div>
												</div>	
												<div class="col-sm-3" id="endorsmentNoDivid" style="display:none;">
												<label> ENDORSMENT NO </label>
													<input type="text"  onkeyUp="clearData();checkIsNumber(this.id,this.value);" class="form-control  clearCls" style="height:40px" id="endorsmentNoId" placeholder="Please enter endorsment number">
													<div class="error_colorCls" id="endorsmentNoErrDivId"></div>
												</div>
												<div class="col-sm-2 pull-right" id="advancedSearchButtonDivId">
												  <label class="text-capitalize">
												  <span class="btn btn-success btn-md"><input type="checkbox"  id="advanceSearchBtnId">Advanced Search</input></span>
												 </label>
												</div>
										   </div>
											</div>
											<div class="row">
												<div class="col-sm-12 m_top10" id="districtConsMandDivId" style="display:none;">
												<div class="col-sm-4" id="districtCandDiv">
													<label>DISTRICT</label>
													<select class="form-control chosen-select clearDataCls" data-placeholder="SELECT DISTRICT " id="districtCandId" multiple>
														<!--<option value="0">All</option>-->
													</select>
													<div class="error_colorCls" id="districtCandErrDiv"></div>
												</div>
												<div class="col-sm-4" id="constituencyCanDiv">
												<label>CONSTITUENCY</label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT CONSTITUENCY "  id="constituencyCanId" multiple>
														<!--<option value="0">All</option>-->
													</select>
													<div class="error_colorCls" id="constituencyCanErrDiv"></div>
												</div>
												<div class="col-sm-4" id="mandalCanDiv">
												<label>MANDAL</label>
													<select class="form-control chosen-select clearDataCls"  data-placeholder="SELECT MANDAL "  id="mandalCanId" multiple>
														<!--<option value="0">All</option>-->
													</select>
													<div class="error_colorCls" id="mandalCanErrDiv"></div>
												</div>
												</div>
												</span>
											</div>
											</div>
											<div class="row">
												<div class="col-sm-12 m_top10">
													<button type ="button" class="btn btn-lg btn-success pull-right" id="advanceSearchId" style="cursor: pointer; font-weight: bold; border-radius: 0px;">SEARCH</button>
												</div>
											</div>	
										</div>
										<div class="row ">
										<div class="col-md-12 m_top20">
											<button class="btn btn-min btn-xs btn-success" id="updatStatusChangeId" style="position: fixed;z-index:9999;margin-top:100px;background-color: #e6e6e6;border-color: #e6e6e6;"> UPDATE STATUS </button>
										</div>
									</div>
									
									</div>
									
									<!--<div class="row m_top10">
										<div class="col-sm-12">
											<button type ="button" class="btn btn-lg btn-success pull-right" id="advanceSearchId" style="cursor: pointer; font-weight: bold; border-radius: 0px;">SEARCH</button>
										</div>
									</div>-->
									
									<div class="row m_top10 " id="petitionSubWorkRadioDivId" style="display:none;">
										<div class="col-sm-12 ">
											<div class="col-sm-2 pull-right">
												<input type="radio" class ="petitionSubWorkRadoCls" name="radioBtton"  value="work" id="workId"  attr_type="subWork" >
												<label><h5>WORKS WISE</h5></label>
											</div>
											<div class="col-sm-2 pull-right">
												<input type="radio" class ="petitionSubWorkRadoCls" name="radioBtton" checked value="petition" id="petitionId"  attr_type="petition" >
												<label><h5>REPRESENTATIONS WISE</h5></label>
											</div>
										</div>
									</div> 
									<div class="row m_top10">
										<div class="">
											<div id="summaryId"></div>
										</div>
									</div>
									<div class="row ">
										<div class="col-md-12 m_top20 text-center">
											<div class="paginationId"></div>
										</div>
									</div>
									
									
									<div class="row m_top10">
										<div class="col-sm-12" style="padding-left: 7px;padding-right: 7px;">
											<div id="representationRequestEntryTable"></div>
										</div>
									</div>
									
									<div class="row ">
										<div class="col-md-12 m_top20 text-center">
											<div class="paginationId"></div>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<input type="hidden" id="hiddenDesignationName" />
<input type="hidden" id="hiddenDesignationId" />
<div class="modal fade" id="representeeDetailsModelDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%;margin:auto">
		<div class="modal-content">
		  <div class="" style="padding: 15px;">
				<button type="button" class="close modalCloseCls" data-dismiss="modal" title="Close"  aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  </div>
		  <div class="modal-body">
				<div id="representeeViewId"></div>
		  </div>
		  <div class="modal-footer">
				<button type="button" class="btn btn-default modalCloseCls" data-dismiss="modal">Close</button>
		  </div>
	  </div>
	</div>
</div>


<div class="modal fade" id="endorseMentModalDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:90%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
		<div class="row">
			<div class="col-sm-12">
				<button type="button" class="close closeSecondModal addRemoveModel" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center font_weight" id="endorseMentHeadingId"></h4> 
			</div>
		</div>		
	  </div>
	<form action="endorsingSubWorksAndAssigningToOfficer" id="endorsingSubWorksId" name="" enctype="multipart/form-data" method="post">
	  <div class="modal-body">
	   <div class="pad_light_yash_bg border_yash border_radius_5" id="totalWorkEditDivId">
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
		<div class="row m_top10" id="endorseDivId" style="display:none;" >
					<div class="col-sm-12">
						<!--<label class="checkbox-inline">
						  <input type="checkbox" name="endorseChck" id="inlineCheckbox1" value="endorse" class="font_weight uploadFuncCls" checked> ENDORSE 
						</label>-->
						<label class="checkbox-inline">
						  <input type="checkbox" name="endorseChck" id="inlineCheckbox2" value="upload" class="font_weight uploadFuncCls"> UPLOAD COVERING LETTER
						</label>
					</div>
		</div>
		<div class="row m_top20" id="uploadCoverFileDivCls" style="display:none;">
				  <div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;">
					<h5 class="panel-title"> UPLOAD COVERING LETTER</h5>
					<span id="uploadCoverFile"></span>
					<span id="fileCoverUploadIdErr" style="color:red;"></span>
				  </div>
		</div>
		<div class="row" id="endorseFunctionDivId">
			<div class="col-sm-5">
				<div class="row m_top10" id="actionChangeDivId" style="display:none;">
					<div class="col-sm-12">
						<label class="checkbox-inline actionChangeCls"  style="display:none" >
						  <input type="checkbox" name="" id="inlineCheckbox1" value="COMPLETED" class="actionCls font_weight" checked> ACTION CHANGE 
						</label>
						<!--<label class="checkbox-inline">
						  <input type="checkbox" name="" id="inlineCheckbox2" value="ASSIGNED" class="actionCls font_weight"> FORWARD
						</label>
						-->
					</div>
				</div>
				<div class="row m_top20" id="uploadFileDivCls" style="display:none;">
				  <div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;">
					<h5 class="panel-title"> UPLOAD <span id="letterNameId"></span></h5>
					<span id="uploadFile"></span>
					<span id="fileUploadIdErr" style="color:red;"></span>
					<span id="actioncopyRefIdDiv">
					</span>
				  </div>
					<div class="col-sm-12 m_top20" id="detaildReportReviewFiledsDivId" style="display:none;">					
						<div class="col-sm-6 ">
						<label><span id="">SANCTIONED AMOUNT (in lakhs): </span> </label>
							<input type="text" class="form-control" id="sanctinedAmountId" placeholder="Sanctioned Amount" name="sanctionedAmount" />
						</div>
						<div class="col-sm-6 ">
						<label><span id="" title="Work in kilo metres">LENGTH (in km):</span> </label>
							<input type="text" class="form-control" id="workInKmId" placeholder="Work in kilo meters" name="noOfKm" />
						</div>
					</div>
					
					<div class="col-sm-12 m_top20" id="actionmemoDivId" style="display:none;">
					<label><span id="">ACTION MEMO REF NO:</span> <span style="color:red;">*</span><span id="actioncopyRefIdErrStr" style="color:red;"></span></label>
						<input type="text" class="form-control" id="actioncopyRefId" placeholder="Action Memo No" name="" attr_name="refNo"/>
					</div>
					
				</div>
				<div class="row m_top10" >
					<div class="col-sm-12" id="statusChangeDivId">
						<label><span id="forwardText">FORWARD FOR:</span> <span style="color:red;">*</span><span id="statusIdErrStr" style="color:red;"></span></label>
						<select class="form-control chosen-select" id="statusChangeId">
							<option value="0"> Select Action </option>
						</select>
					</div>
				</div>
				
				<!--
				<div class="row m_top10" id="actionTypeDivId" style="display:none;">
					<div class="col-sm-12" >
						<label>SELECT ACTION TYPE <span style="color:red;">*</span><span id="actionTypeErr"></span></label>
						<select class="form-control chosen-select" id="assignTypeId" name="actionType">
							<option value="0">SELECT ACTION TYPE</option>
							<option value="ASSIGNED">ASSIGN TO OFFICER</option>
							<option value="COMPLETED">UPLOAD FILE COPY</option>
						</select>
					</div>
				</div>
				-->
				<div class="row m_top10" id="assighTypeId" style="display:none;">
					<div class="col-sm-12" >
						<label>ASSIGN FOR : <span style="color:red;">*</span><span id="actionTypeErr"></span></label>
						<select class="form-control chosen-select" id="actionTypId" name="actionTypeId">
							<option value="0">SELECT ASSIGN TYPE</option>
						</select>
					</div>
				</div>
				
				<div class="row m_top10" id="endorsementDivId" style="display:none;">
					<div class="col-sm-12">
						<label>ENDORSMENT NO<span style="color:red;">*</span><span id="endorsementNoErr"></span></label>
						<input type="text" class="form-control" id="endorsmentNo" placeholder="Endorsment NO" name="endorsementNO"/>
					</div>
				</div>	
				<div class="row m_top10">
					<div class="col-sm-12" id="leadDivId" style="display:none;">
						<label>LEAD<span style="color:red;">*</span><span id="leadIdErr"></span></label>
						<select class="form-control chosen-select" id="leadId" name="leadId">
							<option value="1">SELECT LEAD</option>
						</select>
					</div>
					<div class="col-sm-12" id="leadOtherDivId" style="display:none;">
						<label> OTHER LEAD NAME <span style="color:red;">*</span> <span id="leadOtherErr" style="color:red;"></span></label>
						<input type="text" class="form-control" id="leadOtherId" placeholder="Please enter lead details" name="otherLead"/>
					</div>
					<div class="col-sm-12" id="grantDivId" style="display:none;">
						<label>GRANT UNDER<span  style="color:red;"></span><span id="grantIdErr"></span></label>
						<select class="form-control chosen-select" id="grantId" name="grantId">
							<option value="1">SELECT GRANT UNDER</option>
						</select>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-sm-6" id ="assignDesignationDivId" style="display:none;">
				<label><span id="assignningTypeId">ASSIGN TO</span><span style="color:red;">*</span><span id="assignToIdErr"></span></label>
				<select class="form-control chosen-select popUpChangesCls" id="assignToId" name="deptDesigId">
					<option value="0">SELECT DESIGNATION</option>
				</select>
			</div>
			<div class="col-sm-6" id ="assignOfficerDivId" style="display:none;">
				<label>OFFICER <span style="color:red;">*</span><span id="officerIdErr"></span></label>
				<select class="form-control chosen-select" id="officerId" name="deptDesigOffcrId">
					<option value="0">SELECT OFFICER </option>
				</select>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-4" id ="documentTypeDivId" style="display:none;">
				<label>DOCUMENT TYPE<span style="color:red;">*</span><span id="documentTypeIdErr"></span></label>
				<select class="form-control chosen-select" id="documentTypeId" name="documentTypeId">
					<option value="0">SELECT DOCUMENT TYPE</option>
				</select>
			</div>
			<div class="col-sm-4" id ="referranceNoDivId" style="display:none;">
				<label>DOCUMENT REF. NO<span id="referranceNoIdErr"></span></label>
				<input type="text" class="form-control" id="referranceNoId" placeholder="" name="refNo"/>
			</div>
		</div>
		<div class="row m_top20" id="finalApproDocDiv" style="display:none;">
		  <div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;">
			<h5 class="panel-title"> UPLOAD REF. DOCUMENT <span id="finalapproveUploadIdErr"></span></h5>
			<span id="finalapproveFile"></span>
		  </div>
		</div>
				
		<input type="hidden" class="form-control" id="nextStatusId"  name="statusId"/>
		<input type="hidden" class="form-control" id="petitionsId"  name="petitionId"/>
		<input type="hidden" class="form-control" id="hiddenEndorseNo"  name="workName"/>
		<div class="row m_top10" id="commentsDivId" style="display:none;">
			<div class="col-sm-12">
				<label>Comment<span style="color:red;">*</span><span id="remarkIdErr"></span></label>
				<textarea class="form-control" rows="3" id="remarksId" name="remark"></textarea>
			</div>
		</div>
		<div class="row m_top10" id="coverLetterLableDivId" style="display:none;">
					<div class="col-sm-12">
						<label class="checkbox-inline">
						  <input type="radio" id="" value="withHeader" class="coverLtrLableCls" name="radioBtn" > WITH HEADER 
						</label>
						<label class="checkbox-inline">
						  <input type="radio" id="" value="withOutHeader" class="coverLtrLableCls" name="radioBtn" checked> WITHOUT HEADER
						</label>
					</div>
		</div>
		<div class="row m_top20" id="fileUploadIdDiv" style="display:none;">
			<div class="col-sm-6 col-sm-offset-2 text-center" style="border: 2px dashed #ccc;padding: 10px;">
				<button type="button" class="btn btn-success" id="coverLetterId" onclick="generateCoveringLetterForPetition()">Generate Cover Letter</button>
			</div>
			<span id="coveringLetterPthErr" style="color:red;margin-left:10px;"></span>
		</div>
		<div id="coveringLetterGenerator" class="m_top20"></div>
		<input type="hidden" id="coverLetterPath" name="coverLetterPath"/>
		<input type="hidden" id="actionTypeStr" value="" name="actionType"/>
	   </div>
			<div class="col-sm-2" id="imageBuildingId">
			</div>
			<div class="col-sm-5"><div id="imageOrPdfDisId"></div></div>
		</div>
		
	  <div class="modal-footer m_top20">
		  <div class="row m_top20" >
			<div id="ajaxcallImageId" > </div>
		  <div class="col-sm-8 ">
		   </div>
			 <div class="col-sm-4 ">
				 <button type="button" class="btn btn-default closeSecondModal addRemoveModel" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-success pull-right  " id="endorsWorksId" onclick="endorsingSubWorksAndAssigningToOfficer()" ><span id="buttonNameId"></span></button>
			   </div>
		  </div>
	  </div>
	</form>
  </div>
</div>
</div>
</div>

<div class="modal fade" id="docsModalDivId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
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
<div class="modal fade" id="petitionHistroyModalId" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%;margin:auto">
    <div class="modal-content">
      <div class="modal-header">
			<button type="button" class="close modalCloseCls petitionModelClose closeSecondModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title text-capital"  style="font-weight: bold" id="headingTileHistoryId">PETITION HISTORY</h4> 
	  </div>
      <div class="modal-body">
        <div id="petitionHistroyDetailsId"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default modalCloseCls petitionModelClose closeSecondModal" data-dismiss="modal">Close</button>
      </div>
   
  </div>
</div>
</div>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/login/loginMenu.js"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/slick/slick.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<!--<script src="Assests/Plugins/sliderbar/bootstrap-slider.js" type="text/javascript"></script>-->
<script src="Assests/SimplePagination/simplePagination3.js" type="text/javascript"></script>

<!--<script src="Assests/ruralWaterSupply/custom.js" type="text/javascript"></script>-->
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
<!-- for file uploader -->
<script type="text/javascript" src="Assests/Plugins/pdfexpand_prrws/source/jquery.fancybox.js"></script>
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/jquery.filer.min.js"></script>
<script type="text/javascript" src="Assests/Plugins/dragAndDropPhoto/js/singleFileuploadDocuments.js"></script>
<script src="Assests/representationRequest/representationRequestEntryViewMembers.js" type="text/javascript"></script>
<script src="Assests/representationRequest/representationRequestEntryPopupView.js" type="text/javascript"></script>
<script>
</script>
<script>
var userId = '${sessionScope.USER.userId}';
var glDesignationId = '${sessionScope.USER.designationId}'
var glDesignationName = '${sessionScope.USER.designation}';
$("#hiddenDesignationName").html(glDesignationName);
$("#hiddenDesignationId").val(glDesignationId);
if(parseInt(userId) == 25 || parseInt(userId) == 26 || parseInt(userId) == 27 || parseInt(userId) == 28){
	$('.specialFieldCls').remove();
}
</script>
<script type="text/javascript">
$(document).on("click",".closeSecondModal",function(){
    setTimeout(function(){
      $("body").addClass("modal-open")
    },1000);
  });
  if(glDesignationId != 2 && glDesignationId!=86){
	  $("#actionChangeDivId").show();
  }
  if(glDesignationId == 23){
	  $('#updatStatusChangeId').show();
  }else{
		$('#updatStatusChangeId').hide();
  }
  
  var searchBy= '${param.searchBy}';
  var desigId= '${param.desigId}';
  var statusId= '${param.statusId}';
  var deptId ='${param.deptId}';
 var subjId = '${param.subjId}';
 var refCanId ='${param.refCanId}';
 var briefleadId ='${param.leadId}';
    if(deptId ==''){
	deptId=0;
	}
	if(refCanId ==''){
	refCanId=0;
	}
	if(desigId ==''){
	desigId=0;
	}
	if(statusId ==''){
	statusId=0;
	}
	if(briefleadId ==''){
	subjId=0;
	}if(briefleadId ==''){
		briefleadId=0;
	}else{
		getPmBriefLeadList(0);
	}
  if(searchBy != ''){
	  $("#petitionId").prop("checked",true);
	  onLoadClickDataDetails();
	}else{
		$("#petitionId").prop("checked",true);
		$('#advanceSearchId').trigger('click');
	}
/* if(statusId.length >0){
	getStatusList(statusId);
}else{ */
	getStatusList(0);
	getDepartmntsDetails();
//}
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/representationRequestEntryViewMembers")));
//wurl = wurl.replace("/PRRWS-1.0","");
	/* var worksSlider;
	var slider = new Slider('#noofWorksSlider', {
		formatter: function(value) {
			worksSlider=value;
			$("#noOfWoksValue").text(value);
			return 'Current value: ' + value;
		}
	});
	//estimatedAmountSlider
	var esimatedSliderCount;
	var slider = new Slider('#estimatedAmountSlider', {
		formatter: function(value) {
			esimatedSliderCount=value;
			$("#estimatedValue").text(value);
			return 'Current value: ' + value;
		}
	});
	//pendingDaysSlider
	var pendingSliderCount;
	var slider = new Slider('#pendingDaysSlider', {
		formatter: function(value) {
			pendingSliderCount=value;
			$("#pendingDaysValue").text(value);
			return 'Current value: ' + value;
		}
	}); */
	
	
function getDepartmntsDetails(){
	var selStatusId = $("#statusId").val();
	var statusIds = [];
	if(selStatusId != null && selStatusId.length >0){
		statusIds=selStatusId;
	}else if(statusId.length >0){
		var statusList = statusId.split(',');
		for(var i=0;i<=statusList.length-1;i++){
			statusIds.push(statusList[i]);
		}
	}
 var json = {
		 reportType :"department",
		 fromDate :currentFromDate,
		 toDate : currentToDate,
		 departmentId:0,
		 statusId:statusIds
		}           
	$.ajax({              
		type:'POST',    
		url: 'getDepartmentsBySearchType',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#departmntId").empty();
		$("#departmntId").append("<option value='0' selected> ALL </option>");
		if(result !=null && result.length >0){
			for(var i in result)
				$("#departmntId").append("<option value='"+result[i].key+"' >"+result[i].value+"</option>");
		}
		$("#departmntId").trigger('chosen:updated');
	});	
}

</script>
</body>
</html>