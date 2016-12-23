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
<!-- for file uploader -->
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
<!-- for file uploader -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>  
<script type="text/javascript" src="js/yahoo/get-min.js"></script> 	  
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>  
</head>
<style>
.chosen-container{width:100% !important}
</style>

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
										<input type="text" id="toursDateRangePickerNew" style="width:180px" class="form-control" />
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
								 </span>
							</div>
						</div>
					</div>
					<div class="panel-body">
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
					</div>
				</div>
			</div>
			
			<form name="submitApplication" method="post">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Update Your Details</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>Designation Level</label>
									<select  class="form-control" id="designationSlctBxId">
									<option value="0">Select Designation Level</option>
								</select>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-3">
									<label>Select Name</label>
									<select class="form-control" id="memberSlctBxId" name="toursInputVO.candidateId">    
									<option value="0">Select Name</option>
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
															
															<h4 class="panel-title text-capital" style="display:none" id="selectedProfileId">selected profile</h4>
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
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10 showDivCls" >
									<h4 class="panel-title text-capital">update tour details</h4>
									<div class="panel panel-default">
										<div class="panel-body borderGreen">
											<!-- <i class="closeIcon glyphicon glyphicon-remove"></i> -->
											<div class="row">
												<div class="col-md-3 col-xs-12 col-sm-3">
													<label>Tour Date</label>
													<div class="input-group inputGCustom">
														<input type="text" class="form-control" id="tourDateId0" name="toursInputVO.tourDateId">
														<span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
														</span>
													</div>
												</div>
												<div class="col-md-4 col-xs-12 col-sm-4">
													<label>Tour Category</label>
													<select class="form-control" id="tourCategoryId0" name="toursInputVO.tourCategoryId">    
													   <option value="0">Tour Category</option>
													 </select>
												</div>
												<div class="col-md-2 col-xs-12 col-sm-2">
													<label>Tour Location</label>
													<select class="form-control" id="tourLocationId0" name="toursInputVO.tourLocationId">
														<option>a</option>
													</select>
												</div>
												<div class="col-md-3 col-xs-12 col-sm-3">
													<label>Tour Type</label>
													<select class="form-control" id="tourTypeId0" name="toursInputVO.tourTypeId">    
													   <option value="0">Tour Type</option>
													 </select>
												</div>
											</div>
											<div class="row m_top10">
												<div class="col-md-12 col-xs-12 col-sm-12">
													<label>Add Comment/Tour Description</label>
													<textarea class="form-control" id="tourtextAreaId0" name="toursInputVO.tourtextAreaId"></textarea>
												</div>
											</div>
										</div>
										<div id="addNewTourBlock"></div>
										<div class="panel-footer borderGreen text-right">
											<button type="button" class="btn btn-success addAppendTourBlockCls">+ ADD TOUR</button>
										</div>
									</div>
									<div class="row showDivCls" id="uploadFlDivId" style="display:none;">
										<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
											<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3>  
											<input type="file" id="update_TourFileId2" multiple="multiple"  name="files[]" class="m_top20"/>
											<span id="errFileId" style="color:red;margin-left:470px;"></span>   
										</div>
									</div>  
									<div class="row showDivCls" style="display:none;"> 
										<div class="col-md-4 col-md-offset-4">
											<span class="updateTourStatusCls"></span>
											<button type="button" class="btn btn-success btn-block" onclick="savingApplication();" type="button">SUBMIT APPLICATION</button>
											<span id="successSpanId"></span>  
										</div>   
										<div class="col-md-12 col-sm-12 col-xs-12" id="statusId"></div>
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<!--- new tour block append--->
	<div id="newsTourCloneMainDiv" style="display:none;">
	<div class="panel panel-default" id="newsTimeCloneDivId">
		<div class="panel-body borderGreen">
			<i class="closeIcon glyphicon glyphicon-remove newsTourRemoveBtnCls"></i>
			<div class="row">
				<div class="col-md-3 col-xs-12 col-sm-3">
					<label>Tour Date</label>
					<div class="input-group inputGCustom">
						<input type="text" class="form-control tourDateCls" id="" name="toursInputVO.tourDateId">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
					</div>
				</div>
				<div class="col-md-4 col-xs-12 col-sm-4">
					<label>Tour Category</label>
					<select class="form-control tourCategoryCls" id="" name="toursInputVO.tourCategoryId">    
					   <option value="0">Tour Category</option>
					 </select>
				</div>
				<div class="col-md-2 col-xs-12 col-sm-2">
					<label>Tour Location</label>
					<select class="form-control tourLocationCls" id="" name="toursInputVO.tourLocationId">
						<option>a</option>
					</select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-3">
					<label>Tour Type</label>
					<select class="form-control tourTypeCls" id="" name="toursInputVO.tourTypeId">    
					   <option value="0">Tour Type</option>
					 </select>
				</div>
			</div>
			<div class="row m_top10">
				<div class="col-md-12 col-xs-12 col-sm-12">
					<label>Add Comment/Tour Description</label>
					<textarea class="form-control tourtextAreaCls" id="" name="toursInputVO.tourtextAreaId"></textarea>
				</div>
			</div>
		</div>
	</div>
	</div>
	
<script src="Assets/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/Tours/toursDetails.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
 <!-- for file uploader  -->              
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>       
<script type="text/javascript" src="dragAndDropPhoto/js/updateTourFile.js?v=1.0.5"></script> 
<script type="text/javascript" src="dragAndDropPhoto/js/updateTourFile2.js?v=1.0.5"></script>                
<!-- for file uploader -->
<!-- <script src="js/Tours/updateToursDetails.js" type="text/javascript"></script>     -->
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
initializeFile2();   
var windowUrl = window.location.href;
var wurl = windowUrl.substr(0,(windowUrl.indexOf("/updateToursDetailsAction")));
wurl = wurl.replace("/PartyAnalyst","");
$(document).on("click",".submitedDataModal",function(){
	$("#myModal").modal('show');
});
$( document ).ready(function() {
    $(".showDivCls").hide();
});
 
 var TourCategoryArray =[];
 var TourTypesArray =[];
 defaultDateRangePickerForAppendBlock();
 initializeMultiDateRanePicker();
 $("#designationSlctBxId").chosen();
 $("#memberSlctBxId").chosen();
 
	function initializeMultiDateRanePicker(){
		$("#toursDateRangePickerNew").daterangepicker({
			opens: 'left',
			 startDate: moment().startOf('month'),
			 endDate: moment().endOf('month'),
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
			   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
			}
		})
	}
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
				format:'YYYY-MM-DD' 
			},
		});	
	}
	

 var newsTourCloneCount=1;
	$(document).on("click",".addAppendTourBlockCls",function(){
		var c = $("#newsTourCloneMainDiv").clone(true);
		 c.attr({
				'id': 'newsTourCloneDivId'+newsTourCloneCount
			});
			c.css("display","block");
		c.find(".tourDateCls").attr("id","tourDateId"+newsTourCloneCount);
		c.find(".tourDateCls").attr("name","toursInputVO.tourDateId");
		
		c.find(".tourCategoryCls").attr("id","tourCategoryId"+newsTourCloneCount);
		c.find(".tourCategoryCls").attr("name","toursInputVO.tourCategoryId");
	
		c.find(".tourLocationCls").attr("id","tourLocationId"+newsTourCloneCount);
		c.find(".tourLocationCls").attr("name","toursInputVO.tourLocationId");
		
		c.find(".tourTypeCls").attr("id","tourTypeId"+newsTourCloneCount);
		c.find(".tourTypeCls").attr("name","toursInputVO.tourTypeId");
		
		c.find(".tourtextAreaCls").attr("id","tourtextAreaId"+newsTourCloneCount);
		
		c.find(".tourtextAreaCls").attr("name","toursInputVO.tourtextAreaId");
		c.find(".newsTourRemoveBtnCls").attr("attr_div_id","newsTourCloneDivId"+newsTourCloneCount);
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
		
			$('#tourLocationId'+newsTourCloneCount).chosen();
			$("#tourDateId"+newsTourCloneCount).daterangepicker({
				singleDatePicker: true,
				maxDate:moment(),
				locale: {
					format:'YYYY-MM-DD' 
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
