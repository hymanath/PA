<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Party Meetings - MOM & ATR POINTS</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>

<link href="css/Training/css/basic.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/dropzone.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
  
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  
  
<style>
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader {
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px;
}
body,h1,h2,h3,h4,h5,h6{color:#666 !important}
.text-bold{font-weight:bold}
.m_0
{
	margin:0px
}
.uploadBox
{
	border:1px solid #ccc;
	padding:8px;
}
.grievance-training ul li
{
    border:1px solid #999;
    list-style:none;
}
.grievance-training ul li p
{
    margin:0px;
}
.grievance-training ul li .data
{
    padding:10px;
}
.grievance-training ul li .data-edit .row
{
    padding:10px;
}
.mouse-over
    {
        padding:0px;
    }
    
    .mouse-over:hover:after
    {
        content:"click here to expand";
        position:absolute;
        left:35px;
        z-index:9999;
        color:#fff;
        background:rgba(0,0,0,0.5);
        padding:10px;
        font-size:22px;
		margin-top:20px;
    }
	.alertstyle{background: none repeat scroll 0 0 rgba(0, 0, 0, 0.8);color:#fff !important;height:75px;width:420px;}
	.alerttop{top:215px;}
	.mleft_190{margin-left:190px;font-weight:bold;}
	.mtop-5{margin-top:-5px;}
	.alertstyle h5{font-weight:bold;}
	.colorchange{color:#fff !important;}
	.bt{
		border:none;
	}
</style>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- YUI Dependency files (End) -->
	
</head>
<body>
<main>
    <div class="container">
        <div class="row">
            <section>
                <div class="col-md-8 col-md-offset-2 col-xs-12 col-sm-10 col-sm-offset-1">
                    <div class="panel panel-default">
                        <div class="panel-heading" style="background-color:#CCC">
                            <h4 class="panel-title text-uppercase" ><span id="meetingType"></span>&nbsp; <i><small id="location" ></small></i></h4>
							
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-xs-12 col-sm-12 ">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">Meeting Minutes</h4>
                                        </div>
										<div class="table-responsive">
											<table class="table table-bordered">
												<tr>
													<td>
														<h4 class="m_0 text-center">Total Minute Points </h4>
														<h2 class="m_0 text-center"><span id="minitePointsCount">0</span></h2>
													</td>
													<td>
														<h4 class="m_0 text-center">Total Documents Uploaded </h4>
														<h2 class="m_0 text-center"><span id="miniteDocsCount">0</span></h2>
													</td>
												</tr>
											</table>
										</div>
										<!--<div style="border:1px solid #ababab; margin:5px;padding:5px;">
											<h4>Total MinitePoints : 
											<span style="margin-left:20px;"></span>
										</div>-->
										
                                        <div class="panel-body">
											<div id="addMoreDiv">
                                                <div class="input-group bin-div m_top10" id="list1" style="display:none">
                                                    <span type="text" class="form-control" id="minutes1"></span>
                                                    <span class="input-group-addon trash" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" attr_minuteid="0" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-ok"></i>
                                                    </span>
                                                </div>
                                            </div>   
                                           
                                                <div class="input-group bin-div m_top10" id="list" style="display:none;">
                                                    <input type="text" class="form-control txtbox"></input>
                                                    <span class="input-group-addon trash" style="background-color:#CCC;cursor:pointer"  title="Delete">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" style="background-color:#CCC;cursor:pointer;border-left:1px solid #ddd"  title="Save">
														<i class="glyphicon glyphicon-ok"></i>
													</span>
                                                </div>
                                           
                                            <button class="btn btn-primary btn-xs pull-right addMeetMint" style="border-radius:1px;">ADD</button>
											<br/><br/>
											
												  
													
												 
											
											<!-- CLONABLE DIV FOR FILE -->
											<div style="display:none;" id="fileDiv" class="row col-md-12">
												<input type="file" class="m_top10 fileCls col-md-6" name="imageForDisplay" style="width: 225px;"/>
												<div class="col-md-6"><span class="btn btn-primary btn-xs m_top10 removeBtnCls "  title="Remove File"><i class="glyphicon glyphicon-minus"></i></span></div>
											</div>
											
											
                                            <div class="row">
												<h4 style="font-weight:bold;"> UPLOAD MOM DOCUMENTS</h4>
												<div class="" id="mintueDocumentDivId"></div>
                                                <div class="col-md-12">
                                                    <form id="uploadMinutesDocs" name="uploadMinutesDocs" class="uploadBox">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="fileDivId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFiles"></div>
														<input type= "button" value="Upload Minutes Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-primary btn-sm" id="uploadMinutesDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingId"/>
														<input type="hidden" name="partyMeetingType" value="MINUTE"/>
													</form>
													<button class="btn btn-primary btn-xs pull-right m_top20 "   title="Add one more file" id="addFiles"><i class="glyphicon glyphicon-plus"></i></button>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
                                            </div>
											<!-- meeting docs -->
											
												 
											<div class="row">
												<h4 style="font-weight:bold;"> UPLOAD MEETING RELATED PHOTOS AND DOCUMENTS</h4>
												<div class="" id="meetingDocumentDivId"></div>
                                                <div class="col-md-12">
                                                    <form id="uploadMeetingDocs" name="uploadMeetingDocs" class="uploadBox">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="meetFileDivId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraMeetFiles"></div>
														<input type= "button" value="Upload Meeting Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-primary btn-sm" id="uploadMeetingDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingMeetId"/>
														<input type="hidden" name="partyMeetingType" value="MEETING"/>
													</form>
													<button class="btn btn-primary btn-xs pull-right m_top20 "   title="Add one more file" id="addMeetingFiles"><i class="glyphicon glyphicon-plus"></i></button>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
                                            </div>
											<!-- meeting docs -->
                                        </div>
                                    </div>
                                </div>
                               <!--  <div class="col-md-6 ">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">ATR</h4>
                                        </div>
										
										<table class="table table-bordered">
											<tr>
												<td>
													<h4 class="m_0 text-center">Total ATR Points </h4>
													<h2 class="m_0 text-center"><span id="atrPointsCount">0</span></h2>
												</td>
												<td>
													<h4 class="m_0 text-center">Total Documents Uploaded </h4>
													<h2 class="m_0 text-center"><span id="atrDocsCount">0</span></h2>
												</td>
											</tr>
										</table>
										
                                            <div class="panel-body">
											<div>
                                                <div id="atrDivId"></div>
											<div class="pull-right m_top10">
											<button class="btn btn-primary btn-xs addingRequests" style="border-radius:1px;">ADD</button>
											</div>
											</div>
											<br/><br/>
											
												
										
											<div class="row">
												<h4 style="font-weight:bold;"> UPLOAD ATR DOCUMENTS</h4>	
												<div  class="" id="atrDocumentDivId"></div>
												 <div class="col-md-12">
                                                    <form id="uploadATRDocs" name="uploadATRDocs" class="uploadBox	">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="atrFileId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFilesATR"></div>
														<input type= "button" value="Upload ATR Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-primary btn-sm" id="uploadATRDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingATRId"/>
														<input type="hidden" name="partyMeetingType" value="ATR"/>
													</form>
													<button class="btn btn-primary btn-xs pull-right m_top20" id="addATRFiles"><i class="glyphicon glyphicon-plus"></i></button>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
											</div>
                                    </div>
                                </div>
                            </div>-->
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>
<!---------alert pop up----------------->
<div class="modal fade" id="deletedmsg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Are you Sure,want to Delete This Meeting Minutes ?</h4>
      </div>
     
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="deletedMinMsg" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="deletedmsgAtr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Are you Sure,want to Delete This ATR  ?</h4>
      </div>
     
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="AtrdeletedMsg" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="mintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width: 80%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">MOM Points</h4>
      </div>
	  <div class="modal-body">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<label>Press Alt+t to toggle between Telugu & English</label>
				<textarea rows="5" id="meetRaised" class="form-control"></textarea>
				<div id="momError" style="color:red;"></div>
			</div>
		</div>
		<div class="row m_top20">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<h4 class="panel-title">Action Type</h4>
				<label class="radio-inline" name="actionTypeName">
					<input type="radio" class="checkCls radioBtnGenCls" id="generalBtnId" name="checkRdoBtnname" value="N" checked/>General
				</label>
				<label class="radio-inline" name="actionTypeName">
					<input type="radio" class="checkCls radioBtnActCls" id="actionableBtnId" name="checkRdoBtnname"  value="Y"/>Actionable
				</label>
			</div>
			 <div class="panel-body">
                        	<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-3" id="meetingLvlId">
									<label>Meeting Level</label>
									<span id="meetingLocationErrorMessage" style="color: red;"></span>
										<select class="form-control" id="meetingLocationLevel"></select>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMeetingsList" style="width:20px;height:20px;display:none;"/>
								</div>	
								
                            	<!--<div class="col-md-4 col-xs-12 col-sm-3">
								
									<label>Select Meeting Name/Type Of Meeting</label>
									<select class="form-control" id="typeOfMeeting">
										<option> Select Meeting Type </option>
									</select>
									<span id="typeofMeetingErrorMessage" style="color: red;"></span>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgFortypeOfMeeting" style="width:20px;height:20px;display:none;"/>
									
                                </div>-->
								
								
                            	<!--<div class="col-md-3">
                                	<label>Meeting Location</label>
                                    <select class="form-control" id="meetingLocation"></select>
                                </div>-->
                            	<!--<div class="col-md-3">
                                	<label>Meeting Called By</label>
                                    <select class="form-control" disabled>
                                    	<option>District level</option>
                                    </select>
                                </div>-->
                              <!-- 	<div class="col-md-3">
							  balu
                                	<label >Select Date</label>
									<div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc ;width:350px !important;">
									  <i class="glyphicon glyphicon-calendar"></i><div class="caret"></div>
									  <span style="margin-left: 25px;"></span> 
									</div>
									<div class="input-group">
                                    	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="reportrange">
										<span class="caret"></span>
										<span></span>
                                    </div> 
								</div>-->
								<!--<div class="col-md-3">
                                	<label>Meeting End Date</label> 
									<div class="input-group">
                                    	<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="endDate">
                                    </div>
								</div>-->	
								<div class="col-md-4 col-xs-12 col-sm-4" id="stateShowId" style="display:none;">
										<label>State</label>
										<span id="stateErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="statesDivId">
										<!--<option>Select State</option>-->
										</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-6" id="DistrictShowId" style="display:none;">
										<label>District</label>
										<span id="districtErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="districtId">
										<!--<option>Select District</option>-->
										</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6" id="ConstShowId" style="display:none;">
										<label>Constituency</label>
										<span id="ConsErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="constituencyId" name="constBox" value="constitunecyValue">
										<!--<option>Select Constituency</option>-->
										</select>
								</div>
								<div class="col-md-4 col-xs-12 col-sm-6" id="ManTwnDivShowId" style="display:none;">
										<label>Mandal/Town/Division</label>
										<span id="ManErrorMSgShow" style="color: red;"></span>
										<select class="form-control" id="manTowDivId">
										<option>Select Mandal/Town/Division</option>
										</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-6" id="VillWardShowId" style="display:none;">
									<label>Village/Ward</label>
										<span id="VillErrorMSgShow" style="color: red;"></span>
									<select class="form-control" id="villWardId">
									<option>Select Village/Ward</option>
									</select>
								</div>
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForDist" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForcons" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
								
								<div class="col-md-1" style="height: 44px; width: 10px;">
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForman" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
								</div>
                            </div>
					 </div>
			<!--<div class="col-md-4 col-xs-12 col-sm-3">
				<label>Meeting Level</label>
				<span id="meetingLocationErrorMessage" style="color: red;"></span>
					<select class="form-control" id="meetingLocationLevel"></select>
				<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMeetingsList" style="width:20px;height:20px;display:none;"/>
			</div>	
			
			<!--<div class="col-md-4 col-xs-12 col-sm-3">
			
				<label>Select Meeting Name/Type Of Meeting</label>
				<select class="form-control" id="typeOfMeeting">
					<option> Select Meeting Type </option>
				</select>
				<span id="typeofMeetingErrorMessage" style="color: red;"></span>
				<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgFortypeOfMeeting" style="width:20px;height:20px;display:none;"/>
				
			</div>-->
			<!--<div class="col-md-4 col-xs-12 col-sm-4" id="stateShowId" style="display:none;">
				<label>State</label>
				<span id="stateErrorMSgShow" style="color: red;"></span>
				<select class="form-control" id="statesDivId">
				<!--<option>Select State</option>-->
				<!--</select>
			</div>
			<div class="col-md-4 col-xs-12 col-sm-6" id="DistrictShowId" style="display:none;">
				<label>District</label>
				<span id="districtErrorMSgShow" style="color: red;"></span>
				<select class="form-control" id="districtId">
				<!--<option>Select District</option>-->
				<!--</select>
			</div>
			<div class="col-md-4 col-xs-12 col-sm-6" id="ConstShowId" style="display:none;">
				<label>Constituency</label>
				<span id="ConsErrorMSgShow" style="color: red;"></span>
				<select class="form-control" id="constituencyId" name="constBox">
				<!--<option>Select Constituency</option>-->
				<!--</select>
			</div>
			<div class="col-md-4 col-xs-12 col-sm-6" id="ManTwnDivShowId" style="display:none;">
				<label>Mandal/Town/Division</label>
				<span id="ManErrorMSgShow" style="color: red;"></span>
				<select class="form-control" id="manTowDivId">
				<option>Select Mandal/Town/Division</option>
				</select>
			</div>
			<div class="col-md-4 col-xs-12 col-sm-6" id="VillWardShowId" style="display:none;">
				<label>Village/Ward</label>
					<span id="VillErrorMSgShow" style="color: red;"></span>
				<select class="form-control" id="villWardId">
				<option>Select Village/Ward</option>
				</select>
			</div>
			<div class="col-xs-12 col-md-3 col-sm-6" >
				<button class="btn btn-success btn-sm btn-block" style="margin-top:25px;" id="viewMeetings">View</button>
			</div>-->
		</div>
	  </div>
      <div class="modal-footer">
	   <button type="button" attr_minuteid="0" class="btn btn-success saveMinute" id="saveBtnMeetMin">Save</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">CANCEL</button>
       </div>
    </div>
  </div>
</div>
<div id="showmsshmin"></div>
<!----------------------------->
<!--------Minutes popup-------->

<!----------------->

 <div style="display:none;">
	<div id="DistrictShowId0">
		<label>DISTRICT</label>
		<select id="districtId0" class="form-control locationCls"><option value="0"> Select District</option></select>
	</div>
	<div id="ConstShowId0">
		<label>CONSTITUENCY</label>
		<select id="constituencyId0" class="form-control locationCls"><option value="0"> Select Constituency</option></select>
	</div>
	<div id="ManTwnDivShowId0">
		<label>MANDAL/ TOWN/ DIVISION</label>
		<select id="manTowDivId0" class="form-control locationCls"><option value="0"> Select Mandal/Town/Division</option></select>
	</div>
	<div id="VillWardShowIdSpan0">
		<label>VILLAGE/ WARD</label>
		<select id="villWardId0" class="form-control locationCls"><option value="0"> Select Village/Ward</option></select>
	</div>
	
 </div>

 <!--<div id="dialogMOM" style="display:none;">
	<p>Press Alt+t to toggle between Telugu & English</p>
    <textarea rows="5" id="meetRaised" class="form-control"></textarea>
	<div class="col-md-12" id="momError" style="color:red;"></div>
 </div>-->
 
 <div id="dialogATR" style="display:none;">
	<p>Press Alt+t to toggle between Telugu & English</p>
    <div class="row">
		<div class="col-md-12">
			<label>REQUEST</label><br/>
			<textarea rows="5" id="request" class="form-control"></textarea>
		</div>
		<div class="col-md-12 m_top20">
			<label>ACTION TAKEN</label><br/>
			<textarea rows="5" id="actionTaken" class="form-control"></textarea>
		</div>
					 
		<div class="col-md-12 m_top20">
			<label>RAISED BY</label><br/>
			<input type="text" id="raisedBy"  class="form-control"/>
		</div>
		<div class="col-md-12" id="locationInPop"></div>
		<div class="col-md-12" id="atrError" style="color:red;"></div>
	</div>
 </div>
 
<footer>
        <p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/TrainingProgram/component.js" type="text/javascript"></script>
<script src="js/TrainingProgram/fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!-- Add fancyBox main JS and CSS files -->
<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="pdfexpand/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<script type="text/javascript" src="js/blockui.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">

// Load the Google Transliterate API
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

      // Create an instance on TransliterationControl with the required
      // options.
      var control =
          new google.elements.transliteration.TransliterationControl(options);

      // Enable transliteration in the textbox with id
      // 'descrptionId'.
      
		control.makeTransliteratable(['request']);
		control.makeTransliteratable(['actionTaken']);
		control.makeTransliteratable(['raisedBy']);
		control.makeTransliteratable(['meetRaised']);
    }
    google.setOnLoadCallback(onLoad);
	
	var minutesFiles = 0;
	$("#addFiles").click(function(){
		minutesFiles = minutesFiles +1;
		var c = $("#fileDiv").clone(true);
			c.removeAttr("style");
			c.attr("id","fileDivId"+minutesFiles);
            c.find(".fileCls").attr("id","fileId"+minutesFiles);
            c.find(".removeBtnCls").attr("id","remove"+minutesFiles);
			c.find(".removeBtnCls").attr("attr_count",minutesFiles);
	    $("#ExtraFiles").append(c);
	});
	//$('.tooltipBtnDiv').tooltip();
	var atrFiles = 0;
	$("#addATRFiles").click(function(){
		atrFiles = atrFiles +1;
		var c = $("#fileDiv").clone(true);
			c.removeAttr("style");
			c.attr("id","atrFileDivId"+atrFiles);
            c.find(".fileCls").attr("id","atrFileId"+atrFiles);
            c.find(".removeBtnCls").attr("id","removeATR"+atrFiles);
			c.find(".removeBtnCls").attr("attr_count",atrFiles);
	    $("#ExtraFilesATR").append(c);
	});
	
	var meetDocFiles = 0;
	$("#addMeetingFiles").click(function(){
		meetDocFiles = meetDocFiles +1;
		var c = $("#fileDiv").clone(true);
			c.removeAttr("style");
			c.attr("id","meetFileDivId"+meetDocFiles);
            c.find(".fileCls").attr("id","meetFileDivId"+meetDocFiles);
            c.find(".removeBtnCls").attr("id","removeMeet"+meetDocFiles);
			c.find(".removeBtnCls").attr("attr_count",meetDocFiles);
	    $("#ExtraMeetFiles").append(c);
	});

    var partyMeetingId='${partyMeetingId}';
    
    $('.btn-custom-g').click(function(){
        $('.data-edit').toggle();
        //$('.data').hide();
    });
   
    $('#add-fields').click(function(){
        $('div.bin-div').show();
    });
   
    $(document).ready(function() {
     getPartyMeetingMinutesAtrDetails(partyMeetingId);
    // getTheMeetingLevelDetails()
   });

    $("#mainheading").html("PARTY MEETINGS - MOM & ATR ");
    var mainDivCount=1;
	
   var maximumDivCount=1;
 	
	$(document).on('click', '.addMeetMint', function(){

			$("#mintModal").modal("show");
			//openModalMOM();
			$("#meetRaised").val("");
			$("#generalBtnId").prop('checked', true);        
			$("#meetingLocationLevel").val("");
			$("#statesDivId").val("");
			$("#districtId").val("");
			$("#constituencyId").val("");
			$("#manTowDivId").val("");
			$("#villWardId").val("");
			$("#meetingLvlId").hide();
			$("#stateShowId").hide();
			$("#DistrictShowId").hide();
			$("#ConstShowId").hide();
			$("#ManTwnDivShowId").hide();
			$("#VillWardShowId").hide();
			//getUserAccessLocationDetails();
			
	 }); 	
	 
 $(document).on('click', '.conformDel', function(){
		  $("#deletedmsg").modal("show");
		  
		  var attrId4 = $(this).attr("attr_div_count");
		 $("#deletedMinMsg").attr("attr_div_count",attrId4);
		 
		 var attrId2 = $(this).attr("attr_txt");
		 $("#deletedMinMsg").attr("attr_txt",attrId2);
		 
		 var attrId3 = $(this).attr("attr_minuteId");
		 $("#deletedMinMsg").attr("attr_minuteId",attrId3);
		  
	 });//deletedmsgAtr

	 
   $(document).on('click', '#deletedMinMsg', function(){
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Deleted Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var divId = $(this).attr("attr_txt");
		var divCount = $(this).attr("attr_div_count");
        $("#"+divId).remove();
        //$("#save"+divCount).remove(); 
		//$(this).remove();
		$("#list"+divCount).remove();		
		
		var minuteId = $(this).attr("attr_minuteId");
		
		var jsObj =    {minuteId : minuteId}
       
        $.ajax(
        {
            type: "POST",
            url:"deleteMeetingMinuteAction.action",
            data:{task :JSON.stringify(jsObj)}
        }
        ).done(function(result){
			$.unblockUI();
			if(result=="success"){
			var reslt;
			reslt = "Minute Deleted Successfully"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			updateMinCount("delete");
		   }
		   location.reload();
		});
		
    }); 
   
   function updateMinCount(type){
		if(type=="delete"){
			$("#minitePointsCount").text(parseInt($("#minitePointsCount").text())-1);
		}else{
			$("#minitePointsCount").text(type);
		}
		
	}
	
    function getTheMeetingLevelDetails(){
        var jsObj =    {}
       
        $.ajax(
        {
            type: "POST",
            url:"getTheMeetingLevelDetailsAction.action",
            data:{task :JSON.stringify(jsObj)}
        }
        ).done(function(result){
            $("#meetingLocationLevel").append('<option value="0">Meeting Level</option>');
            if(result!=null && result.length>0){
                for(var i in result){
                $("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
                }
            }
           
        });
    }
   
  /*   function getDistrictsForStates(state,atrId,locationLevelValue){
       
    $("#searchDataImgForDist"+atrId).show();
   
   
   var jsObj=
	   {				
					stateId:state,
					elmtId:"districtList_d",
					type:"default",
					task:"getDistrictsForState"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getNewDistrictsOfStateSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
       $("#searchDataImgForDist"+atrId).hide();
     for(var i in result){
       if(result[i].id == 0){
         //$("#districtId").append('<option value='+result[i].id+'>ALL</option>');
       }else{
		   if(locationLevelValue==result[i].id){
			   $("#districtId"+atrId).append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		   else{
				$("#districtId"+atrId).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
          
       }
     }
	 
	  $("#locationInPop").html("");//locationCls
		 
		 var locationDiv = $("#DistrictShowId0");
		 locationDiv.find(".locationCls").attr("id","locationDivId");
		 
		 $("#locationInPop").html($("#DistrictShowId0").html());
		 $("#locationInPop").attr("locationScope",1);
		 
   });
  } */
  function getDistrictsForStates(state){
		$("#searchDataImgForDist").show();
		
		$("#districtId  option").remove();
		//$("#districtId").append('<option value="">Select District</option>');
		$("#constituencyId  option").remove();
		<!--$("#constituencyId").append('<option value="">Select Constituency</option>');-->
		$("#manTowDivId  option").remove();
		$("#manTowDivId").append('<option value="0">Select Mandal/Town/Divison</option>');
		$("#villWardId  option").remove();
		$("#villWardId").append('<option value="0">Select Village/Ward</option>');
	   var jsObj=
	   {				
					stateId:state,
					elmtId:"districtList_d",
					type:"default",
					task:"getDistrictsForState"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getNewDistrictsOfStateSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForDist").hide();
		    $("#districtId").append('<option value="0">Select District</option>');
			
			for(var i in result){
				if(distArr.length>0){
					if($.inArray(result[i].id, distArr) > -1){
					$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   }
				}else{
					 $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}
			//meetingLevelWiseHideShow();
			//getConstituenciesForDistricts('');
			/* setTimeout(
			function(){		
				getMandalVillageDetails(4);
				setTimeout(
			function(){						
				getMandalVillageDetails(5);
			}, 1000);
			
			}, 2000); */
			
		 $("#locationInPop").html("");//locationCls
		 
		 var locationDiv = $("#DistrictShowId0");
		 locationDiv.find(".locationCls").attr("id","locationDivId");
		 
		 $("#locationInPop").html($("#DistrictShowId0").html());
		 $("#locationInPop").attr("locationScope",1);
	   });
	}
 /*function getConstituenciesForDistricts(district,atrId,locationLevelValue){
        $("#searchDataImgForman"+atrId).show();
        var distArrTemp = [district];
       var jsObj={				
			districtId:distArrTemp,
			stateId:0,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
           $("#searchDataImgForman"+atrId).hide();
            for(var i in result){
               if(result[i].id == 0){
                 // $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
               }else{
				   if(locationLevelValue==result[i].id){
					   $("#constituencyId"+atrId).append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				   }else{
						$("#constituencyId"+atrId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				   }
                  
               }
            }
			
			$("#locationInPop").html("");
			
			var locationDiv = $("#ConstShowId0");
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($("#ConstShowId0").html());
			$("#locationInPop").attr("locationScope",2);
        });
    }*/
function getConstituenciesForDistricts(district){
	   $("#constituencyId  option").remove();
	   $("#manTowDivId  option").remove();
		$("#manTowDivId").append('<option value="0">ALL</option>');
		$("#villWardId  option").remove();
		$("#villWardId").append('<option value="0">ALL</option>');
	   
		//$("#constituencyId").append('<option value="">Select Constituency</option>');
		var stateId = $("#statesDivId").val();
		
		var distArrTemp = [];
		if(district==0){
			distArrTemp = distArr; //8888
		}else{
			distArrTemp.push(district);
		}
		 $("#searchDataImgForcons").show();
		var jsObj={				
			districtId:distArrTemp,
			stateId:stateId,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForcons").hide();
		   $("#constituencyId").append('<option value="0">Select Constituencies</option>');
			for(var i in result){
			   if(assmblyArr.length>0){
				   if($.inArray(result[i].locationId, assmblyArr) > -1){
					  $("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
			   }
				else{
					$("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
				
			}
			$("#locationInPop").html("");
			
			var locationDiv = $("#ConstShowId0");
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($("#ConstShowId0").html());
			$("#locationInPop").attr("locationScope",2);
			
		});
	}

   
    function getMandalVillageDetails(locationId, locationLevel,atrId,locationLavelValue){
		$("#manTowDivId  option").remove();
		$("#villWardId  option").remove();
          $("#searchDataImgForcons"+atrId).show();
        var constituencyId = [];
        var mandalId = [];
        var locationTemp = "#ManTwnDivShowId0";
		
		var districts = [];
	   
        if(locationLevel==4){
			$("#locationInPop").attr("locationScope",3);
             $("#searchDataImgForcons"+atrId).show();
            constituencyId.push(locationId);
        }
        if(locationLevel==5){
			$("#locationInPop").attr("locationScope",4);
           locationTemp = "#VillWardShowIdSpan0";
            mandalId.push(locationId);
        }
       
       var jsObj={               
            stateId : 0,
            districtId : districts,
            constituencyId : constituencyId,//228
            mandalId : mandalId,
            locationLevel : locationLevel,
            task:""
        }
        $.ajax({
              type:'POST',
              url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
              dataType: 'json',
              data: {task:JSON.stringify(jsObj)}
       }).done(function(result){
            $("#searchDataImgForcons"+atrId).hide();
              var divId = "#manTowDivId"+atrId;
              if(locationLevel ==5){
                  divId = "#villWardId"+atrId;
              }
              
            for(var i in result){
				if(locationLavelValue==result[i].locationId){
					$(divId).append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				}else{
					$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
                
            }
			
			$("#locationInPop").html("");
			
			var locationDiv = $(locationTemp);
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($(locationTemp).html());
		});
    }
   
    //getmeetinglocationlevel(1, 36);
    function getmeetinglocationlevel(locationLevel, locationId,atrId,locationLevelValue){
       //alert(locationLevel+"---"+locationId+"---"+atrId+"---"+locationLevelValue);
	   
        if(locationLevel == 1){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).show();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).hide();
			
			getDistrictsForStates(locationId,atrId,locationLevelValue);
        }
        else if(locationLevel == 2){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).show();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).hide();
            getConstituenciesForDistricts(locationId,atrId,locationLevelValue);
        }
        else if(locationLevel == 3){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).show();
            $("#VillWardShowIdSpan"+atrId).hide();
			
			
            getMandalVillageDetails(locationId, 4,atrId,locationLevelValue);
        }
        else if(locationLevel == 4){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).show();
			
			//$("#VillWardShowIdSpan"+atrId).val($("#VillWardId"+atrId).text());
            getMandalVillageDetails(locationId, 5,atrId,locationLevelValue);
        }
    }
	
	function getPartyMeetingMinutesAtrDetails(partyMeetingID){
		
		var jsObj={				
			partyMeetingID : partyMeetingID
		}
		$.ajax({
			  type:'POST',
			  url: 'getPartyMeetingMinutesAtrDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   
		   if(result!=null){
			   $("#meetingType").html(result.partyMeetingType+" - "+result.name);
			   $("#location").html(result.locationName);
			   
			   if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
				   $("#miniteDocsCount").html(result.minutesDocuments.length);
				   var str='';
				    str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				    for(var i in result.minutesDocuments){
						str+='<li class="col-md-12 list-group-item" id="minuteDocFileId'+result.minutesDocuments[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="deleteDoc pull-right" attr_type="minute" id="'+result.minutesDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
						
				   }
				    str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#mintueDocumentDivId").html(str);
			   }
			    if(result.atrDocuments!=null && result.atrDocuments.length>0){
					$("#atrDocsCount").html(result.atrDocuments.length);
				   var str='';
				    str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.atrDocuments){
					    str+='<li class="col-md-12 list-group-item" id="atrDocFileId'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" attr_type="atr" id="'+result.atrDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#atrDocumentDivId").html(str);
				  // $('.fancybox').fancybox();
			   }
			   
			   if(result.meetingDocs!=null && result.meetingDocs.length>0){
					//$("#atrDocsCount").html(result.meetingDocs.length);
				   var str='';
				    str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold">UPLOAD MEETING DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.meetingDocs){
					    str+='<li class="col-md-12 list-group-item" id="atrDocFileId'+result.meetingDocs[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.meetingDocs[i].url+'">'+result.meetingDocs[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" attr_type="atr" id="'+result.meetingDocs[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#meetingDocumentDivId").html(str);
				  // $('.fancybox').fancybox();
			   }
			   
			   if(result.minutesDetails!=null && result.minutesDetails.length>0){
				   $("#minitePointsCount").html(result.minutesDetails.length);
				   minutesFiles = result.minutesDetails.length;
				   var str='';
				    mainDivCount=i;
					str+='<ul class="list-group" id="list'+mainDivCount+'">';
				   for(var i in result.minutesDetails){
					  	 mainDivCount=i;
						str+='<li class="list-group-item " id="list'+mainDivCount+'">';
						str+='<p id="minutes'+mainDivCount+'" class="updatedMeetMintValue" style="margin-bottom: 0px; margin-top: 11px;" onclick=enableSaveOption("'+mainDivCount+'");>'+result.minutesDetails[i].minutePoint+'</p>';
						str+='<div class="btn-group btn-group-sm pull-right" role="group" style="display: inline-block;position: absolute;right: 0;top: 0;">';
				       str+=' <button class="btn btn-default conformDel" id="delMinuteId'+i+'" title="Delete" attr_txt="minutes'+mainDivCount+'" attr_div_count="'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default updatedMeetMin" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'"  title="Edit"   ><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
						str+=' </li>';
						
							
				   }
				   str+='</ul>';
				   $("#addMoreDiv").html(str);
				 //  $('.trash').tooltip()
			   }else{
				   $("#addMoreDiv").html("<h5>No Meeting Minutes</h5>");
				   
			   }
			   
			   if(result.atrDetails!=null && result.atrDetails.length>0){
				   $("#atrPointsCount").html(result.atrDetails.length);
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="btn-group btn-group-sm pull-right" style="margin-top: -16px; margin-right: -1px; z-index: 999;margin-bottom: 2px;">';
				       str+=' <button class="btn btn-default  deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'"  title="Delete"  attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default editBtn "   title="Edit"  attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'" attr_location_value='+result.atrDetails[i].locationValue+'><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
					    str+='<ul class="" style="padding-left: 0px;margin-top: -48px;">';
					   str+='<li class="col-md-12 list-group-item" >';
					   str+='<span class="text-bold">REQUEST : </span>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].request+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">ACTION TAKEN : </span>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].actionTaken+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">RAISED BY : </span>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span> - <i>'+result.atrDetails[i].locationName+'</i>';
					   str+='</li>';
					   str+='</div>';
					   
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
				   }
				   
				   $("#atrDivId").html(str);
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
				   
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
		   }
	   });
	}
	$(document).on('click', '.updatedMeetMin', function(){
		$("#mintModal").modal("show");
		//openModalMOM();
		
		var meetmin=$(this).parent().parent().find(".updatedMeetMintValue").text();
		$("#meetRaised").val(meetmin);
		
		var attrId1 = $(this).attr("attr_minuteId");
		 $("#saveBtnMeetMin").attr("attr_minuteId",attrId1);
		$("#meetingLvlId").hide();
		$("#stateShowId").hide();
		getPartyMeetingMinuteRetrieveDetails(attrId1);
	});
	
	/* function enableSaveOption(txtBoxCnt){
		
		$("#save"+txtBoxCnt).show();
	} */
	
	$(document).on('click', '.saveMinute', function(){
		$("#momError").text("");
		var reslt;
		var minuteText = $("#meetRaised").val();
		//var isActionable = $("#actionableBtnId").val();
		
		var isActionable=$('input[name=checkRdoBtnname]:checked').val();
		var levelId =0;
		var levelValue = 0;
		var stateId=0;
		var districtId=0;
		var constituencyId=0;
		var mandalId=0;
		var panchayatId=0;
		
		if(isActionable =="Y"){	
			levelId = $("#meetingLocationLevel").val();
			
			
			stateId = $("#statesDivId").val();
			districtId = $("#districtId").val();
			constituencyId = $("#constituencyId").val();			
			mandalId = $("#manTowDivId").val();
			panchayatId = $("#villWardId").val();				
			
			if(stateId == null){
				stateId =0;
			}
			if(districtId == null){
				districtId =0;
			}
			if(constituencyId == null){
				constituencyId =0;
			}
			if(mandalId == null){
				mandalId =0;
			}
			if(panchayatId == null){
				panchayatId =0;
			}
			
			if(panchayatId != null && panchayatId > 0){
			levelValue = panchayatId;
			}else if(mandalId != null && mandalId > 0){
				levelValue = mandalId;
			}else if(constituencyId != null && constituencyId > 0){
				levelValue = constituencyId;
			}else if(districtId != null && districtId > 0){
				levelValue = districtId;
			}else if(stateId != null && stateId > 0){
				levelValue = stateId;
			}
		}	
		if(minuteText.trim().length==0){
			$("#momError").text(" Please Enter Minute Text");
			return;
		}
		if($("#meetingLocationLevel").val()== 2 ){
			if(districtId == 0){
				$("#momError").text(" Please Select District ");
				return;
			}
		}else if($("#meetingLocationLevel").val()== 3){
			if(constituencyId == 0){
				$("#momError").text(" Please Select constituency");
				return;
			}
		}else if($("#meetingLocationLevel").val()== 4 || $("#meetingLocationLevel").val()== 5 || $("#meetingLocationLevel").val()== 6){
			if(mandalId == 0){
				$("#momError").text(" Please Select Mandal");
				return;
			}
		}else if($("#meetingLocationLevel").val()== 7 || $("#meetingLocationLevel").val()== 8){
			if( panchayatId == 0){
				$("#momError").text(" Please Select Panchayat");
				return;
			}
				
		}else if($("#meetingLocationLevel").val()== 1){
			if(stateId == 0){
				$("#momError").text(" Please Select State");
				return;
			}
		}
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Saving Minutes Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var minuteId = $(this).attr("attr_minuteid");
		var jsObj={		
			minuteId : minuteId,
			minuteText : minuteText,
			partyMeetingId : partyMeetingId,//global var
			statusId : 1,
			stateId:stateId,
			districtId:districtId,
			constituencyId:constituencyId,
			tehsilId:mandalId,
			panchayatId:panchayatId,
			isActionable:isActionable,
			levelId:levelId,
			levelValue:levelValue
		}
		$.ajax({
			  type:'POST',
			  url: 'updateMeetingMinutePointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#mintModal').modal('hide');
			//$( "#dialogMOM" ).dialog('close');
			
		   if(result=="success"){
				rebuildMinutes(partyMeetingId,minuteId)
		   }else{
				reslt = "Please Try Again"
				$("#mintupdatealertmag").html(reslt);
				$('#alertmintsave').modal('show');
				$.unblockUI();
		   }
		   
			$("#saveBtnMeetMin").attr("attr_minuteId",0);
		});
	});
	
	function rebuildMinutes(partyMeetingId,minuteId){
		var jsObj={		
			partyMeetingId : partyMeetingId//global var
		}
		$.ajax({
			  type:'POST',
			  url: 'getTheMinutePointsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.minutesDetails!=null && result.minutesDetails.length>0){
			   minutesFiles = result.minutesDetails.length;
			   updateMinCount(minutesFiles);
			   var str='';
			   for(var i in result.minutesDetails){
				   mainDivCount=i;
					str+='<li class="list-group-item " id="list'+mainDivCount+'">';
					 //str+='<div class=" m_top10" id="list'+mainDivCount+'">';
					 str+='<span id="minutes'+mainDivCount+'" class="updatedMeetMintValue" onclick=enableSaveOption("'+mainDivCount+'");>'+result.minutesDetails[i].minutePoint+'</span>';
					 str+='<div class="btn-group btn-group-sm pull-right" style="display: inline-block;position: absolute;right: 0;top: 0;">';
					 str+=' <button class="btn btn-default ToolTipDiv conformDel" data-toggle="tooltip" data-placement="top" title="Delete" attr_txt="minutes'+mainDivCount+'" attr_div_count="'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'"><i class="glyphicon glyphicon-trash"></i></button>';
					 str+=' <button class="btn btn-default updatedMeetMin ToolTipDiv " attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'" data-toggle="tooltip" data-placement="top" title="Edit" ><i class="glyphicon glyphicon-edit"></i></button>';
					 str+=' </div>';
					 str+=' </li>';
				}
			   $("#addMoreDiv").html(str);
			   //$('.trash').tooltip()
			}
			$.unblockUI();
			//alert("Updated Successfully");
			if(minuteId == 0){
				var stts = "Saved Successfully"
				$("#mintupdatealertmag").html(stts);
				$('#alertmintsave').modal('show');
			}else if(minuteId > 0){
				var stts = "Updated Successfully"
				$("#mintupdatealertmag").html(stts);
				$('#alertmintsave').modal('show');
			}
		});
	}
	
	$("#uploadMinutesDocsId").click(function(){
		$("#partyMeetingId").val(partyMeetingId);
		var files = [];
		$("#uploadMinutesDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			var reslt;
			reslt = "Please Select Documents"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Please Select Documents");
			return;
		}
		
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showingStatus(uploadResult,"MINUTE");
				}
			};

		YAHOO.util.Connect.setForm('uploadMinutesDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadMinutesDocAction.action',uploadHandler);
	});
	
	$("#uploadMeetingDocsId").click(function(){
		$("#partyMeetingMeetId").val(partyMeetingId);
		var files = [];
		$("#uploadMeetingDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			var reslt;
			reslt = "Please Select Documents"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Please Select Documents");
			return;
		}
		
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showingStatus(uploadResult,"MEETING");
				}
			};

		YAHOO.util.Connect.setForm('uploadMeetingDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadMeetingDocAction.action',uploadHandler);
	});
	
	$("#uploadATRDocsId").click(function(){
		$("#partyMeetingATRId").val(partyMeetingId);
		var files = [];
		$("#uploadATRDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			var reslt;
			reslt = "Please Select Documents"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Please Select Documents");
			return;
		}
		
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showingStatus(uploadResult,"ATR");
				}
			};

		YAHOO.util.Connect.setForm('uploadATRDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadMinutesDocAction.action',uploadHandler);
	});
	
	
	function showingStatus(myResult,docSourceType){
		var reslt;
		var result = myResult;
		if (result.indexOf("success") >= 0){
			$("#mintupdatealertmag").html("Uploaded Successfully");
			$('#alertmintsave').modal('show')
			//alert("File Uploaded Successfully");
			//rebuildTheDocumentsDiv(docSourceType,reslt);
		}
		else{
			reslt = "Failed to Upload.. Please Try Again"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Failed to Upload.. Please Try Again");
		}
		partyMeetingDocs(docSourceType);
		
	}
	
	function rebuildTheDocumentsDiv(fromType,result){
		var jsObj={		
			partyMeetingId : '${partyMeetingId}'
		}
		$.ajax({
			  type:'POST',
			  url: 'getDocumentDetailsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(fromType=="MINUTE"){
				if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
					$("#miniteDocsCount").text(result.minutesDocuments.length);
				   var str='';
				   str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold"> UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				    for(var i in result.minutesDocuments){
					    str+='<li class="list-group-item col-md-12" id="docDiv'+result.minutesDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.minutesDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#mintueDocumentDivId").html(str);
			   }
			   $("#ExtraFiles").html("");
			}
			else if(fromType=="ATR"){
				if(result.atrDocuments!=null && result.atrDocuments.length>0){
					$("#atrDocsCount").text(result.atrDocuments.length);
				   var str='';
				   str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.atrDocuments){
					    str+='<li class="col-md-12  list-group-item" id="docDiv'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.atrDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#atrDocumentDivId").html(str);
			   }
			   $("#ExtraFilesATR").html("");
			}
			else if(fromType=="MEETING"){
				if(result.meetingDocs!=null && result.meetingDocs.length>0){
					//$("#atrDocsCount").text(result.atrDocuments.length);
				   var str='';
				   str+='<div class="panel panel-default">';
					//str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.meetingDocs){
					    str+='<li class="col-md-12  list-group-item" id="docDiv'+result.meetingDocs[i].id+'">';
					    str+='<a target="_tab" href="'+result.meetingDocs[i].url+'">'+result.meetingDocs[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.meetingDocs[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#meetingDocumentDivId").html(str);
			   $("#ExtraMeetFiles").html("");
			   }
			}
			reslt = "File Uploaded Successfully"
			$("#mintupdatealertmag").html(reslt);
			
		});
	}
	
	
	$(document).on("click",".removeBtnCls",function(){
		$(this).parent().parent().remove();
	});
	
	
	function showBtnsDiv(atrId){
		$("#btnsDiv"+atrId).show();
	}
	
	$(document).on('click', '.deleteDoc', function(){
		var docId = $(this).attr("id");
		var type = $(this).attr("attr_type");
		var divId = "#"+type+"DocFileId"+docId;
		
		var jsObj={		
			docId : docId
		}
		$.ajax({
			  type:'POST',
			  url: 'deletePartyMeetingDocumentAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				updateMinDocCount(type);
				$("#docDiv"+docId).remove();
				$(""+divId+"").remove();
			}else{
			var reslt;
			reslt = "Please Try Again"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
				//alert("Please Try Again");
			}
		});
	});
	
	function updateMinDocCount(type){
		if(type=="atr"){
			$("#atrDocsCount").text(parseInt($("#atrDocsCount").text())-1);
		}else{
			$("#miniteDocsCount").text(parseInt($("#miniteDocsCount").text())-1);
		}
	}
	
	var editAtrId='';
	 $(document).on('click', '.editBtn', function(){
		 //$("#myModashow").modal("show");
		 
		 var req=$(this).parent().parent().find(".updaterequest").text();
		 var actn=$(this).parent().parent().find(".updateaction").text();
		 var raised=$(this).parent().parent().find(".updateraisedBy").text();
		 var attrId = $(this).attr("attr_id");
		 //var lctnVal = $(this).parent().parent().parent().find(".selectedLctn").attr("attr_lctnid");
		 var lctnVal = $(this).attr("attr_location_value");
		 
		 //$("#saveBtn").attr("attr_id",attrId);
		 $("#request").val(req);
		 $("#actionTaken").val(actn);
		 $("#raisedBy").val(raised);
		 
		 //$("#locationDivId").val(lctnVal);
		 //$('#locationDivId').val(""+lctnVal+"").trigger('change');
		 
		 $('#locationDivId option[value="'+lctnVal+'"]').prop('selected', true);
		 
		 openModalATR(attrId);
	});
	
	$(document).on('click', '.addingRequests', function(){
		 
		 $("#request").val("");
		 $("#actionTaken").val("");
		 $("#raisedBy").val("");
		 $('#locationInPop option:selected').prop('selected', false);
		 
		 openModalATR(0);
		
		// $("#myModashow").modal("show");
	});
	
	
	$(document).on('click', '#saveBtn', function(){
		//$.blockUI({ message: "<h5> Saving ATR Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		$("#atrError").text("");
		var reslt;
		var request = $("#request").val();
		var ActionTaken = $("#actionTaken").val();
		var raisedBy = $("#raisedBy").val();
		var locationId = $("#locationDivId").val();
		var atrId = $(this).attr("attr_id");
		var partyMeetingId = '${partyMeetingId}';
		var locationscope = $("#locationInPop").attr("locationscope");
		
		var errorTxt = "Please Enter";
		var isError = false;
		if(request.trim().length==0){
			errorTxt+=" Request"; 
			isError = true;
		}
		if(ActionTaken.trim().length==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Action Taken";
			isError = true;
		}
		if(raisedBy.trim().length==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Raised By";
			isError = true;
		}
		if(locationId==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Location";
			isError = true;
		}
		
		if(isError){
			$("#atrError").text(errorTxt);
			return;
		}
		
		$.blockUI({ message: "<div style='padding:10px; background-color:#CCC;'><h5> Saving ATR Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		
		var jsObj={		
			atrId : atrId,
			request : request,
			ActionTaken : ActionTaken,
			raisedBy : raisedBy,
			locationId : locationId,
			partyMeetingId : partyMeetingId,
			locationscope : locationscope
		}
		$.ajax({
			  type:'POST',
			  url: 'updateMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//$("#myModashow").modal("hide");
			$( "#dialogATR" ).dialog('close');
			if(result=="success"){
				//alert("Updated Successfully");
				getAtrPointsForAMeeting(partyMeetingId,atrId);
			}else{
				reslt = "Please Try Again"
				$("#mintupdatealertmag").html(reslt);
				$('#alertmintsave').modal('show');
				//alert("Please Try Again");
				$.unblockUI();
			}
		});	
		
	});
	
	function getAtrPointsForAMeeting(partyMeetingId,atrId){
		var jsObj={		
			partyMeetingId : partyMeetingId
		}
		$.ajax({
			  type:'POST',
			  url: 'getAtrPointsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result.atrDetails!=null && result.atrDetails.length>0){
				updateAtrCount(result.atrDetails.length);
				$("#atrDivId").html("");
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'" class="m_top10">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="btn-group btn-group-sm pull-right" style="margin-top: -16px; margin-right: -1px; z-index: 999;margin-bottom: 2px;">';
				       str+=' <button class="btn btn-default  deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'"  title="Delete"  attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default editBtn "   title="Edit"  attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'" attr_location_value='+result.atrDetails[i].locationValue+'><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
					    str+='<ul class="" style="padding-left: 0px;margin-top: -48px;">';
					   str+='<li class="col-md-12 list-group-item" >';
					   str+='<span class="text-bold">REQUEST : </span>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].request+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">ACTION TAKEN : </span>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].actionTaken+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">RAISED BY : </span>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span> - <i>'+result.atrDetails[i].locationName+'</i>';
					   str+='</li>';
					   str+='</div>';
					   
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					  
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
					  
				   }
				   
				   $("#atrDivId").html(str);
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				  // getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
			   $.unblockUI();
			   //alert("Updated Successfully");
				if(atrId == 0){
					var  stts = "Saved Successfully"
					$("#mintupdatealertmag").html(stts);
					$('#alertmintsave').modal('show');
				}else if(atrId >0){
					var stts = "Updated Successfully"
					$("#mintupdatealertmag").html(stts);
					$('#alertmintsave').modal('show');
				}
		});
	}
	
	function partyMeetingDocs(docSourceType){
		
		var divId = "#mintueDocumentDivId";
		if(docSourceType=="ATR"){
			divId = "#atrDocumentDivId";
			$("#uploadATRDocs input[type=file]").each(function() {
				$(this).val("");
			});
		}else if(docSourceType=="MINUTE"){
			$("#uploadMinutesDocs input[type=file]").each(function() {
				$(this).val("");
			});
		}else if(docSourceType=="MEETING"){
			divId = "#meetingDocumentDivId"
			$("#uploadMeetingDocs input[type=file]").each(function() {
				$(this).val("");
			});
		}
		
		var partyMeetingId = '${partyMeetingId}';
		var jsObj={		
			partyMeetingId : partyMeetingId,
			docSourceType:docSourceType
		}
		$.ajax({
			type:'POST',
			url: 'getDocsOfMeetingAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 /* if(result!=null && result.length>0){
				   var str='';
				    for(var i in result){
					    str+='<div class="col-md-12 row" id="minuteDocFileId'+result[i].id+'" style="padding:6px;">';
					    str+='<a class="col-md-10" href="'+result[i].url+'">'+result[i].name+'</a>';
						str+='<div class="deleteDoc col-md-2" attr_type="minute" id="'+result[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div>';
				   }
				   $(""+divId+"").html(str);
			   }	 */
			   var str='';
				   str+='<div class="panel panel-default">';
					/* if(docSourceType=="MEETING"){
						str+='<div class="panel-heading text-bold"> UPLOAD MEETING DOCUMENTS</div>';
					}else{
						str+='<div class="panel-heading text-bold"> UPLOAD DOCUMENTS</div>';   
					} */
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
			   if(result!=null && result.length>0){
				   $("#miniteDocsCount").text(result.length);
				    for(var i in result){
					    str+='<li class="list-group-item col-md-12" id="minuteDocFileId'+result[i].id+'" style="padding:6px;">';
					    str+='<a class="col-md-10" href="'+result[i].url+'">'+result[i].name+'</a>';
						str+='<div class="deleteDoc pull-right" attr_type="minute" id="'+result[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
				   }
				   
			   }
			   str+='</ul>';
				   $(""+divId+"").html(str);
		});	
	}
	 $(document).on('click', '.deleteAtr', function(){
		  $("#deletedmsgAtr").modal("show");
		  
		  var attrId5 = $(this).attr("attr_atrid");
		 $("#AtrdeletedMsg").attr("attr_atrid",attrId5);
		 
		
		 
	 });
	$(document).on('click', '#AtrdeletedMsg', function(){
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Deleted Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var atrId = $(this).attr("attr_atrid");
		
		var jsObj={		
			atrId : atrId
		}
		$.ajax({
			  type:'POST',
			  url: 'deleteMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$.unblockUI();
			if(result=="success"){
			var reslt;
			reslt = "ATR Deleted Successfully"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
				//alert("ATR Deleted");
				$("#atrInnerDiv"+atrId).remove();
				updateAtrCount("delete");
			}
		});
		
	});
	
	function updateAtrCount(type){
		if(type=="delete"){
			$("#atrPointsCount").text(parseInt($("#atrPointsCount").text())-1);
		}else{
			$("#atrPointsCount").text(type);
		}
	}
	
	alertmssgshowing();
	function alertmssgshowing(){
		var str='';
		str+='<div class="modal fade alerttop" id="alertmintsave" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="z-index:99999">';
		str+='<div class="modal-dialog modal-sm">';
		str+=' <div class="modal-content modal-sm alertstyle">';
		str+='<div class="modal-body ">';
		str+=' <div><h5 class="text-center colorchange" id="mintupdatealertmag"></h5></div>';
		str+=' </div>';
		str+=' <div class="modal-footer pad-0 bt">';
		str+='<div class="row">';
		str+='<div class="mtop-5">';
		str+='<button type="button" class="btn btn-primary btn-xs mleft_190 pull-left" data-dismiss="modal" style="margin-top: -11px;">Close</button>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#showmsshmin").html(str);
	}
	
	$(document).on("change","#locationDivId",function(){
		var someVal = $(this).val();
		$('#locationDivId').find('option[value='+someVal+']').prop('selected',true);
	});
	
	function openModalMOM(){
		alert(7);
		$("#momError").html("");
		$( "#dialogMOM" ).dialog({
		  height: 420,
		  width: 700,
		  title :"MOM Points ",
		  buttons: [
			{
				text: "Cancel",
				click: function() {
					 $(this).dialog("close");
				}
			},
			{
				text: "Save",
				"class": 'saveMinute',
				"id": 'saveBtnMeetMin',
				"attr_minuteid": '0'
			}
		],
   	   close: function() {
			 $(this).dialog("close");
		  }
		});
	}
	
	
	function openModalATR(atrId){
		$("#atrError").html("");
		$( "#dialogATR" ).dialog({
		  height: 700,
		  width: 700,
		  title :"ATR Points ",
		  buttons: [
			{
				text: "Cancel",
				click: function() {
					 $(this).dialog("close");
				}
			},
			{
				text: "Save",
				"id": 'saveBtn',
				"attr_id":atrId
			}
		],
		close: function() {
			 $(this).dialog("close");
		  }
		});
	}
$(document).on(".checkCls",function(){
  $(".checkCls").attr("checked", false); //uncheck all checkboxes
  $(this).attr("checked", true);  //check the clicked one
});	

	var stateArr = [];
	var distArr = [];
	var parlArr = [];
	var assmblyArr = [];
	var firstMeetingId;
	function getUserAccessLocationDetails(){
	    var jsObj={				
			task:"getUserAccessLocationDetails"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getUserAccessLocationDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   $("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
		   var rslt = result.userAccessLevelList;
			if(rslt!=null && rslt.length>0){
				var isAddedMandal = false;
				var isAddedVillage = false;
				for(var i in rslt){
					if(rslt[i].levelId ==4 || rslt[i].levelId ==5 || rslt[i].levelId ==6){
						var locationLevel = " Mandal/ Town/ Division";
						if(!isAddedMandal){
							$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+locationLevel+'</option>');
						}
						isAddedMandal = true;
					}else if(rslt[i].levelId ==7 || rslt[i].levelId ==8){
						locationLevel = " Village/ Ward";
						if(!isAddedVillage){
							$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+locationLevel+'</option>');
						}
						isAddedVillage = true;
					}else{
						$("#meetingLocationLevel").append('<option value="'+rslt[i].levelId+'">'+rslt[i].name+'</option>');
					}
					
				}
			}
			$("#searchDataImgForMeetingsList").hide();
			
			var rsltValues = result.userAccessLevelValuesList;
			if(rsltValues!=null && rsltValues.length>0){
				for(var i in rsltValues){
					if(rsltValues[i].levelId==1){
						stateArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==2){
						distArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==3){
						parlArr = rsltValues[i].levelValues;
					}else if(rsltValues[i].levelId ==4){
						assmblyArr = rsltValues[i].levelValues;
					}
				}
			}
			
			if(distArr.length==0 && parlArr.length==0){
				$('#meetingLocationLevel').val('1').trigger('change');
				$("#statesDivId").val(stateArr[0]).trigger('change');
			} else if(stateArr.length>0 && distArr.length>0 && parlArr.length==0){
				console.log(2);
				$('#meetingLocationLevel').val('2').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#districtId').val(distArr[0]).trigger('change');
					
				},1000); 
				
			}else if(parlArr.length>0){
				$('#meetingLocationLevel').val('3').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#districtId').val(distArr[0]).trigger('change');
					
				},1000); 
				
				setTimeout(function(){	
					$('select[name="constBox"] option:eq(2)').attr('selected', 'selected');
					//$("#viewMeetings").trigger( "click" );
				},2000);
			} 
		});
	}
		//Location Showing and Hiding
$("#meetingLocationLevel").change(function(){
			
			$("#districtId").val($("#districtId option:first").val());
			$("#constituencyId").val($("#constituencyId option:first").val());
			$("#manTowDivId").val($("#manTowDivId option:first").val());
			$("#villWardId").val($("#villWardId option:first").val());

				 $("#statesDivId").html("");
				
				//$("#statesDivId").append('<option value=""> Select State </option>');
				$("#statesDivId").append('<option value=0> ALL </option>');
				
				if($.inArray(1, stateArr) > -1){
					$("#statesDivId").append('<option value=1> AndhraPradesh </option>');
				}
				if($.inArray(36, stateArr) > -1){
					$("#statesDivId").append('<option value=36> Telangana </option>');
				} 
				<!--<c:if test="${sessionScope.USER.isAdmin == 'true'}">-->
					$(".stateCls").each(function(){
						if($(this).prop('checked')==true){
					
						 if($(this).val()==1){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
							getDistrictsForStates($( "#statesDivId" ).val());
						}else if($(this).val()==36){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=36> Telangana </option>');
							getDistrictsForStates($( "#statesDivId" ).val());
						}else if($(this).val()==0){
							$("#statesDivId").html("");
							$("#statesDivId").append('<option value=0> All </option>');
							$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
							$("#statesDivId").append('<option value=36> Telangana </option>');
							//getDistrictsForStates(this.value);
							}
						}
					});
				<!--</c:if>	-->

meetingLevelWiseHideShow();	
});	
function getMandalsForDistrictId(){
	 $("#manTowDivId  option").remove();
	var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}
			}else{
				districtId.push(districtId);
			}
			districtId = districtId;
			var constituencyId=[];
	 var jsObj={
			stateId:0,			
			districtId :districtId,
			mandalId:0,
			locationLevel:4,
			constituencyId:constituencyId
		}
		$.ajax({
			type:"POST",
			url :"getSubLevelLctnsForConstituencyAndMandalAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#manTowDivId").append("<option value='0'>Select Mandal</option>");
			   for(var i in result){
				   $("#manTowDivId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
  }

 function meetingLevelWiseHideShow(){
	 if($("#meetingLocationLevel").val()== 2 ){
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").hide();
				$("#ManTwnDivShowId").hide();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 3){
				getConstituenciesForDistricts("");
				
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").hide();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 4 || $("#meetingLocationLevel").val()== 5 || $("#meetingLocationLevel").val()== 6){
				getConstituenciesForDistricts("");	
				getMandalsForDistrictId();
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").show();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 7 || $("#meetingLocationLevel").val()== 8){
				getConstituenciesForDistricts("");

				getMandalVillageDetails(4);
				getMandalsForDistrictId();
				getVillagesForDistrictId();
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").show();
				$("#VillWardShowId").show();
				
			}else if($("#meetingLocationLevel").val()== 1){
				
				$("#stateShowId").show();
				$("#DistrictShowId").hide();
				$("#ConstShowId").hide();
				$("#ManTwnDivShowId").hide();
				$("#VillWardShowId").hide();
			}
		
}
 function getVillagesForDistrictId(){
	$("#villWardId  option").remove();
	 var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}
			}else{
				districtId.push(districtId);
			}
			districtId = districtId;
			var constituencyId=[];
	 	var jsObj={
			stateId:0,			
			districtId :districtId,
			mandalId:0,
			locationLevel:5,
			constituencyId:constituencyId
		}
		$.ajax({
			type:"POST",
			url :"getSubLevelLctnsForConstituencyAndMandalAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				$("#villWardId").append("<option value='0'>Select Village</option>");
				for(var i in result){
				   $("#villWardId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
 }
 $( "#statesDivId" ).change(function() {
		$("#stateErrorMSgShow").html("");
		getDistrictsForStates(this.value);
	});
$( "#districtId" ).change(function() {
		$("#districtErrorMSgShow").html("");
		getConstituenciesForDistricts(this.value);
	});
$(document).on("click","#actionableBtnId",function(){
		getUserAccessLocationDetails();
		$("#meetingLocationLevel").html("");
		$("#meetingLvlId").show();
		$("#stateShowId").show();
		
});
$(document).on("click","#generalBtnId",function(){
		$("#meetingLocationLevel").val("");
		$("#statesDivId").val("");
		$("#districtId").val("");
		$("#constituencyId").val("");
		$("#manTowDivId").val("");
		$("#villWardId").val("");
		$("#meetingLvlId").hide();
		$("#stateShowId").hide();
		$("#DistrictShowId").hide();
		$("#ConstShowId").hide();
		$("#VillWardShowId").hide();
		$("#ManTwnDivShowId").hide();
});

function getPartyMeetingMinuteRetrieveDetails(minuteId){
	var jsObj={
		minuteId:minuteId	
		}
		$.ajax({
			type:"POST",
			url :"getPartyMeetingMinuteRetrieveDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
				if(result !=null && result.length>0){
				if(result[0].actionType == "Y"){
					
					$("#actionableBtnId").attr('checked', true);
					if ($("#actionableBtnId").is(":checked")) {
						getUserAccessLocationDetails();
						setTimeout(function(){ 
							prePopulatePartyMeetingMinuteDetails(result);
						}, 5000);
					}	
				}else if(result[0].actionType == "N"){
					$("#generalBtnId").attr('checked', true);
					if ($("#generalBtnId").is(":checked")) {
						$("#meetingLvlId").hide();
						$("#stateShowId").hide();
						$("#DistrictShowId").hide();
						$("#ConstShowId").hide();
						$("#ManTwnDivShowId").hide();
						$("#VillWardShowId").hide();
					}
				} 
				
		   }
   });	
 }
 function prePopulatePartyMeetingMinuteDetails(result){
	 $("#meetRaised").val(result[0].name);
	 if ($("#actionableBtnId").is(":checked")) {
		  $("#meetingLvlId").show();
		$("#meetingLocationLevel").val(result[0].locationLevel);
		$("#statesDivId").val(result[0].stateId);
		$("#districtId").val(result[0].districtId);
		$("#constituencyId").val(result[0].constituencyId);
		$("#manTowDivId").val(result[0].tehsilId);
		$("#villWardId").val(result[0].panchayatId);
     }
	 
 }
 function getMandalVillageDetailsForConstituency(locationLevel){
	//$("#manTowDivId  option").remove();
		//$("#villWardId option").remove();
		var stateId = $("#statesDivId").val();
		var districtId = $("#districtId").val();
		var constituencyId = $("#constituencyId").val();
		var distArrTemp = [];
		if(districtId==0){
			distArrTemp = distArr;
		}else{
			distArrTemp.push(districtId);
		}
		
		var mandalId = "0";
		if(locationLevel==4){
			$("#manTowDivId").html("");
		}
		if(locationLevel==5){
			mandalId = $("#manTowDivId").val();
			//$("#manTowDivId").html("")
			//if(mandalId >0)
				$("#villWardId").html("");
		}
		var assmblyArrTemp = [];
		if(constituencyId==0){
			//assmblyArrTemp = assmblyArr;	
			var divId = "#manTowDivId";
			//divId = "#villWardId";//teja
			//$(divId).html("");
			if(locationLevel ==5){
				  divId = "#villWardId";
			}
		}/* else if($("#manTowDivId").val()==null || $("#manTowDivId").val() == 0){
			$("#villWardId  option").remove();
			$("#villWardId").append('<option value="">ALL</option>');
			assmblyArrTemp.push(constituencyId);
		} */
		else{
			assmblyArrTemp.push(constituencyId);
		}
		$("#searchDataImgForman").show();
	   var jsObj={				
			stateId : stateId,
			districtId : distArrTemp,
			constituencyId : assmblyArrTemp,//228
			mandalId : mandalId,
			locationLevel : locationLevel,
			task:""
		}
		
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForman").hide();
			  var divId = "#manTowDivId";
				  if(locationLevel ==5){					  
				  divId = "#villWardId";
				 }
			$(divId).append("<option value='0'>All</option>");
			for(var i in result){
				$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
			if(locationLevel ==4)
					 getMandalVillageDetailsForConstituency(5);
		});
	}
$(document).on("change","#constituencyId",function(){
			$("#ConsErrorMSgShow").html("");
			getMandalVillageDetailsForConstituency(4);
});
$(document).on("change","#manTowDivId",function(){
	$("#ManErrorMSgShow").html("");
		if($("#manTowDivId").val() !=null && $("#manTowDivId").val().length>0 && $("#manTowDivId").val()>0){
			getMandalVillageDetailsForConstituency(5);
		}		
});
</script>
</body>
</html>