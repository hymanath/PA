<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update Tour Details</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/tourDetails/tours_custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet">
<!-- for file uploader -->
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
<!-- for file uploader -->
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>  
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>  
<link href="newCoreDashBoard/Plugins/RangeSlider/jquery-ui-1.8.10.custom.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link href="newCoreDashBoard/Plugins/RangeSlider/iThing.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>  
<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
<script src="http://www.google.com/jsapi" type="text/javascript"></script>	  
<style>
.candiateCls
{
	color:#337ab7;
}
.tableAttachments
{
	border:1px solid #ddd
}
.tableAttachments tr td
{
	border-top:1px solid #ddd;
}
.btnView
{
	border:1px solid #449D44;
	background-color:#fff;
}
.btnDelete
{
	border:1px solid #c9302c;
	background-color:#fff;
}

#toursCandidateDetails .table-bordered input{
	width:80px;
}
.chosen-container{width:100% !important}
.requiredFont{
		color:red;
}
#toursCandidateDetailsModal .table-bordered input{
	width:80px;
}
.jFiler-input-dragDrop
	{
		padding:5px;
	}
	.icon-jfi-folder
	{
		font-size: 30px
	}
	.jFiler-input-text h3
	{
		font-size:16px;
	}
	.jFiler-input-text span
	{
		margin-top:5px !important;
		margin-bottom:5px !important;
	}
.m_top20{margin-top:20px}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
							<div class="row">
							<div class="col-md-10 col-xs-12 col-sm-10">
								<h4 class="panel-title text-capital">TOUR DETAILS OVERVIEW</h4>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-2">
								  <span class="input-group pull-right " style="width:200px;">
										<input type="text" id="toursDateRangePickerNew" style="width:200px" class="form-control" />
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
								 </span>
							</div>
						</div>
					</div>
					<!--<div class="panel-body">
						<div class="table-responsive">
							<table class="table tableTours">
								<thead>
									<th></th>
									<th>total leaders</th>
									<th>submited leaders</th>
									<th>not submited leaders</th>
									<th>compliance</th>
									<th>non compliance</th>
								</thead>
								<tr>
									<td>minister <i class="glyphicon glyphicon-info-sign"></i></td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
									<td>17</td>
								</tr>
								<tr>
									<td>minister <i class="glyphicon glyphicon-info-sign"></i></td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
									<td>10</td>
								</tr>
							</table>
						</div>
					</div>-->
				<div class="panel-body pad_0 border_0">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="overAllLeaderDivId"></div>
                        </div>
                    </div>
                </div>
				</div>
			</div>
			
			<form name="toursFormApplication" method="post" id="toursFormApplication">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Update Tour Details</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>Designation Level:<font class="requiredFont">*</font></label>
									<select  class="form-control" id="designationSlctBxId">
									<option value="0">Select Designation Level</option>
								</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>Select Name:<font class="requiredFont">*</font></label>
									<select class="form-control" id="memberSlctBxId" >    
									<option value="0">SELECT NAME</option>
								</select>
								</div>
							
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<!--<h4 class="panel-title text-capital" style="display:none" id="selectProfileId" >selected profile</h4>-->
										<ul class="list-inline ">
											<li>
													<!--<div class="pad_10">
														<img src="Assests/img/profile.png" style="height: 30px;width: 30px;"/>
														<p>Kollu Ravindra</p>
														<p>IXR9816267</p>
														<p>+91 9988998899</p>
													</div>-->
												<div class="row m_top20 hideProfileDivCls">
													<div class="col-md-12 col-xs-12 col-sm-12">
														<div class="block">
															<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="profileProcessingImgId"></center></div>
															
															<!--<h4 class="panel-title text-capital" style="display:none" id="selectedProfileId">selected profile</h4>-->
															<div id="selectedMemberDtslDivId"></div>
													   </div>
												   </div>
												</div>
										   <!--<div class="pad_10 borderGreen">
														<label class="checkbox-inline">
															<input type="checkbox"/>Select Profile</label>
												</div>-->
											</li>
										</ul>
								</div>
								
								<div id="overallDivId" style="display:none;">
									<div class="col-md-12 col-xs-12 col-sm-12 m_top10" >
										<div  id="toursId">
											<div class="col-md-8 col-xs-12 col-sm-8">
												<h4><span  id="candidateNameId"></span>&nbsp;&nbsp;&nbsp;<small id="changedDate"></small></h4>
											</div>
											<div class="col-md-2 col-xs-12 col-sm-2">
												<div class="input-group inputGCustom">
													<input type="hidden" id="hiddenTdpCadreId"/>
													<input type="text" class="form-control" id="tourMonthYear" >
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-calendar"></i>
													</span>
												</div>
												<div id="errorDiv" class="text-danger"></div>
											</div>
											<div class="col-md-2 col-xs-12 col-sm-2">
												<button type="button" class="btn btn-success" title="To get tours over view" onclick="getAllTourDetailsOverview(1,'',0,'','','')">Submit</button>
											</div>
										</div>
										<div class="row">
										<!--<form name="submitApplication" method="post">-->
											<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
												<div id="toursCandidateDetails"></div>
												<div id="commentsUlId"></div>
												<!--<h4 style="margin-top:20px !important">Program Wise Info</h4>-->
												<div id="toursCandidateProgramDetails" style="margin-top:10px;"></div>
												<!-- <div id="remarkId6" style="display:none">
													<label>Comment: </label>
													<textarea id="remarkId7" class="form-control" style="resize: none;"></textarea>
												</div> -->	
												<div id="attachementsId"></div>
											</div>
											<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
												<div style="display:none;" id="dateWiseBlockId"><!--san-->
													<div class="panel panel-default">
														<div id="addNewTourBlock"></div>
														<div class="panel-footer borderGreen text-right">
															<button type="button" class="btn btn-success addAppendTourBlockCls">+ ADD TOUR</button>											
														</div>
													</div>
												</div>
												<div class="">
													  <label>Comment:</label>
													  <textarea style="resize: none;" name="toursNewVO.remark" class="form-control lang" id="remarkId"></textarea>
												</div>												
												<div class="row" id="uploadFlDivId">
													<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
														<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3>  
														<input type="file" id="update_TourFileId2" multiple="multiple"  name="files[]" class="m_top20"/>
														<span id="errFileId" style="color:red;margin-left:470px;"></span>   
													</div>
												</div>  
												<div class="row" id="submitApplicationBtnId"> 
													<div class="col-md-4 col-md-offset-4">
														<span class="updateTourStatusCls"></span>
														<button type="button" class="btn btn-success btn-block" onclick="savingApplication1();" type="button" id="submitId">SUBMIT APPLICATION</button>
														<span id="successSpanId"></span>  
													</div>   
													<div class="col-md-1">
														<div id="formSubmitLoader"></div>
													</div>
													<div class="col-md-12 col-sm-12 col-xs-12" id="statusId"></div>
												</div> 
											</div>
										 <!--</form>=-->
											
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Hidden Variables-->
				<div>
					<input type="hidden" id="hiddenCadreId" value="" name="toursNewVO.tdpCadreId" />
					<input type="hidden" id="hiddenTourMonthId"  value="" name="toursNewVO.tourMonth"/>
				</div>
			</form>
		</div>
	</div>
	<!-- Tour BLock New Append Balu Start-->
	<div id="tourCloneMainDivNew" style="display:none">
		<div class="panel-body borderGreen">
			<i class="closeIcon glyphicon glyphicon-remove newsTourRemoveBtnClsNew"></i>
			<div class="row">
				<div class="col-md-4 col-xs-12 col-sm-3">
					<label>Tour category</label>
					<select class="form-control tourCategoryNewCls">   
						<option value="0">Tour Category</option>
					</select>
				</div>
				<div class="col-md-4 col-xs-12 col-sm-3">
					<label>Tour Type</label>
					<select class="form-control tourTypeNewCls">   
						<option value="0">Tour Type</option>
					</select>
				</div>
				<div class="col-md-4 col-xs-12 col-sm-2">
					<label>Tour Days</label>
					<input type="text" class="form-control tourDaysNewCls"/>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<label>Add Comment/Tour Description</label>
					<textarea class="form-control tourDescNewCls"></textarea>
				</div>
			</div>
		</div>
	</div>
	<!-- Tour BLock New Append Balu End-->
	
	
	<!--- new tour block append--->
	<div id="newsTourCloneMainDiv" style="display:none;">
	<div class="panel panel-default" id="newsTimeCloneDivId">
		<div class="panel-body borderGreen">
			<i class="closeIcon glyphicon glyphicon-remove newsTourRemoveBtnCls"></i>
			<div class="row">
				<div class="col-md-3 col-xs-12 col-sm-3">
					<label>Tour Date</label>
					<div class="input-group inputGCustom">
						<input type="text" class="form-control tourDateCls">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-3">
					<label>Tour Type</label>
					<select class="form-control tourTypeCls">    
					   <option value="0">Tour Type</option>
					 </select>
				</div>
				<div class="col-md-4 col-xs-12 col-sm-4">
					<label>Tour Category</label>
					<select class="form-control tourCategoryCls">    
					   <option value="0">Tour Category</option>
					 </select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2">
					<label>Tour Location</label>
					<select class="form-control tourLocationCls">
						<option value="0">Tour Location</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-xs-12 col-sm-2 stateDivCls" style="display:none;">
					<label>State</label>
					<select class="form-control stateSelCls">
						<option value="0">Select State</option>
					</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2 districtDivCls" style="display:none;">
					<label>District</label>
					<select class="form-control districtSelCls">
						<option value="0">Select District</option>
					</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2 constituencyDivCls" style="display:none;">
					<label>Constituency</label>
					<select class="form-control constituencySelCls">
						<option value="0">Select Constituency</option>
					</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2 tehMunDivCls" style="display:none;">
					<label>Mandal / Municipality</label>
					<select class="form-control tehMunSelCls">
						<option value="0">Select Mandal / Municipality</option>
					</select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2 villWardDivCls"  style="display:none;">
					<label>Village / Ward</label>
					<select class="form-control villWardSelCls">
						<option value="0">Select Village / Ward</option>
					</select>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<label>Add Comment/Tour Description</label>
					<textarea class="form-control tourtextAreaCls"></textarea>
				</div>
			</div>
		</div>
	</div>	
	</div>
	<!-- membersOverviewModal -->
	
	<div class="modal fade" id="membersOverviewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="membersOverviewModalLabel">Modal title</h4>
		  </div>
		  <div class="modal-body">
			  <div class="row">
			  	<div class="col-md-12 col-xs-12 col-sm-12">
					<div id="membersOvrvwId"></div>
					<div id="membersOverviewId"></div>
			  	</div>
			  </div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		  </div>
		</div>
	  </div>
	</div>
	<!--membersOverviewModal EDit-->
	<div class="modal fade" id="membersOverviewModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close closeMembersOverviewModalEdit" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="membersOverviewModalLabel1"></h4>
		  </div>
		  <div class="modal-body">
			  <div class="row">
			  	<div class="col-md-12 col-xs-12 col-sm-12">
					<div id="membersOverviewModalEditId"></div>
			  	</div>
			  </div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default closeMembersOverviewModalEdit" data-dismiss="modal">Close</button>
		  </div>
		</div>
	  </div>
	</div>
	<!--editview modal start-->
	<div class="modal fade" tabindex="-1" id="retrivalEditModalId" role="dialog">
	  <div class="modal-dialog" role="document" style="width:80%">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close closeRetrivalEditModalId" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Edit / View Tour Details</h4>
		  </div>
		  <div class="modal-body" id="retrivalEditModalBodyId">
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<form name="submitUpdateApplication" method="post"> <!--balu11-->
						<div style="border:1px solid #ddd;padding:10px;">
							<div id="retriveModalId"></div>
						</div>
						<div style="border:1px solid #ddd;padding:10px;">
							<div id="retriveModalDocumentDivId"></div>
						</div>
						<div class="row showDivUpdateCls" id="uploadUpdateFlDivId"></div>
						
						<div class="row showDivUpdateCls" > 
							<div class="col-md-4 col-md-offset-4">
								<!--<span class="updateTourStatusCls"></span>-->
								<button type="button" class="btn btn-success btn-block" onclick="updateApplication();" type="button">UPDATE APPLICATION</button>
								<span id="successUpdateSpanId"></span>  
							</div>   
							<!--<div class="col-md-12 col-sm-12 col-xs-12" id="statusId"></div>-->
						</div> 
					</form>
				</div>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default closeRetrivalEditModalId" data-dismiss="modal">Close</button>
		  </div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!--editview modal end-->
	
	<!-- Document Model -->
	<div class="modal fade" tabindex="-1" id="tourNewDocumentId" role="dialog" style="z-index:99999;">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Tour Document</h4>  
			</div>
			<div class="modal-body" id="tourNewDocumentBodyId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
	</div><!--  /.modal -->
	
	
	<!-- Tours New Modal Start-->
	<div class="modal" tabindex="-1" role="dialog" id="tourIndividualPerformanceDivId" style="z-index:9999;">
	  <div class="modal-dialog modal-lg" style="width:85%">       
		<div class="modal-content" style="border-radius:0px">
		  <div class="modal-header" style="background-color:#CCC">
			<button type="button" class="close tourIndividualCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<span id="nameOfMemberHeadingId"></span>
		  </div>
		  <div class="modal-body"> 
			<div class="row" style="background: rgb(204, 204, 204) none repeat scroll 0% 0%; padding: 0px 0px 20px; border-radius: 6px; margin: 10px 0px 0px;">
				<div class="col-md-2 col-xs-12 col-sm-4"> 
					<select class="pull-right form-control" id="dateRangeSliderYear" style="margin-top: 46px;">
						<option value="0">Select Year</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
					</select>
				</div>
				<div class="col-md-9 col-xs-12 col-sm-12" style="margin-left: -20px;"> 
					<div id="toursUpdateYear" style="margin-top:7px"></div>
				</div>
				<div class="col-md-1 col-xs-12 col-sm-4 pull-right">
					<button class="btn btn-success pull-right" id="subMitBtn" type="button" style="margin-top: 46px;">SUBMIT</button>
				</div>
			
			</div>
			<!---<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12"> 
					<div id="tourSlider" style="margin-top:7px"></div>
				</div>
			</div>--->
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12"> 
					<div id="tourIndividualDetailsBlock" class="m_top20"></div>
					<div id="monthWiseComplainceDivId" class="row m_top20"></div>
					<div id="tourIndividualDetailsTableBlock" class="m_top20"></div>
				</div>
			</div>
		  </div>
		  <div class="modal-footer">     
			<button type="button" class="btn btn-default tourIndividualCls" class="close" data-dismiss="modal">Close</button>
		  </div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- Tours New Modal End-->
	<div class="modal" tabindex="-1" role="dialog" id="editTourDetailsModalId" style="z-index:9999;">
	  <div class="modal-dialog modal-lg" style="width:85%">       
		<div class="modal-content" style="border-radius:0px">
			<div class="modal-header">
				<button type="button" class="close closeClass" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<div id="monthEditId" style="margin-left: 228px;margin-bottom:-21px;"></div>
				<h4 class="modal-title">EDIT/VIEW TOUR DETAILS</h4>  
			</div>
			<form name="toursUpdateFormApplication" method="post" id="toursUpdateFormApplication">
			<div class="modal-body">
				<!--<div class="col-md-12 col-xs-12 col-sm-12 m_top10">-->
					<div id="toursCandidateDetailsModal"></div>
					<div id="toursCandidateProgramDetailsModal"></div>
					
					<div id="remarkId3" style="display:none">
						<label>Comment: </label>
						<textarea id="remarkId2" name="toursNewVO.remark" class="form-control" style="resize: none;"></textarea>
					</div>	
						
					<div id="attachementsIdModal"></div>
					<div class="row" id="updateToursModalDiv">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
						<!--<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3> -->
							<input type="file" id="editupdateTourDiv" multiple="multiple"  name="files[]" class="m_top20"/>
							<span id="" style="color:red;margin-left:470px;"></span>   
						</div>
					</div>  
					<div class="row" id=""> 
						<div class="col-md-4 col-md-offset-4">
							<span class="updateTourStatusModalCls"></span>
							<button type="button" class="btn btn-success btn-block" id="updateId"onclick="updateApplication1()" type="button">SUBMIT/UPDATE TOURS</button>
							<span id="successSpanModalId"></span>  
						</div>   
						<!--<div class="col-md-12 col-sm-12 col-xs-12" id="statusId"></div>-->
					</div> 
				<!--</div>-->
				<div>
					<input type="hidden" id="hiddentdpCadreIdForModal" value="" name="toursNewVO.tdpCadreId" />
					<input type="hidden" id="hiddentourMonthIdForModal"  value="" name="toursNewVO.ToursMonthId"/>
				</div>
			</div>
			</form>
		</div>
	  </div>
	</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jquery-ui.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/RangeSlider/jQDateRangeSlider-withRuler-min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<!--<script src="js/Tours/toursDetails.js" type="text/javascript"></script>-->
<!-- for file uploader  -->              
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<!--<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>-->
<script type="text/javascript" src="dragAndDropPhoto/js/updateTourFile.js?v=1.0.5"></script> 
<script type="text/javascript" src="dragAndDropPhoto/js/updateTourFile2.js?v=1.0.5"></script>                
<script type="text/javascript" src="dragAndDropPhoto/js/editUpdateDetails.js?v=1.0.5"></script>                
<!-- for file uploader -->
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>

<script src="js/Tours/updateToursDetails.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
/*New Code*/
/*Month & Year Picker*/
$("#tourMonthYear").datetimepicker({
	format:'MM-YYYY',
	maxDate:moment()
});
$('#tourMonthYear').on('dp.change', function(e) { 
	var date = e.date.format("MMM-YYYY")
	$("#changedDate").html('('+(date)+')');
});
editUpdateDetailsInitilize();
 google.load("elements", "1", {
          packages: "transliteration"
    });

    function onLoad() {
      var options = {
          sourceLanguage:
              google.elements.transliteration.LanguageCode.ENGLISH,
          destinationLanguage:
              [google.elements.transliteration.LanguageCode.TELUGU],
          shortcutKey: 'alt+t',
          transliterationEnabled: true
      };
      var control = new google.elements.transliteration.TransliterationControl(options);
		control.makeTransliteratable(['remarkId']);
		control.makeTransliteratable(['remarkId2']);
		
    }
    google.setOnLoadCallback(onLoad);
var TourCategoryArray =[];
var TourTypesArray =[];
var tourCloneMainDivCount=1;
	$(document).on("click",".tourCloneMainDivNewBtn",function(){
		var e = $("#tourCloneMainDivNew").clone(true);
		 e.attr({
				'id': 'newsTourCloneDivIdNew'+tourCloneMainDivCount,
				'class' : 'outerDivClsNew',
				'attr_countNew' : tourCloneMainDivCount
			});
			e.css("display","block");
			
		e.find(".tourCategoryNewCls").attr("id","tourCategoryNew"+tourCloneMainDivCount);
		e.find(".tourCategoryNewCls").attr("name","toursVOListNew["+tourCloneMainDivCount+"].tourCategoryId");
		
		e.find(".tourTypeNewCls").attr("id","tourTypeNew"+tourCloneMainDivCount);
		e.find(".tourTypeNewCls").attr("name","toursVOListNew["+tourCloneMainDivCount+"].tourTypeId");
		
		e.find(".tourDaysNewCls").attr("id","tourDaysNew"+tourCloneMainDivCount);
		e.find(".tourDaysNewCls").attr("name","toursVOListNew["+tourCloneMainDivCount+"].tourDays");
		
		e.find(".tourDescNewCls").attr("id","tourDescNew"+tourCloneMainDivCount);
		e.find(".tourDescNewCls").attr("name","toursVOListNew["+tourCloneMainDivCount+"].description");
		
		e.find(".newsTourRemoveBtnClsNew").attr("attr_div_id_New","newsTourCloneDivIdNew"+tourCloneMainDivCount);
		$("#toursNewBlockClonedId").append(e);

		if(TourCategoryArray != null && TourCategoryArray.length>0){
				for(var i in TourCategoryArray){
					$('#tourCategoryNew'+tourCloneMainDivCount).append('<option value="'+TourCategoryArray[i].id+'">'+TourCategoryArray[i].name+'</option>');
				}
				$('#tourCategoryNew'+tourCloneMainDivCount).chosen();
			}
			if(TourTypesArray != null && TourTypesArray.length>0){
				for(var i in TourTypesArray){
					$('#tourTypeNew'+tourCloneMainDivCount).append('<option value="'+TourTypesArray[i].id+'">'+TourTypesArray[i].name+'</option>');
				}
				$('#tourTypeNew'+tourCloneMainDivCount).chosen();
			}
			
		tourCloneMainDivCount=tourCloneMainDivCount+1;
	});	
	$(document).on("click",".newsTourRemoveBtnClsNew",function(){
		var divId = $(this).attr("attr_div_id_New");
		$("#"+divId).remove();
	}); 
/*New Code End*/

$(document).on("click",".closeRetrivalEditModalId",function(){
	$("#retrivalEditModalId").modal('hide');
	setTimeout(function(){
		$("body").addClass('modal-open');
		//$("#membersOverviewModalEdit").modal('show');
		
		$('#membersOverviewModalEdit').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		
	},500);
});
$(document).on("click",".closeMembersOverviewModalEdit",function(){
	$("#membersOverviewModalEdit").modal('hide');
	setTimeout(function(){
		$("body").addClass('modal-open');
		//$("#membersOverviewModal").modal('show');
		$('#membersOverviewModal').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
	},500);
});

initializeFile2();   
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursAction")));
wurl = wurl.replace("/PartyAnalyst","");
$(document).on("click",".submitedDataModal",function(){
	//$("#myModal").modal('show');
	$('#myModal').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
	
});

if($(window).width() > 500)
{
	$(".table").wrap( "<div class='table-responsive'></div>" );
}
	var customStartToursDate = moment().subtract(1,'month').startOf("month").format('DD/MM/YYYY')
	var customEndToursDate = moment().subtract(1,'month').endOf("month").format('DD/MM/YYYY');
	 defaultDateRangePickerForAppendBlock();
	 initializeMultiDateRanePicker();
	 $("#designationSlctBxId").chosen();
	 $("#memberSlctBxId").chosen();
	function initializeMultiDateRanePicker(){
		$("#toursDateRangePickerNew").daterangepicker({
			opens: 'left',
			 startDate: customStartToursDate,
			 endDate: customEndToursDate,
			locale: {
			  format: 'DD/MM/YYYY'
			},
			ranges: {
			   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
			   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay()), 'days'), moment().subtract(parseInt(getDay()), 'days')],
			   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			   'This Month': [moment().startOf('month'), moment().endOf('month')],
			   'This Year': [moment().startOf('Year'), moment()],
			   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
			}
		});
		/* $('#toursDateRangePickerNew').on('apply.daterangepicker', function(ev, picker) {
			getToursDetailsOverview();
		}); */
	}
	var designationIds = [];
	getTourBasicOverviewDtlsDesignationWise(customStartToursDate,customEndToursDate,designationIds); //default call 
	function getDay(){
		var date = new Date();
		var dd = date.getDate(); 
		return dd;
	}   
	
	function defaultDateRangePickerForAppendBlock(){
		 $("#tourDateId0").daterangepicker({
			singleDatePicker: true,
			maxDate:moment(),
			locale: {
				format:'DD/MM/YYYY' 
			},
		});	
	}
	

	
	
	
	
 var newsTourCloneCount=0;
	$(document).on("click",".addAppendTourBlockCls",function(){
		var c = $("#newsTourCloneMainDiv").clone(true);
		 c.attr({
				'id': 'newsTourCloneDivId'+newsTourCloneCount,
				'class' : 'outerDivCls',
				'attr_count' : newsTourCloneCount
			});
			c.css("display","block");
		c.find(".tourDateCls").attr("id","tourDateId"+newsTourCloneCount);
		c.find(".tourDateCls").attr("name","toursVOList["+newsTourCloneCount+"].tourDateId");
		
		c.find(".tourCategoryCls").attr("id","tourCategoryId"+newsTourCloneCount);
		c.find(".tourCategoryCls").attr("name","toursVOList["+newsTourCloneCount+"].tourCategoryId");
		c.find(".tourCategoryCls").attr("attr_count",newsTourCloneCount);
	
		c.find(".tourLocationCls").attr("id","tourLocationId"+newsTourCloneCount);
		c.find(".tourLocationCls").attr("name","toursVOList["+newsTourCloneCount+"].tourLocationId");
		c.find(".tourLocationCls").attr("attr_TourCount",newsTourCloneCount);
		
		c.find(".tourTypeCls").attr("id","tourTypeId"+newsTourCloneCount);
		c.find(".tourTypeCls").attr("name","toursVOList["+newsTourCloneCount+"].tourTypeId");
		
		c.find(".tourtextAreaCls").attr("id","tourtextAreaId"+newsTourCloneCount);
		
		c.find(".tourtextAreaCls").attr("name","toursVOList["+newsTourCloneCount+"].description");
		c.find(".newsTourRemoveBtnCls").attr("attr_div_id","newsTourCloneDivId"+newsTourCloneCount);
		
		c.find(".stateSelCls").attr("id","stateSelId"+newsTourCloneCount);
		c.find(".stateSelCls").attr("name","toursVOList["+newsTourCloneCount+"].stateId");
		c.find(".stateDivCls").attr("id","stateDivId"+newsTourCloneCount);
		c.find(".stateDivCls").attr("class","col-md-2 col-xs-12 col-sm-2 stateDivCls locationDivCls"+newsTourCloneCount);
		c.find(".stateSelCls").attr("attr_count",newsTourCloneCount);
		
		c.find(".districtSelCls").attr("id","districtSelId"+newsTourCloneCount);
		c.find(".districtSelCls").attr("name","toursVOList["+newsTourCloneCount+"].districtId");
		c.find(".districtDivCls").attr("id","districtDivId"+newsTourCloneCount);
		c.find(".districtDivCls").attr("class","col-md-2 col-xs-12 col-sm-2 districtDivCls locationDivCls"+newsTourCloneCount);
		c.find(".districtSelCls").attr("attr_count",newsTourCloneCount);
		
		c.find(".constituencySelCls").attr("id","constituencySelId"+newsTourCloneCount);
		c.find(".constituencySelCls").attr("name","toursVOList["+newsTourCloneCount+"].constituencyId");
		c.find(".constituencyDivCls").attr("id","constituencyDivId"+newsTourCloneCount);
		c.find(".constituencyDivCls").attr("class","col-md-2 col-xs-12 col-sm-2 constituencyDivCls locationDivCls"+newsTourCloneCount);
		c.find(".constituencySelCls").attr("attr_count",newsTourCloneCount);
		
		c.find(".tehMunSelCls").attr("id","tehMunSelId"+newsTourCloneCount);
		c.find(".tehMunSelCls").attr("name","toursVOList["+newsTourCloneCount+"].localBodyId");
		c.find(".tehMunDivCls").attr("id","tehMunDivId"+newsTourCloneCount);
		c.find(".tehMunDivCls").attr("class","col-md-2 col-xs-12 col-sm-2 tehMunDivCls locationDivCls"+newsTourCloneCount);
		c.find(".tehMunSelCls").attr("attr_count",newsTourCloneCount);
		
		c.find(".villWardSelCls").attr("id","villWardSelId"+newsTourCloneCount);
		c.find(".villWardSelCls").attr("name","toursVOList["+newsTourCloneCount+"].panchayatWardId");
		c.find(".villWardDivCls").attr("id","villWardDivId"+newsTourCloneCount);
		c.find(".villWardDivCls").attr("class","col-md-2 col-xs-12 col-sm-2 villWardDivCls locationDivCls"+newsTourCloneCount);
		c.find(".villWardSelCls").attr("attr_count",newsTourCloneCount);
		
		$("#addNewTourBlock").append(c);
			if(TourCategoryArray != null && TourCategoryArray.length>0){
				for(var i in TourCategoryArray){
					$('#tourCategoryId'+newsTourCloneCount).append('<option value="'+TourCategoryArray[i].id+'">'+TourCategoryArray[i].name+'</option>');
				}
				$('#tourCategoryId'+newsTourCloneCount).chosen();
			}
			if(TourTypesArray != null && TourTypesArray.length>0){
				for(var i in TourTypesArray){
					$('#tourTypeId'+newsTourCloneCount).append('<option value="'+TourTypesArray[i].id+'">'+TourTypesArray[i].name+'</option>');
				}
				$('#tourTypeId'+newsTourCloneCount).chosen();
			}
		
			$("#tourDateId"+newsTourCloneCount).daterangepicker({
				singleDatePicker: true,
				maxDate:moment(),
				locale: {
					format:'DD/MM/YYYY' 
				},
			});			
		newsTourCloneCount=newsTourCloneCount+1;
	});
	$(document).on("click",".newsTourRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	}); 
</script>
</body>
</html>
