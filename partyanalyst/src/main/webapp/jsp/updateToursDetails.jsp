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

<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>  
<script type="text/javascript" src="js/yahoo/get-min.js"></script> 	    
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-10 col-xs-12 col-sm-10">
                        	<h4 class="panel-title">tour details overview</h4>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-2">
                        	<select class="selectChosen">
                            	<option>Last Month(September)</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="panel-body pad_0 border_0">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<table class="table table-condensed tableOverview">
                            	<thead>
                                	<th></th>
                                    <th>Total Leaders</th>
                                    <th>Submited Leaders</th>
                                    <th>Not Submited Leaders</th>
                                    <th>Submited Tours</th>
                                    <th>Average Tours</th>
                                </thead>
                                <tbody>
                                	<tr>
                                    	<td>General Secretary</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                    </tr>
                                    <tr>
                                        <td>Secretary</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                    </tr>
                                    <tr>
                                        <td>Organising Secretary</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                    </tr>
                                    <tr>
                                        <td>Member Of Parliament</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                   </tr>
                                   <tr>
                                        <td>District President</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                   </tr>
                                   <tr>
                                        <td>Constituency Incharge</td>
                                        <td>5</td>
                                        <td><span class="submitedDataModal">5</span></td>
                                        <td>5</td>
                                        <td>5</td>
                                        <td>5</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<form name="submitApplication" method="post">
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capital">update tour details</h4>
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
											<select class="selectChosen" name="toursInputVO.month">
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
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6">
											<label>Insert Year</label>
											<input type="text" length="4" placeholder="Type Year Here" id="yearId" class="form-control" name="toursInputVO.year"></input>
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6 ownDivCls"  style="display:none">
											<label Id="ownLabelId">Own DIstrict</label>
											<input type="text" length="2" placeholder="Type No of Tour Here" id="ownSelectBoxId" class="form-control" name="toursInputVO.ownTours"></input>
											<input type="hidden" id="ownLocationScopeId" name="toursInputVO.ownLocationScopeId">
											<input type="hidden" id="ownLocationScopeValue" name="toursInputVO.ownLocationId">
										</div>
										<div class="col-md-3 col-xs-12 col-sm-6 inchageDivCls" style="display:none">
											<label id="inchargeLableId">Incharge DIstrict</label>
											<input type="text" length="2" placeholder="Type No of Tour Here" id="inchargeSelectBoxId" class="form-control" name="toursInputVO.inchargeTours"></input>
											<input type="hidden" id="inchageLocationScopeId" name=" toursInputVO.inchargeLocationScopeId">
											<input type="hidden" id="inchageownLocationScopeValue" name="toursInputVO.inchargeLocationId">
										</div>    
									</div>
									<div class="row m_top10">  
										<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
											<label>Add Comment</label>
											<textarea class="form-control" name="toursInputVO.remarks"></textarea>    
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row showDivCls" id="uploadFlDivId" style="display:none;"> 
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
								<p class="m_0 text-success font_16 font_weight">UPLOAD SCAN COPY</p>
								<input type="file" id="filer_input3" multiple="multiple"  name="fileImage" class="m_top20"/>
							</div>
						</div>
						
						<div class="row showDivCls" style="display:none;">
							<div class="col-md-4 col-md-offset-4">
								<button class="btn btn-success btn-block" onclick="savingApplication();" type="button">SUBMIT APPLICATION</button>
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
        <h4 class="modal-title" id="myModalLabel">General Secretarys overview<small class="text-muted">- Last Month(September)</small></h4>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-md-12 col-xs-12 col-sm-12">
            	<div class="bg_ED block">
                	<table class="table">
                    	<tr>
                        	<td>
                            	<h4 class="text-capitalize text-muted">Total Leaders</h4>
                                <h3>5</h3>
                            </td>
                            <td>
                            	<h4 class="text-capitalize text-muted">Total Leaders</h4>
                                <h3>5<small class="text-success">80%</small></h3>
                            </td>
                            <td>
                            	<h4 class="text-capitalize text-muted">Total Leaders</h4>
                                <h3>5<small class="text-success">20%</small></h3>
                            </td>
                            <td>
                            	<h4 class="text-capitalize text-muted">Total Leaders</h4>
                                <h3>5</h3>
                            </td>
                            <td>
                            	<h4 class="text-capitalize text-muted">Total Leaders</h4>
                                <h3>5</h3>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="col-md-12 col-xs-12 col-sm-12">
            	<table class="table table-condensed tableModal">
                	<thead class="text-capital">
                    	<th>general secretary name</th>
                        <th>total tours submited</th>
                        <th>comments</th>
                        <th>attachments</th>
                    </thead>
                    <tr>
                    	<td>B Jaya Nageshwar Reddy</td>
                        <td>7</td>
                        <td>Tour comments</td>
                        <td>
                        	<img src="" class="img-responsive" alt=""/>
                        	<button class="btn btn-success editBtn">EDIT</button>
                        </td>
                    </tr>
                    <tr>
                    	<td>B Jaya Nageshwar Reddy</td>
                        <td>7</td>
                        <td>Tour comments</td>
                        <td>
                        	<img src="" class="img-responsive" alt=""/>
                        	<button class="btn btn-success editBtn">EDIT</button>
                        </td>
                    </tr>
                    <tr>
                    	<td>B Jaya Nageshwar Reddy</td>
                        <td>7</td>
                        <td>Tour comments</td>
                        <td>
                        	<img src="" class="img-responsive" alt=""/>
                        	<button class="btn btn-success editBtn">EDIT</button>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-md-12 col-xs-12 col-sm-12">
            	<h4 class="panel-title text-capital">uploaded attachments</h4>
                <div class="block">
                    <button class="btn btn-success deleteBtn pull-right">delete</button>
                	<button class="btn btn-success viewBtn pull-right">view</button>
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
<script src="js/Tours/updateToursDetails.js" type="text/javascript"></script>  
<script type="text/javascript">
//$("#uploadFile").dropzone({ url: "/file/post" });
$(document).on("click",".submitedDataModal",function(){
	$("#myModal").modal('show');
});
$('.selectChosen').chosen({width:'100%'})
</script>
</body>
</html>
