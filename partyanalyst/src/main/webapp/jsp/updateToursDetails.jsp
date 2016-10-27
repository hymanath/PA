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
                            <select class="selectChosen" id="memberSlctBxId">
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
                                        <select class="selectChosen">
                                        	<option>Last Month(September)</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-xs-12 col-sm-6">
                                    	<label>Select Year</label>
                                        <select class="selectChosen">
                                        	<option>2016</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 col-xs-12 col-sm-6 ownDivCls"  style="display:none">
                                    	<label Id="ownLabelId">Own DIstrict</label>
                                        <select id="ownSelectBoxId" class="selectChosen">
                                        	<option>22</option>
                                        </select>
									  <input type="hidden" id="ownLocationScopeId" name="toursInputVO.ownLocationScopeId">
									  <input type="hidden" id="ownLocationScopeValue" name="toursInputVO.ownLocationId">
                                    </div>
                                    <div class="col-md-3 col-xs-12 col-sm-6 inchageDivCls" style="display:none">
                                    	<label id="inchargeLableId">Incharge DIstrict</label>
                                        <select id="inchargeSelectBoxId" class="selectChosen">
                                        	<option>04</option>
                                        </select>
									  <input type="hidden" id="inchageLocationScopeId" name=" toursInputVO.inchargeLocationScopeId">
									  <input type="hidden" id="inchageownLocationScopeValue" name="toursInputVO.inchargeLocationId">
                                    </div>
                                </div>
                                <div class="row m_top10">
                                    <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                                    	<label>Add Comment</label>
                                        <textarea class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row m_top20 showDivCls"  style="display:none;">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="panel-title text-capital">upload attachment:</h4>
                            <form action="" method="post" enctype="multipart/form-data">
                              <input type="file" name="file" id="uploadFile"/>
                            </form>
                        </div>
                    </div>
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
