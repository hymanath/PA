<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tours </title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="tourDetails/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
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
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-10 col-xs-12 col-sm-10">
                        	<h4 class="panel-title text-capital">TOUR DETAILS OVERVIEW</h4>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-2">
						      <span class="input-group pull-right " style="width:200px;">
									<input type="text" id="toursDateRangePicker" style="width:180px" class="form-control" />
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
							 </span>
                        	<!--select class="selectChosen">
                            	<option>Last Month(September)</option>
                            </select>-->
                        </div>
                    </div>
                </div>
                <div class="panel-body pad_0 border_0">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
					  <div ><center ><img style="display: none;" src="images/icons/loading.gif" id="overAllLeaderDivProcessImgId"></center></div>
						<div id="overAllLeaderDivId"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<form name="submitApplication" method="post">
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capital">UPDATE TOUR DETAILS</h4>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>Designation Level</label>
                            <select  class="selectChosen" id="designationSlctBxId">
                            	<option value="0">Select Designation Level</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                        	<label>Select Name</label>
                            <select class="selectChosen" id="memberSlctBxId" name="toursInputVO.candidateId">    
                            	<option value="0">Select Name</option>
                            </select>
                        </div>
                    </div>
                    <div class="row m_top20 hideProfileDivCls">
                    	 <div class="col-md-12 col-xs-12 col-sm-12">
                         	<div class="block">
							<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="profileProcessingImgId"></center></div>
                             <div id="selectedMemberDtslDivId"></div>
                            </div>
                         </div>
                    </div>
					    
						<div class="row m_top20 showDivCls" style="display:none;">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<h4 class="panel-title text-capital">update your details</h4>
								<div class="block">
									<div class="row">  
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Select Month</label>
											<select id="monthSelectBoxId" class="selectChosen" name="toursInputVO.month">
											    <option value="0">Select Month</option>
												<option value="January">January</option>
												<option value="February">February</option>
												<option value="March">March</option>
												<option value="April">April</option>
												<option value="May">May</option>
												<option value="June">June</option>
												<option value="July">July</option>
												<option value="August">August</option>
												<option value="September">September</option>
												<option value="October">October</option>
												<option value="November">November</option>
												<option value="December">December</option>
											</select>
											<span id="errMnthId" style="color:red;"></span>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">  
											<label>Insert Year</label>  
											<select id="yearId" class="selectChosen" name="toursInputVO.year">
											    <option value="0">Select Year</option>
												<option value="2016">2016</option>
												<option value="2015">2015</option>     
											</select>
											<span id="errYearId" style="color:red;"></span>   
										</div>  
										<div class="col-md-3 col-xs-12 col-sm-6 ownDivCls"  style="display:none">
											<label Id="ownLabelId">Own DIstrict</label>
											<input type="text" length="2" placeholder="Type No of Tour Here" id="ownSelectBoxId" attr_err_id="ownSelectBoxerrId" class="form-control clearFieldCls" name="toursInputVO.ownTours"></input>
											<input type="hidden" id="ownLocationScopeId" name="toursInputVO.ownLocationScopeId">
											<input type="hidden" id="ownLocationScopeValue" name="toursInputVO.ownLocationId">
											<span id="ownSelectBoxerrId" class="textErrCls" style="color:red;"></span>
										</div>  
										<div class="col-md-3 col-xs-12 col-sm-6 inchageDivCls" style="display:none">  
											<label id="inchargeLableId">Incharge DIstrict</label>
											<input type="text" length="2" placeholder="Type No of Tour Here" id="inchargeSelectBoxId" attr_err_id="inSelectBoxerrId" class="form-control clearFieldCls" name="toursInputVO.inchargeTours"></input>
											<input type="hidden" id="inchageLocationScopeId" name=" toursInputVO.inchargeLocationScopeId">  
											<input type="hidden" id="inchageownLocationScopeValue" name="toursInputVO.inchargeLocationId">
											<span id="inSelectBoxerrId" class="textErrCls" style="color:red;"></span>
										</div>    
									</div>  
									<div class="row m_top10">  
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<label>Add Comment</label>
											<textarea class="form-control clearFieldCls" name="toursInputVO.remarks"></textarea>    
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row showDivCls" id="uploadFlDivId" style="display:none;"> 
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
								<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3>  
								<input type="file" id="filer_input3" multiple="multiple"  name="files[]" class="m_top20"/>        
							</div>
						</div>            
						
						<div class="row showDivCls" style="display:none;">  
							<div class="col-md-4 col-md-offset-4">
								<span class="updateTourStatusCls"></span>
								<button class="btn btn-success btn-block" onclick="savingApplication();" type="button">SUBMIT APPLICATION</button>
								<span id="successSpanId"></span>  
							</div>   
							  <div class="col-md-12 col-sm-12 col-xs-12" id="statusId"></div>
						</div>  
					</form>   
					<div class="row m_top20 otherMemberBlockCls" style="display:none;">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="panel-title text-capital">Search Member By</h4>
                        	<div class="block">
                            	<div class="row m_top20">
								 <div class="col-md-6 col-xs-12 col-sm-6">
									<label class="radio-inline">
									  <input type="radio" attr_type="MemberShip Number" checked class="searchRadioBtnCls" name="optradio">MemberShip Number
									</label>
									<label  class="radio-inline">
									  <input type="radio" attr_type="Mobile Number"  class="searchRadioBtnCls" name="optradio">Mobile Number
									</label>
									<label  class="radio-inline">
									  <input type="radio" attr_type="Name"  class="searchRadioBtnCls" name="optradio">Name
									</label>
									</div>  
								 </div>
								<div class="row m_top20">
                                	<div class="col-md-4 col-xs-12 col-sm-6">
                                        <select class="selectChosen" id="constituencySelectBoxId">
                                        	<option value="0">Select Constituency</option>
                                        </select>
										<span id="constituencyErrorId" style="color:red"><span>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-6">
                                    	<input type="text" class="form-control" id="searchValueInputBoxId" placeholder="Enter MemberShip Number"/>
                                    </div>
									<div class="col-md-4 col-xs-12 col-sm-6">
                                    	<input type="button" class="btn btn-success" id="searchMemberBtnId" value="Search"/>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header">    
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><small class="text-muted" id="timeId"></small></h4>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-md-12 col-xs-12 col-sm-12">
            	<div class="bg_ED block" id="desigDtlsId">
						
                </div>
				<div >
						<center >
							<img style="display: none;" src="images/icons/loading.gif" id="desigDtlsProcessImgId">
						</center>
					</div>
            </div>
            <div class="col-md-12 col-xs-12 col-sm-12">
				<div id="memDtlsId">         
					
				</div>
				<div >
						<center >
							<img style="display: none;" src="images/icons/loading.gif" id="memDtlsProcessImgId">  
						</center>
					</div>
            </div>
             
        </div>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="myModalUpdateId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header">    
        <button type="button" class="close closeButtonCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelUpdateId"><small class="text-muted" id="timeId"></small></h4>
      </div>
      <div class="modal-body">  
        <div class="row">
            <div class="col-md-12 col-xs-12 col-sm-12">
				<div id="memDtlsUpdateId">         
					
				</div>
				<div >
					<center >
						<img style="display: none;" src="images/icons/loading.gif" id="memDtlsUpdateProcessImgId">    
					</center>
				</div>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<!-- for file uploader -->
<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/customNominatedPost.js?v=1.0.5"></script>  
<script type="text/javascript" src="dragAndDropPhoto/js/updateTourFile.js?v=1.0.5"></script>          
<!-- for file uploader -->
<script src="js/Tours/updateToursDetails.js" type="text/javascript"></script>     
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>    
<script type="text/javascript">
//$("#uploadFile").dropzone({ url: "/file/post" });
$(document).on("click",".submitedDataModal",function(){
	$("#myModal").modal('show');
});
$('.selectChosen').chosen({width:'100%'})
</script>
</body>
</html>  
