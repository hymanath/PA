<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Party Meetings - ATR & MOM</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<!--<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">-->
<!--<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">-->
<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="training/dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>


<style>
.modalImagesUl li {
    background-color: #672f0a;
    border: 2px solid;
    color: #fff;
    display: table-cell;
    margin: 0 3px;
    padding: 3px;
    text-align: center;
    vertical-align: middle;
}
.modalImagesUl li img {
    display: block;
    margin: auto;
}
.panelWidthCols
{
	width:20%;
}
.panelWidthCols .panel-body .table thead th , .panelWidthCols .panel-body .table tr td
{
	padding:3px !important;
}
#meetingTableId thead th , #meetingTableId tr td
{
	font-size:12px !important
}
footer{background-color:#5c2d25;color:#ccc;padding:30px}
header.eventsheader { 
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px; 
}
table thead th , table tbody tr td{text-align:center !important}
h1,h2,h3,h4,h5,h6,p,ul,table
{
	margin:0px !important;
}
.panelMeetings
{
	border-radius:0px;
}
.panelMeetings .panel-heading
{
	background:#fff;
	border-radius:0px;
	padding:5px;
	border:1px solid #ddd;
}
.panelMeetings .panel-heading .panel-title
{
	margin-top:15px;
}
.panelMeetings .panel-body
{
	padding:0px;
}
.text-capital
{
	text-transform:uppercase
}
.tableMeetings
{
	margin:0px;
}
.tableMeetings thead th
{
	font-size:11px;
	background:#FFF7CC;
}
.tableMeetings tr:first-child td:first-child
{
	color:#FFC501;
	font-size:18px;
	vertical-align:middle;
}
.tableMeetings tr:first-child td:nth-child(2)
{
	font-size:16px;
	color:#4A8CED
}
.tableMeetings tr:first-child td:nth-child(3)
{
	color:#F34F1A;
	font-size:16px
}
.block
{
	border:1px solid #ddd;
	padding:15px;
}
.graphList
{
	padding-left:10px;
}
.graphList li
{
	list-style:none;
	padding:2px;
	font-size:14px;
}
.iconPlanned
{
	background:#FEC601;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.iconConducted
{
	background:#458AE9;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.iconNotC
{
	background:#F64911;
	height:10px;
	width:10px;
	display:inline-block;
	margin-right:3px;
	border-radius:50%;
}
.m_top10
{
	margin-top:10px;
}
.slick-list
{
	margin:0px 20px !important;
}
.slick-prev ,.slick-next
{
	background:#fff;
	color:#333
}
.modalImagesUl
{ 
  padding:7px;
  width:100%;
  white-space: nowrap;
}
</style>

</head>
<body>
<!--<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
	<!--<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="background-color: rgb(239, 64, 54);">
					   <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
					   <!--<li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					   
					   <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER') ||  fn:containsIgnoreCase(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_GROUP_REDIRECT')}">
						<li><a href="callCenterTrainingAgentDashBoard.action"><span>CALLERS DASHBOARD</span></a> </li>
					   </c:if>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					</ul>   
            </div>			
        </div>       
    </div>
	
	
</header>-->
<main>
	<div class="container">
    	<div class="row">
        	<section>
            	<div class="col-md-12 col-xs-12 col-sm-12">
				<!-- Summary Block-->
				<div class="panel panel-default">
            	<div class="panel-heading">
                	<h4 class="panel-title">PARTY MEETINGS</h4>
					<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<span class="pull-right" style="margin-top:-21px">
						  <span class="btn btn-xs btn-success" style="border-radius:15px;"> 
							<input type="radio"  class="stateCls" style="cursor:pointer;" name="stateName" value="0" onclick="handleFunctions();"> <b>ALL</b>
						  </span>
						  <span class="btn btn-xs btn-success" style="border-radius:15px;"> 
							<input type="radio"  checked="true" class="stateCls" style="cursor:pointer;" name="stateName" value="1" onclick="handleFunctions();"> <b>AP</b>
						  </span> 
						  <span class="btn btn-xs btn-success" style="border-radius:15px;">
							<input type="radio" class="stateCls" style="cursor:pointer;" name="stateName" value="36" onclick="handleFunctions();"> <b>TS</b>
						  </span>
					</span>
					</c:if>
					
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-8 col-xs-12 col-sm-8">
                        	<div class="block">
                            	<div class="row">
                                	<div class="col-md-6 col-xs-12 col-sm-6">
                                    	<h3 class="text-capital" >total meetings - <img src='images/Loading-data.gif'  id="loadingImgForTotalId" style="width:20px;height:20px;display:none;"/><span id="totalMeetingsId">0</span></h3>
                                       <!-- <h5>( Meetings Conducted Ratio - <span class="text-success"><span class="dataClearCls" id="tConductedPercId">0%</span><i class="glyphicon glyphicon-arrow-up"></i></span> <span class="text-danger"><span id="tNotConductedPercId">0%</span><i class="glyphicon glyphicon-arrow-down"></i></span>)</h5>-->
									   <div style="margin-top: 5px;"></div>
									   <h5> Total Conducted Ratio - <span class="dataClearCls text-success" id="tConductedPercId">0%</span><br>
									   Total Not Conducted Ratio - <span id="tNotConductedPercId" class="text-danger">0%</span><br>
									   Toatl NotUpdated Ratio - <span id="tNotUpadtedPercId" class="text-warning">0%</span></h5>
                                    </div>
                                    <div class="col-md-2 col-xs-12 col-sm-2">
                                    	<div class="totalMeetings" style="height:80px"></div>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-4">
                                    	<ul class="graphList">
                                        	<li><span class="iconPlanned"></span>Planned <span class="pull-right dataClearCls" id="tPlannedId"><img src='images/Loading-data.gif'  id="loadingImgForTotalPlnId" style="width:10px;height:10px;display:none;"/>0</span></li>
                                            <li><span class="iconConducted"></span>Conducted <span class="pull-right dataClearCls" id="tConductedId"><img src='images/Loading-data.gif'  id="loadingImgForTotalCndId" style="width:10px;height:10px;display:none;"/>0</span></li>
                                            <li><span class="iconNotC"></span>Not Conducted <span class="pull-right dataClearCls" id="tNotConductedId"><img src='images/Loading-data.gif'  id="loadingImgForTotalNtCndId" style="width:10px;height:10px;display:none;"/>0</span></li>
                                        </ul>
                                    </div>
                                </div>	
                            </div>
                        </div>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<div class="block">
								<label >Select Date</label>
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i><span class="caret"></span></span>
									<input type="text" class="form-control" id="datePickerBlockId">
									<span></span>
								</div>
							<!--	<div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc ;">
								  <i class="glyphicon glyphicon-calendar"></i><div class="caret"></div>
								  <span style="margin-left: 25px;"></span> 
								</div>-->
								<p style="font-size:11px;margin-top:5px !important" class="text-muted">*Select Date Range to view date between meetings overview</p>
							</div>
                             
                        </div>
                    </div>
                    <div class="row m_top10">
					<center><img src='images/Loading-data.gif'  id="loadingImgForLevelId" style="width:100px;height:100px;display:none;"/></center>
						<div id="levelBuildingId"></div>
					</div>
                    	<!--<div class="col-md-3 col-xs-12 col-sm-6">
                       	  <div class="panel panel-default panelMeetings">
                        	  <div class="panel-heading">
                        	    <div class="row">
                                	<div class="col-md-8 col-xs-12 col-sm-7">
                                    	<h4 class="panel-title text-capital">district level meetings</h4>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-5">
                                    	<div class="districtMeetings" style="height:60px;"></div>
                                    </div>
                                </div>
                      	      </div>
                        	  <div class="panel-body">
                              	<table class="table table-bordered tableMeetings">
                                	<thead>
                                    	<th>Planned</th>
                                        <th>Conducted</th>
                                        <th>Not Conducted</th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td rowspan="2">100</td>
                                            <td>49</td>
                                            <td>51</td>
                                        </tr>
                                        <tr>
                                        	<td>49%</td>
                                            <td>51%</td>
                                        </tr>
                                    </tbody>
                                </table>
                              </div>
                      	  </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                       	  <div class="panel panel-default panelMeetings">
                        	  <div class="panel-heading">
                        	    <div class="row">
                                	<div class="col-md-8 col-xs-12 col-sm-7">
                                    	<h4 class="panel-title text-capital">constituency level meetings</h4>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-5">
                                    	<div class="consMeetings" style="height:60px;"></div>
                                    </div>
                                </div>
                      	      </div>
                        	  <div class="panel-body">
                              	<table class="table table-bordered tableMeetings">
                                	<thead>
                                    	<th>Planned</th>
                                        <th>Conducted</th>
                                        <th>Not Conducted</th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td rowspan="2">100</td>
                                            <td>49</td>
                                            <td>51</td>
                                        </tr>
                                        <tr>
                                        	<td>49%</td>
                                            <td>51%</td>
                                        </tr>
                                    </tbody>
                                </table>
                              </div>
                      	  </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                       	  <div class="panel panel-default panelMeetings">
                        	  <div class="panel-heading">
                        	    <div class="row">
                                	<div class="col-md-8 col-xs-12 col-sm-7">
                                    	<h4 class="panel-title text-capital">mandal level meetings</h4>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-5">
                                    	<div class="mandalMeetings" style="height:60px;"></div>
                                    </div>
                                </div>
                      	      </div>
                        	  <div class="panel-body">
                              	<table class="table table-bordered tableMeetings">
                                	<thead>
                                    	<th>Planned</th>
                                        <th>Conducted</th>
                                        <th>Not Conducted</th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td rowspan="2">100</td>
                                            <td>49</td>
                                            <td>51</td>
                                        </tr>
                                        <tr>
                                        	<td>49%</td>
                                            <td>51%</td>
                                        </tr>
                                    </tbody>
                                </table>
                              </div>
                      	  </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                       	  <div class="panel panel-default panelMeetings">
                        	  <div class="panel-heading">
                        	    <div class="row">
                                	<div class="col-md-8 col-xs-12 col-sm-7">
                                    	<h4 class="panel-title text-capital">village level meetings</h4>
                                    </div>
                                    <div class="col-md-4 col-xs-12 col-sm-5">
                                    	<div class="villageMeetings" style="height:60px;"></div>
                                    </div>
                                </div>
                      	      </div>
                        	  <div class="panel-body">
                              	<table class="table table-bordered tableMeetings">
                                	<thead>
                                    	<th>Planned</th>
                                        <th>Conducted</th>
                                        <th>Not Conducted</th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td rowspan="2">100</td>
                                            <td>49</td>
                                            <td>51</td>
                                        </tr>
                                        <tr>
                                        	<td>49%</td>
                                            <td>51%</td>
                                        </tr>
                                    </tbody>
                                </table>
                              </div>
                      	  </div>
                        </div>
                    </div>
                </div>
            </div> -->
			<!-- Sumamry Block-->
				
                	<div class="panel panel-default" style="border:1px solid #ddd !important">
                    	<div class="panel-heading">
                        	<h4 class="panel-title">Search / Update Meetings Conducted Status</h4>
                        </div>
                        <div class="panel-body">
                        	<div class="row">
								<div class="col-md-4 col-xs-12 col-sm-3">
									<label>Meeting Level</label>
									<span id="meetingLocationErrorMessage" style="color: red;"></span>
										<select class="form-control" id="meetingLocationLevel"></select>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMeetingsList" style="width:20px;height:20px;display:none;"/>
								</div>	
								
                            	<div class="col-md-4 col-xs-12 col-sm-3">
								
									<label>Select Meeting Name/Type Of Meeting</label>
									<select class="form-control" id="typeOfMeeting">
										<option> Select Meeting Type </option>
									</select>
									<span id="typeofMeetingErrorMessage" style="color: red;"></span>
									<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgFortypeOfMeeting" style="width:20px;height:20px;display:none;"/>
									
                                </div>
								
								
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
										<select class="form-control" id="constituencyId" name="constBox">
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
								<div class="col-xs-12 col-md-3 col-sm-6" >
									<button class="btn btn-success btn-sm btn-block" style="margin-top:25px;" id="viewMeetings">View</button>
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
							<div class="row m_top10" >
							
							
							
							
							
							
                        </div>
					 </div>
					<div class="row m_top20" style="padding:10px;margin-top:35px;">
						<div class="col-md-12">
							<center><img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForResults" style="margin-left:0px;margin-top: 30px;width:20px;height:20px;display:none;"/></center><div id="meetingDetailsTableId"></div>
						</div>
					</div>
                </div>
            </section>
        </div>
    </div>
</main>
<div class="modal fade" id="editConductedBtnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width: 80%;">
    <div class="modal-content">
      <div class="modal-body" id="editConductedModalBody">
		<!--<label>Change Status</label>-->
		<!--<select class="form-control" id="isConductedUpdateId">
			<option value="0">Select Status</option>
			<option value="Y">Yes</option>
			<option value="N">No</option>
		</select>-->
		<span id="errorId"></span>
		<div class="input-group" style="display:none" id="dateUpdateId">
			<input type="text" class="form-control" id="dateValueId" />
			<span class="input-group-addon">
				<i class="glyphicon glyphicon-calendar"></i>
			</span>			
		</div>
		<textarea type="text" class="form-control" style="display:none" id="remarksUpdateId" ></textarea>
		<input type="hidden" value="" id="hiddenMeetingId" />
		<input type="hidden" value="" id="hiddenAttrId" />
      </div>
      <div class="modal-footer" style="padding:5px;">
	  <button type="button" class="btn btn-success btn-xs" id="updateDetailsBtnId">SAVE</button>
        <button type="button" class="btn btn-default btn-xs" data-dismiss="modal">CLOSE</button>        
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document"  style="width: 80%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modelTitle"></h4>
      </div>
      <div class="modal-body">
		<div class="row">
			<div  id="modelBody"></div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- srujana-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" style="width:80%">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close imageCloseBtnCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modelTitle1"></h4>
      </div>
      <div class="modal-body">
		<div class="row">
			<div  id="modelBody1"></div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default imageCloseBtnCls" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!--<footer>
		<p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>-->
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>

<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.js" type="text/javascript"></script>
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="dist/HighCharts/highcharts.js"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<!--<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>-->
<script type="text/javascript">

$("#datePickerBlockId").daterangepicker({
	opens:'left',
	applyClass: 'btn-small btn-primary datePickerBlockCls',
	ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		 'Last 3 Months': [moment().subtract(3, 'month'),moment()],
		 'Last 6 Months': [moment().subtract(6, 'month'),moment()],
		 'Last 1 Year': [moment().subtract(1, 'year'),moment()]
		 }
});

$("#datePickerBlockId").val(moment().subtract(29, 'days').format("MM/DD/YYYY") +'-'+ moment().format("MM/DD/YYYY"));

//Adding Class To "ranges" Class
$(document).find(".datePickerBlockCls").closest(".ranges").addClass("datePickerRangesCls");

$("#dateValueId").daterangepicker({singleDatePicker: true,maxDate: moment()})
getUserAccessLocationDetails();
	
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
				setTimeout(function(){
					$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					//$("#viewMeetings").trigger( "click" );
				},1000); 
			} else if(stateArr.length>0 && distArr.length>0 && parlArr.length==0){
				$('#meetingLocationLevel').val('2').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					$('#districtId').val(distArr[0]).trigger('change');
					//$("#viewMeetings").trigger( "click" );
					
				},1000); 
				
			}else if(parlArr.length>0){
				$('#meetingLocationLevel').val('3').trigger('change');
				$('#statesDivId').val(stateArr[0]).trigger('change');
				setTimeout(function(){
					$('#typeOfMeeting').val(firstMeetingId).trigger('change');
					$('#districtId').val(distArr[0]).trigger('change');
					
				},1000); 
				
				setTimeout(function(){
					$('select[name="constBox"] option:eq(2)').attr('selected', 'selected');
					//$("#viewMeetings").trigger( "click" );
				},2000);
			} 
		});
	}
	

	   /* $('#fromDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  }); 
	  
	  $('#endDate').daterangepicker(
	  {
		   singleDatePicker: true, 
		   timePicker: true,
		   format: 'DD/MM/YYYY h:mm A'
	  }, function(start, end, label) {
		//console.log(start.toISOString(), end.toISOString(), label);
	  });  */
	  
	  var cb = function(start, end, label) {
		 //console.log(start.toISOString(), end.toISOString(), label);
		  $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		 }

		 var optionSet1 = {
		 startDate: moment().subtract(29, 'days'),
		 endDate: moment(),
		 minDate: '01/01/2012',
		 maxDate: '12/31/2018',
		 //dateLimit: { days: 60 },
		 showDropdowns: true,
		 showWeekNumbers: true,
		 timePicker: false,
		 timePickerIncrement: 1,
		 timePicker12Hour: true,
		 ranges: {
			 'Today': [moment(), moment()],
			 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			 'This Month': [moment().startOf('month'), moment().endOf('month')],
			 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			 'Last 3 Months': [moment().subtract(3, 'month'),moment()],
			 'Last 6 Months': [moment().subtract(6, 'month'),moment()],
			 'Last 1 Year': [moment().subtract(1, 'year'),moment()]
		 },
		 opens: 'left',
		 buttonClasses: ['btn btn-default'],
		 applyClass: 'btn-small btn-primary newsSubmitBtn',
		 cancelClass: 'btn-small',
		 format: 'MM/DD/YYYY',
		 separator: ' to ',
		 locale: {
		 applyLabel: 'Submit',
		 cancelLabel: 'Clear',
		 fromLabel: 'From',
		 toLabel: 'To',
		 customRangeLabel: 'Custom',
		 daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		 monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		 firstDay: 1
		 }
		 };

		 var optionSet2 = {
		 startDate: moment().subtract(7, 'days'),
		 endDate: moment(),
		 opens: 'left',
		 ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		 }
		 };

		 $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

		 $('#reportrange').daterangepicker(optionSet1, cb);

		 //$('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
		 //$('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
		 /* $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
		 console.log("apply event fired, start/end dates are " 
		 + picker.startDate.format('MMMM D, YYYY') 
		 + " to " 
		 + picker.endDate.format('MMMM D, YYYY')
		 ); 
		 }); */
		 /* $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); }); */

   
   //$("#mainheading").html(" PARTY MEETINGS - MOM & ATR ");
</script>
<script>
var jsonGlob = "";
	function getTheMeetingLevelDetails(){
		$("#searchDataImgForMeetingsList").show();
		var jsObj =	{}
		
		$.ajax(
		{
			type: "POST",
			url:"getTheMeetingLevelDetailsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
			if(result!=null && result.length>0){
				for(var i in result){
				$("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
				}
			}
			$("#searchDataImgForMeetingsList").hide();
		});
	}
	
	$( "#typeOfMeeting" ).change(function() {
		$("#typeofMeetingErrorMessage").html("");
	});
	
	$( "#meetingLocationLevel" ).change(function() {
		//default Date
		$('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
		
		var locationLevelsArr=[];
	 $("#meetingLocationErrorMessage").html("");
	  $("#searchDataImgFortypeOfMeeting").show();
	  
	  var locationLevelId = $(this).val();
	  if($(this).val() == 4){
		  locationLevelsArr.push(4);
		  locationLevelsArr.push(5);
		  locationLevelsArr.push(6);
	  }else if($(this).val() == 7){
		  locationLevelsArr.push(7);
		  locationLevelsArr.push(8);
	  }else{
		  locationLevelsArr.push(locationLevelId);
	  }
	  
		var jsObj =	{locationLevelsArr:locationLevelsArr}
		
		$.ajax(
		{
			type: "POST",
			url:"getMeetingTypesNewAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			$("#typeOfMeeting  option").remove();
			$("#typeOfMeeting").append('<option value="0">Select Meeting Type</option>');
			if(result!=null && result.length>0){
				firstMeetingId=result[0].id;
				for(var i in result){
					$("#typeOfMeeting").append('<option value="'+result[i].id+'">'+result[i].meetingType+'</option>');
				}
			}
			
			$("#searchDataImgFortypeOfMeeting").hide();
		});
		
	});
	
	$("#viewMeetings").click(function() {
		
		$("#meetingDetailsTableId").html("");
				
		if($("#meetingLocationLevel").val()==0){
			$("#meetingLocationErrorMessage").html("Please Select Meeting Location");
			return;
		}else {
			$("#meetingLocationErrorMessage").html('');
		}
		
		var locationLevel = $("#meetingLocationLevel").val();
		
		if($("#typeOfMeeting").val()==0){
			$("#typeofMeetingErrorMessage").html("Please Select Meeting");
			//alert("Pleas Select Meeting");
			return;
		}
		else{
			$("#typeofMeetingErrorMessage").html('');
		}
		var meetingType = $("#typeOfMeeting").val();
		
		var stateId=[];
		var districtId=[];
		var constituencyId=[];
		var mandalTownDivisonId=[];
		var villageWardId =[];
		
		
		if(locationLevel == 5 || locationLevel == 6){
			locationLevel = 4;
		}
		if(locationLevel == 7 || locationLevel == 8){
			locationLevel = 5;
		}
		
		
		
		if(locationLevel == 1){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				
				return;
			}else{
				$("#stateErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel == 2){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}
			else{
				$("#stateErrorMSgShow").html('');
			}
			 if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}
			else{
				
				$("#districtErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel==3){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}
			else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}
			else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				
				$("#ConsErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
			}
			//$("#searchDataImgForResults").show();
		}	
		
		
		if(locationLevel==4){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				$("#ConsErrorMSgShow").html('');
			}
			if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				$("#ManErrorMSgShow").html("Please Select Mandal/Town/Division");
				//alert("Please Select Mandal/Town/Division");
				return;
			}else{
				$("#ManErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
				mandalTownDivisonId.push($("#manTowDivId").val());
			}
			//$("#searchDataImgForResults").show();
		}
		
		
		if(locationLevel==5){
			if($("#statesDivId").val()==""){
				$("#stateErrorMSgShow").html("Please Select State");
				return;
			}else{
				$("#stateErrorMSgShow").html('');
			}
			if($("#districtId").val()=="" || $("#districtId").val()=="Select District"){
				$("#districtErrorMSgShow").html("Please Select District");
				//alert("Please Select District");
				return;
			}else{
				$("#districtErrorMSgShow").html('');
			}
			if($("#constituencyId").val()=="" || $("#constituencyId").val()=="Select Constituency"){
				$("#ConsErrorMSgShow").html("Please Select Constituency");
				//alert("Please Select Constituency");
				return;
			}else{
				$("#ConsErrorMSgShow").html('');
			}
			if($("#manTowDivId").val()=="" || $("#manTowDivId").val()=="Select Mandal/Town/Division"){
				$("#ManErrorMSgShow").html("Please Select Mandal/Town/Division");
				//alert("Please Select Mandal/Town/Division");
				return;
			}else{
				$("#ManErrorMSgShow").html('');
			}
			if($("#villWardId").val()=="" || $("#villWardId").val()=="Select Village/Ward"){
				$("#VillErrorMSgShow").html("Please Select Village/Ward");
				//alert("Please Select Village/Ward");
				return;
			}else{
				
				$("#VillErrorMSgShow").html('');
				stateId = getStateDistrictAssemblySelection("state");
				districtId = getStateDistrictAssemblySelection("district");
				constituencyId = getStateDistrictAssemblySelection("constituency");
				mandalTownDivisonId.push($("#manTowDivId").val());
				villageWardId.push($("#villWardId").val());
			}
			//$("#searchDataImgForResults").show();
		}
		
		$("#villWardId").change(function(){
			$("#VillErrorMSgShow").html();
		});
		
		/* var startDate = $(".newsSubmitBtn").closest(".range_inputs").find(".dp_startDate").val();
		var endDate = $(".newsSubmitBtn").closest(".range_inputs").find(".dp_endDate").val(); */
		var dates = $("#datePickerBlockId").val();
		var startDate = "";
		var endDate ="";
		if(dates !=null && dates.length>0){
			startDate = dates.split("-")[0];
			endDate =dates.split("-")[1];
		}
		
		
		$("#searchDataImgForResults").show();
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						sateId:stateId,
						districtId:districtId,
						constituencyId:constituencyId,
						mandalTownDivisonId:mandalTownDivisonId,
						villageWardId:villageWardId,
						startDate:startDate,
						endDate:endDate
					}
		jsonGlob = jsObj;	
		$.ajax(
		{
			type: "POST",
			url:"getAllMeetingsAction.action",
			data:{task :JSON.stringify(jsObj)}
		}
		).done(function(result){
			
			$("#searchDataImgForResults").hide();
			
			var str='';
			if(result!=null && result.length>0){
				str+='<h4><b>'+result[0].meetingType+'</b></h4>';
				str+='<div class="table-responsive">'
				str+='<table class="m_top20 table table-bordered m_top10 table-condensed">'
				str+='<thead>';
				str+='<th>Total Meeting</th>';				
				str+='<th>MOM Files</th>';
				str+='<th>MOM Text</th>';
				str+='<th>ATR Files</th>';
				str+='<th>ATR Text</th>';				
				str+='</thead>';
				str+='<tbody>';
				str+='<tr>'
				str+='<td>'+result.length+'</td>';
				var momFiles=0,momText=0,atrFiles=0,atrText=0;
				for(var i in result){
					if(result[i].docTxtInfo != null){
						momFiles = result[i].docTxtInfo.momFilesCount != null && result[i].docTxtInfo.momFilesCount > 0 ? momFiles+result[i].docTxtInfo.momFilesCount : momFiles;
						momText = result[i].docTxtInfo.momPointsCount != null && result[i].docTxtInfo.momPointsCount > 0 ? momText+result[i].docTxtInfo.momPointsCount : momText;
						atrFiles = result[i].docTxtInfo.atrFilesCount != null && result[i].docTxtInfo.atrFilesCount > 0 ? atrFiles+result[i].docTxtInfo.atrFilesCount : atrFiles;
						atrText = result[i].docTxtInfo.atrTextCount != null && result[i].docTxtInfo.atrTextCount > 0 ? atrText+result[i].docTxtInfo.atrTextCount : atrText;
					}					
				}
				str+='<td attr_type="MINUTE" attr_action_id="'+1+'"';
				if(momFiles > 0)
					str+=' class="allMomAtrCountsCls" style="color:green;cursor:pointer"';
				str+='>'+momFiles+'</td>';
				str+='<td attr_type="momText"';
				if(momText > 0)
					str+=' class="allMomAtrCountsCls" attr_action_id="'+1+'" style="color:green;cursor:pointer"';
				str+='>'+momText+'</td>';
				str+='<td attr_type="ATR"';
				if(atrFiles > 0)
					str+=' class="allMomAtrCountsCls" attr_action_id="'+1+'" style="color:green;cursor:pointer"';
				str+='>'+atrFiles+'</td>';
				str+='<td attr_type="atrText"';
				if(atrText > 0)
					str+=' class="allMomAtrCountsCls" attr_action_id="'+1+'" style="color:green;cursor:pointer"';
				str+='>'+atrText+'</td>';
				str+='</tr>'
				str+='</tbody>';
				str+='</table>'
				str+='</div>';
				str+='<div class="table-responsive">';
				str+='<table class="m_top20 table table-bordered m_top10 table-condensed" id="meetingTableId">';
				/* str+='<thead class="bg_d">';
				//str+='<th>Meeting Type</th>';
				str+='<th>Meeting Location</th>';
				str+='<th>Meeting Name</th>';
				str+='<th>Schedule Date</th>';
				str+='<th></th>';
				str+='</thead>'; */
				str+='<thead class="bg_d">';
				str+='<tr>';
				if(locationLevel==3){
					str+='<th rowspan="2">Constituency Number</th>';
				}
				str+='<th rowspan="2">Meeting Location</th>';
				str+='<th rowspan="2">Meeting Name</th>';
				str+='<th rowspan="2">CONDUCTED</th>';
				str+='<th rowspan="2"> IVR CONDUCTED STATUS</th>';
				str+='<th rowspan="2">CONDUCTED DATE</th>';
				str+='<th rowspan="2">REMARKS</th>';
				str+='<th colspan="2">MOM</th>';
				str+='<th colspan="2">ATR</th>'; 
				str+='<th rowspan="2">PLANNED DATE</th>';				
				str+='<th rowspan="2">UPDATE FOR MOM/ATR </th>';
				str+='</tr>';
				str+='<tr>';
				str+='<th>Files</th>';
				str+='<th>Text</th>';
				str+='<th>Files</th>';
				str+='<th>Text</th>';
				str+='</tr>';
				str+='</thead>';
				str+='<tbody id="">';
				for(var i in result){
					str+='<tr>';
					//str+='<td>'+result[i].meetingType+'</td>';
					if(locationLevel==3){
						str+='<td>'+result[i].constLocationNum+'</td>';
					}
					str+='<td>'+result[i].location+'</td>';
					str+='<td>'+result[i].meetingName+'</td>';
					
					/* if(result[i].isConducted !=null && result[i].isConducted == 'Y'){
						str+='<td><i class="glyphicon glyphicon-edit editConductedBtn" style="cursor:pointer" attr_isConducted='+result[i].isConducted+'></i> <i class="glyphicon glyphicon-ok" style="color:green"></i></td>';
					}else if(result[i].isConducted !=null && result[i].isConducted == 'N'){
						str+='<td><i class="glyphicon glyphicon-edit editConductedBtn" style="cursor:pointer" attr_isConducted='+result[i].isConducted+'></i><i class="glyphicon glyphicon-remove" style="color:red"></i></td>';
					}else{
						str+='<td> <i class="glyphicon glyphicon-edit editConductedBtn" style="cursor:pointer" attr_isConducted="0"></i> </td>';
					} */
					
					/* if(result[i].isConducted !=null && result[i].isConducted == 'Y'){
						str+='<td><input type="radio" id="yesId'+i+'" name="first_item'+i+'" value="Y" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'" checked />YES';
						str+='<input type="radio" id="noId'+i+'" name="first_item'+i+'" value="N" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'" />NO</td>';
					}else if(result[i].isConducted !=null && result[i].isConducted == 'N'){
						str+='<td><input type="radio" id="yesId'+i+'" name="first_item'+i+'" value="Y" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'"  />YES';
						str+='<input type="radio" id="noId'+i+'" name="first_item'+i+'" value="N" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'" checked />NO</td>';
					}else{
						str+='<td><input type="radio" id="yesId'+i+'" name="first_item'+i+'" value="Y" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'" />YES';
						str+='<input type="radio" id="noId'+i+'" name="first_item'+i+'" value="N" class="updateConductedCls" attr_meetingId="'+result[i].partyMeetingId+'" />NO</td>';
					}  */
					
					str+='<td>';
					if(result[i].isConducted !=null && result[i].isConducted == 'Y'){
						str+='<select  style="width:70px !important;margin:auto" class="form-control updateConductedCls" id="updateConducted'+i+'Id" attr_meetingId="'+result[i].partyMeetingId+'">';
							str+='<option value="0"></option>';
							str+='<option value="Y" selected>Yes</option>';
							str+='<option value="N">No</option>';
						str+='</select>';
					}else if(result[i].isConducted !=null && result[i].isConducted == 'N'){
						str+='<select style="width:70px !important;margin:auto" class="form-control updateConductedCls" id="updateConducted'+i+'Id" attr_meetingId="'+result[i].partyMeetingId+'">';
							str+='<option value="0"></option>';
							str+='<option value="Y">Yes</option>';
							str+='<option value="N" selected>No</option>';
						str+='</select>';
					}else{
						str+='<select style="width:70px !important;margin:auto" class="form-control updateConductedCls" id="updateConducted'+i+'Id" attr_meetingId="'+result[i].partyMeetingId+'">';
							str+='<option value="0"></option>';
							str+='<option value="Y">Yes</option>';
							str+='<option value="N">No</option>';
						str+='</select>';
					}
					str+='</td>';
					if(result[i].ivrStatus != null && result[i].ivrStatus != "" && result[i].ivrStatus == 'Y'){
						str+='<td>Yes</td>';
					}else{
						str+='<td>No</td>';	
					}
					
				
				
				
					
					if(result[i].conductedDate !=null && result[i].conductedDate !=""){
						//str+='<td> '+result[i].conductedDate.split(' ')[0]+' <i class="glyphicon glyphicon-edit editConductedDateBtn" style="cursor:pointer" attr_conductedDate="'+result[i].conductedDate.split(' ')[0]+'" attr_meetingId='+result[i].partyMeetingId+'></i></td>';
						str+='<td> <a class="editConductedDateBtn" style="cursor:pointer" attr_conductedDate="'+result[i].conductedDate.split(' ')[0]+'" attr_meetingId='+result[i].partyMeetingId+' id="conductedDateId'+i+'" >'+result[i].conductedDate.split(' ')[0]+'</a></td>';
					}else{
						//str+='<td> <i class="glyphicon glyphicon-edit editConductedDateBtn" style="cursor:pointer" attr_conductedDate="" attr_meetingId='+result[i].partyMeetingId+'></i></td>';
						if(result[i].isConducted !=null && result[i].isConducted == 'Y'){
							str+='<td> <a><i class="editConductedDateBtn" style="cursor:pointer" attr_conductedDate="" attr_meetingId='+result[i].partyMeetingId+' id="conductedDateId'+i+'">Enter Date</i></a></td>';
						}else{
							str+='<td>-</td>';
						}
						
					}
					
					if(result[i].remarks !=null && result[i].remarks !=""){
						//str+='<td>'+result[i].remarks+' <i class="glyphicon glyphicon-edit editRemarksBtn" style="cursor:pointer" attr_remarks="'+result[i].remarks+'" attr_meetingId="'+result[i].partyMeetingId+'"></i></td>';
						str+='<td><a class="editRemarksBtn" style="cursor:pointer" attr_remarks="'+result[i].remarks+'" attr_meetingId="'+result[i].partyMeetingId+'" id="remarksId'+i+'" >'+result[i].remarks+'</a></td>';
					}else{
						//str+='<td> <i class="glyphicon glyphicon-edit editRemarksBtn" style="cursor:pointer" attr_remarks="" attr_meetingId="'+result[i].partyMeetingId+'"></i></td>';
						str+='<td> <a><i class="editRemarksBtn" style="cursor:pointer" attr_remarks="" attr_meetingId="'+result[i].partyMeetingId+'" id="remarksId'+i+'">Enter Remarks</i></a></td>';
					}
					
					
					if(result[i].docTxtInfo!=null && result[i].docTxtInfo.momFilesCount!=null && result[i].docTxtInfo.momFilesCount>0){
						str+='<td style="color:green;cursor:pointer" class="getSummary" attr_meetingId="'+result[i].partyMeetingId+'" attr_type="MINUTE" >'+result[i].docTxtInfo.momFilesCount+'</td>';
					}else{
						str+='<td>0</td>';
					}
					
					if(result[i].docTxtInfo!=null && result[i].docTxtInfo.momPointsCount!=null  && result[i].docTxtInfo.momPointsCount>0){
						str+='<td style="color:green;cursor:pointer" class="getSummary"  data-toggle="modal" attr_meetingId="'+result[i].partyMeetingId+'" attr_type="momText">'+result[i].docTxtInfo.momPointsCount+'</td>';
					}else{
						str+='<td>0</td>';
					}
					
					if(result[i].docTxtInfo!=null && result[i].docTxtInfo.atrFilesCount!=null && result[i].docTxtInfo.atrFilesCount>0){
						str+='<td style="color:green;cursor:pointer" class="getSummary" attr_meetingId="'+result[i].partyMeetingId+'" attr_type="ATR">'+result[i].docTxtInfo.atrFilesCount+'</td>';
					}else{
						str+='<td>0</td>';
					}
					
					if(result[i].docTxtInfo!=null && result[i].docTxtInfo.atrTextCount!=null && result[i].docTxtInfo.atrTextCount>0){
						str+='<td style="color:green;cursor:pointer" class="getSummary" attr_meetingId="'+result[i].partyMeetingId+'" attr_type="atrText">'+result[i].docTxtInfo.atrTextCount+'</td>';
					}else{
						str+='<td>0</td>';
					}
					
					if(result[i].startTime == result[i].endTime){
						str+='<td>'+result[i].startTime+'</td>';
					}else{
						str+='<td>'+result[i].startTime+' to '+result[i].endTime+'</td>';
					}
					
					
					
					//str+='<td><button class="btn btn-success btn-sm" onclick="updateMeeting(\''+result[i].partyMeetingId+'\');">UPDATE</button></td>';
					if((result[i].isConducted !=null && result[i].isConducted == 'Y') || (result[i].ivrStatus !=null && result[i].ivrStatus == 'Y') ){
					str+='<td><i class="glyphicon glyphicon-edit" style="cursor:pointer" onclick="updateMeeting(\''+result[i].partyMeetingId+'\');" data-toggle="tooltip" data-placement="top" title="Click here to update MOM/ATR"></i></td>';
					}else{
						str+='<td>-</td>';
					}
				}
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
			}else{
				str+='No Meetings Found';
			}
			//$("#searchDataImgForResults").hide();
			$('#meetingDetailsTableId').html(str);
			
			$('#meetingTableId').dataTable({
			"iDisplayLength": 10,
				"aLengthMenu": [[10,50,100, 200, 500, -1], [10,50,100, 200, 500, "ALL"]]			
			});
			//$('#meetingTableId').removeClass("dataTable");
			$('#meetingTableId_wrapper').css("margin-top","20px");
			
			 $('[data-toggle="tooltip"]').tooltip();
			
		});
	});
	
	function getStateDistrictAssemblySelection(forLocation){
		var resultArr = [];
		if(forLocation=="state"){
			var state = $("#statesDivId").val();
			var stateId = [];
			if(state==0){
				if(stateArr.length>0){
					stateId = stateArr;
				}else{
					stateId.push(1);
					stateId.push(36);
				}
			}else{
				stateId.push(state);
			}
			resultArr = stateId;
		}else if(forLocation=="district"){
			var district = $("#districtId").val();
			var districtId = [];
			if(district==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
				}
			}else{
				districtId.push(district);
			}
			resultArr = districtId;
			
			//getMandalsForDistrictId(districtId);
			//getVillagesForDistrictId(districtId);
		}else if(forLocation=="constituency"){
			var assembly = $("#constituencyId").val();
			var constituencyId = [];
			if(assembly==0){
				if(assmblyArr.length>0){
					constituencyId = assmblyArr;
				}else{
					constituencyId.push(0);
				}
			}else{
				constituencyId.push(assembly);
			}
			
			resultArr = constituencyId;
		}
		return resultArr;
		
	}
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
		    $("#districtId").append('<option value="0">ALL</option>');
			
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
	   });
	}
	function getConstituenciesForDistricts(district){
	   
	   $("#manTowDivId  option").remove();
		$("#manTowDivId").append('<option value="0">ALL</option>');
		$("#villWardId  option").remove();
		$("#villWardId").append('<option value="0">ALL</option>');
	   
		$("#constituencyId  option").remove();
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
		   $("#constituencyId").append('<option value="0">ALL</option>');
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
				<c:if test="${sessionScope.USER.isAdmin == 'true'}">
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
							getDistrictsForStates(this.value);
							}
						}
					});
				</c:if>	

meetingLevelWiseHideShow();	
});			
			
		$("#constituencyId").change(function(){
			$("#ConsErrorMSgShow").html("");
			getMandalVillageDetails(4);
		});
		$("#manTowDivId").change(function(){
			$("#ManErrorMSgShow").html("");
				if($("#manTowDivId").val() !=null && $("#manTowDivId").val().length>0 && $("#manTowDivId").val()>0){
					getMandalVillageDetails(5);
				}
			
		});
function getMandalVillageDetails(locationLevel){
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
			$(divId).append("<option value='0'>ALL</option>");
			for(var i in result){
				$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
			if(locationLevel ==4)
					 getMandalVillageDetails(5);
		});
	}
		
	function updateMeeting(partyMeetingId){
		window.open('meetingDetailsList.action?partyMeetingId='+partyMeetingId+'', '_blank');
		//alert(meetingType+"--"+meetingLocationLevel);
	}
	
	$(document).on('click','.getSummary', function() {
	  var fromType = $(this).attr("attr_type");
	  var meetingId = $(this).attr("attr_meetingId");
	  globalImgsMdlCnt = 1;
	  var jsObj =	{
			meetingId : $(this).attr("attr_meetingId"),
			type:$(this).attr("attr_type")
			}
			
		$.ajax({
			type: "POST",
			url:"getSummaryForAMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			
			if(fromType=="momText"){
				if(result!=null && result.minutes!=null && result.minutes.length>0){
					var str='';
					for(var i in result.minutes){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.minutes[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else if(fromType=="atrText"){
				if(result!=null && result.atrPoints!=null && result.atrPoints.length>0){
					var str='';
					for(var i in result.atrPoints){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result.atrPoints[i]+"</div>";
					}
					$("#modelBody").html(str);
				}
				
				$("#modelTitle").html(result.subName);
				$("#myModal").modal('toggle');
			}else{
				buildSummaryForAMeeting(result,meetingId,fromType);
			}
			
		});
	 
  });

</script>
<script type="text/javascript">
$(function () {
    /* $('.districtMeetings').highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: false,
                alpha: 15
            },
			backgroundColor: 'transparent'
        },
		legend: {
                enabled: false,
                align: 'center',
                x: 10,
                verticalAlign: 'left',
                y: 20,
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 35,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
            data: [
                ['Nara Lokesh Meeting with Mandal Presidents', 1000],
                ['Lunch Section Party Office', 3000],
                ['Lokesh Meeting', 1000]
            ]
        }]
    }); */
});

$(document).on("click",".datePickerBlockCls",function(){
	getLevelWiseMeetingDetails();
});
$(document).on("click", ".datePickerRangesCls li",function(){
	var selc_text = $(this).text();
		if(selc_text!="Custom Range"){				
				getLevelWiseMeetingDetails();
		}	
});
getLevelWiseMeetingDetails();
function getLevelWiseMeetingDetails(){
	
		$("#levelBuildingId").html('');
	
		var value = $("#datePickerBlockId").val();
		var fromDate = "";
		var toDate ="";
		if(value !=null && value.length>0){
			fromDate = value.split("-")[0];
			toDate =value.split("-")[1];
		}
		var stateId = 0;
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
			stateId = $(this).val();	
			}
		});
		var jsObj={
			fromDate:fromDate,
			toDate	:toDate,
			stateId : stateId
		}
		
		$.ajax({
			type: "POST",
			url:"getLevelWiseMeetingDetailsAction.action",
			data:{task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#loadingImgForLevelId").hide();
			var str='';
			if(result !=null && result.length>0){
				var totalPlanned=0;
				var totalConducted=0;
				var totalNotConducted=0;
				var noOfBlocks = result.length;
				var totalNotUpdated = 0;
				for(var i in result){
					totalPlanned=totalPlanned+result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
					totalConducted= totalConducted + result[i].invitedCount;
					totalNotConducted= totalNotConducted + result[i].nonInviteeCount;
					totalNotUpdated= totalNotUpdated + result[i].attendedCount;
					if(noOfBlocks == 3)
					{
						str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					}else if(noOfBlocks == 4)
					{
						str+='<div class="col-md-3 col-xs-12 col-sm-6">';
					}else if(noOfBlocks == 5)
					{
						str+='<div class="col-md-3 col-xs-12 col-sm-6 panelWidthCols">';
					}else if(noOfBlocks == 6)
					{
						str+='<div class="col-md-4 col-xs-12 col-sm-6">';
					}
					var locationLevel;
					if(result[i].name != null && result[i].name == "STATE"){
						locationLevel =1;
					}else if(result[i].name != null && result[i].name == "DISTRICT"){
						locationLevel =2;
					}else if(result[i].name != null && result[i].name == "CONSTITUENCY"){
						locationLevel =3;
					}else if(result[i].name != null && result[i].name == "MANDAL"){
						locationLevel =4;
					}else if(result[i].name != null && result[i].name == "VILLAGE"){
						locationLevel =5;
					}
					
					
                       	  str+='<div class="panel panel-default panelMeetings">';
                        	  str+='<div class="panel-heading">';
                        	    str+='<div class="row">';
                                	str+='<div class="col-md-6 col-xs-12 col-sm-7">';
                                    	str+='<h4 class="panel-title text-capital">'+result[i].name+' level meetings</h4>';
                                    str+='</div>';
                                    str+='<div class="col-md-6 col-xs-12 col-sm-5">';
										str+='<div class="meetings'+i+'" style="height:60px;">ssss'+i+'</div>';
                                    str+='</div>';
                                str+='</div>';
                      	      str+='</div>';
                        	  str+='<div class="panel-body">';
                              	str+='<table class="table table-bordered tableMeetings">';
                                	str+='<thead>';
                                    	str+='<th>Planned</th>';
                                        str+='<th>Conducted</th>';
                                        str+='<th>Not Conducted</th>';
                                    str+='</thead>';
                                    str+='<tbody>';
                                    	str+='<tr>';
										var plannedCount=result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
                                        	str+='<td rowspan="2">'+plannedCount+'</td>';
                                            str+='<td>'+result[i].invitedCount+'</td>';
                                            str+='<td>'+result[i].nonInviteeCount+'</td>';
                                        str+='</tr>';
										
										//calculation
										
										var conductedPerc =0;
										var notConductedPerc=0;
										if(plannedCount !=null && plannedCount>0){
											if(result[i].invitedCount >0){
												conductedPerc = ((result[i].invitedCount/plannedCount)*100).toFixed(2);
											}
											if(result[i].nonInviteeCount >0){
												notConductedPerc = ((result[i].nonInviteeCount/plannedCount)*100).toFixed(2);
											}
										}
										
										
                                        str+='<tr>';
                                        	str+='<td>'+conductedPerc+'%</td>';
                                           str+=' <td>'+notConductedPerc+'%</td>';
                                        str+='</tr>';
										var notUpdatedPerc = 0;
										if(result[i].attendedCount >0){
												notUpdatedPerc = ((result[i].attendedCount/plannedCount)*100).toFixed(2);
											}
										str+='<tr><td class="text-muted text-center" colspan="3">Not Updated : <span class="text-warning">'+result[i].attendedCount+'   ('+notUpdatedPerc+'%)</span></td></tr>';
                                    str+='</tbody>';
                                str+='</table>';
								str+='<table class="table table-bordered table-condensed">';
									str+='<thead>';
										str+='<th colspan="4">MOM & ATR Summay</th>';
									str+='</thead>';
									str+='<tbody>';
										str+='<tr>';
											str+='<td class="tesxt-capital">MOM Files</td>';
											str+='<td class="tesxt-capital">MOM Text</td>';
											str+='<td class="tesxt-capital">ATR Files</td>';
											str+='<td class="tesxt-capital">ATR Text</td>';
										str+='</tr>';
										str+='<tr>';
										  if(result[i].momFilesCount != null && result[i].momFilesCount>0){
											  str+='<td  style="color:green;cursor:pointer"  class="allMomAtrCountsCls" attr_type="MINUTE" attr_action_id="'+2+'" attr_level_id="'+locationLevel+'"  attr_location_name ="'+result[i].name+'">'+result[i].momFilesCount+'</td>';
										  }else{
											 str+='<td>'+result[i].momFilesCount+'</td>';
										  }
										  if(result[i].momPointsCount != null && result[i].momPointsCount>0){
											str+='<td style="color:green;cursor:pointer"  class="allMomAtrCountsCls" attr_type="momText" attr_action_id="'+2+'" attr_level_id="'+locationLevel+'"  attr_location_name ="'+result[i].name+'">'+result[i].momPointsCount+'</td>';
											}else{
												str+='<td>'+result[i].momPointsCount+'</td>';
											}
										  if(result[i].atrFilesCount != null && result[i].atrFilesCount>0){
												str+='<td style="color:green;cursor:pointer"  class="allMomAtrCountsCls" attr_type="ATR" attr_action_id="'+2+'" attr_level_id="'+locationLevel+'"  attr_location_name ="'+result[i].name+'" >'+result[i].atrFilesCount+'</td>';
											}else{
											str+='<td>'+result[i].atrFilesCount+'</td>';
											}
										  if(result[i].atrTextCount != null && result[i].atrTextCount>0){
												str+='<td style="color:green;cursor:pointer"  class="allMomAtrCountsCls" attr_type="atrText" attr_action_id="'+2+'" attr_level_id="'+locationLevel+'"  attr_location_name ="'+result[i].name+'" >'+result[i].atrTextCount+'</td>';
											}else{
											    str+='<td>'+result[i].atrTextCount+'</td>';
											}
										str+='</tr>';
									str+='</tbody>';
								str+='</table>';
								//str+='<p class="text-muted text-center">demo : 9</p>';
                             str+=' </div>';
                      	 str+=' </div>';
                       str+=' </div>';
				}				
				
				
				
				$("#tPlannedId").html(totalPlanned);
				$("#tConductedId").html(totalConducted);
				$("#tNotConductedId").html(totalNotConducted);
				$("#totalMeetingsId").html(totalPlanned);
				
				var conductPerc =0 ;
				var notConductPerc = 0;
				var notUpdatePerc = 0;
				if(totalPlanned !=null && totalPlanned>0){
					if(totalConducted >0){
						conductPerc = ((totalConducted/totalPlanned)*100).toFixed(2);
					}
					if(totalNotConducted>0){
						 notConductPerc= ((totalNotConducted/totalPlanned)*100).toFixed(2);
					}
					if(totalNotUpdated>0){
						 notUpdatePerc= ((totalNotUpdated/totalPlanned)*100).toFixed(2);
					}
				}
				$("#tConductedPercId").html(''+conductPerc+' %');
				$("#tNotConductedPercId").html(''+notConductPerc+' %');
				$("#tNotUpadtedPercId").html(''+notUpdatePerc+' %');
				
				$("#levelBuildingId").html(str);
				
				//High Charts Building Start
				for(var i in result){
					
					var plannedCount=result[i].invitedCount+result[i].nonInviteeCount+result[i].attendedCount;
					
					$('.meetings'+i+'').highcharts({
						colors: ['#fec601', '#458ae9', '#f64911'],
						chart: {
							type: 'pie',
							options3d: {
								enabled: false,
								alpha: 15
							},
							backgroundColor: 'transparent'
						},
						tooltip: {
							useHTML: true,
							borderWidth: 0,
							style: {
								padding: 0
							}
						},
						legend: {
								enabled: false,
								align: 'center',
								x: 10,
								verticalAlign: 'left',
								y: 20,
								floating: false,
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
						plotOptions: {
							pie: {
								innerSize: 40,
								depth: 35,
								dataLabels: {
									enabled: false,
								}
							}, 
						},
						
						series: [{
							name: " ",
							data: [
								['Planned', plannedCount],
								['Conducted', result[i].invitedCount],
								['Not Conducted', result[i].nonInviteeCount]
							]
						}]
					});					
				}
				
				
					
					$('.totalMeetings').highcharts({
						colors: ['#fec601', '#458ae9', '#f64911'],
						chart: {
							type: 'pie',
							options3d: {
								enabled: false,
								alpha: 15
							},
							margin:'0',
							padding:'0',
							backgroundColor: 'transparent'
						},
						legend: {
								enabled: false,
								align: 'center',
								x: 10,
								verticalAlign: 'left',
								y: 20,
								floating: false,
								backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
								borderColor: '#CCC',
								borderWidth: 1,
								shadow: false
							},
						plotOptions: {
							pie: {
								innerSize: 40,
								depth: 35,
								dataLabels: {
									enabled: false,
								}
							}, 
						},
						tooltip: {
							useHTML: true,
							borderWidth: 0,
							style: {
								padding: 0
							}
						},
						series: [{
							name: " ",
							data: [
								['Planned', totalPlanned],
								['Conducted', totalConducted],
								['Not Conducted', totalNotConducted]
							]
						}]
					});
					
					//High Chart Building End
				
			}
		});
}
/* updateConductedDetails();
function updateConductedDetails(){
	var jsObj={
		meetingId : 117140,
		isConducted :'N',
		remarks:"NoReason",	
		conductedDate:""
	}
	$.ajax({
		type: "POST",
		url:"updateConductedDetailsAction.action",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	});	
}
 */
function updateConductedStatus(meetingId,status){
	
	var jsObj={
		meetingId : meetingId,
		isConducted :status
	}
	$.ajax({
		type: "POST",
		url:"updateConductedStatusAction.action",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		setTimeout(function(){
					//$("#viewMeetings").trigger( "click" );
				},500); 
		getLevelWiseMeetingDetails();
	});	
}
function updateConductedDate(dateValue){
	
	$("#errorId").html("");	
	var meetingId = $("#hiddenMeetingId").val();
	var attrId = $("#hiddenAttrId").val();
	
	/* if(dateValue ==null || dateValue =="undefined" || dateValue.length<=0){
		$("#errorId").css("color", "red");
		$("#errorId").html(" please select Date");
		return;
	} */
	
	var jsObj={
		meetingId : meetingId,
		conductedDate :dateValue
	}
	$.ajax({
		type: "POST",
		url:"updateConductedDateAction.action",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result == "success"){
			$("#errorId").css("color", "green");
			$("#errorId").html("Updated Successfully");
			
			if(dateValue !=null && dateValue.length>0){
				$("#"+attrId).html(dateValue.split("/")[2]+"-"+dateValue.split("/")[0]+"-"+ dateValue.split("/")[1]);
				$("#"+attrId).attr("attr_conductedDate",dateValue.split("/")[2]+"-"+dateValue.split("/")[0]+"-"+ dateValue.split("/")[1]);
			}else{
				$("#"+attrId).html("Enter Date");
				$("#"+attrId).attr("attr_conductedDate","");
			}
			
			 setTimeout(function(){ 
					$("#editConductedBtnModal").modal('hide');
			 }, 1000);
			 setTimeout(function(){
					$("#viewMeetings").trigger( "click" );
				},500); 
		}else{
			$("#errorId").css("color", "red");
			$("#errorId").html("some problem occured while updating.");
		}
	});	
}
function updateConductedReason(remarks){
	var meetingId = $("#hiddenMeetingId").val();
	var attrId = $("#hiddenAttrId").val();
	
	/* if(remarks ==null || remarks =="undefined" || remarks.length<=0){
		$("#errorId").css("color", "red");
		$("#errorId").html(" please Enter Remarks");
		return;
	} */
	
	var jsObj={
		meetingId : meetingId,
		remarks :remarks
	}
	$.ajax({
		type: "POST",
		url:"updateConductedReasonAction.action",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result == "success"){
			$("#errorId").css("color", "green");
			$("#errorId").html("Updated Successfully");
			
			
			if(remarks !=null && remarks.length>0){
				$("#"+attrId).html(remarks);
				$("#"+attrId).attr("attr_remarks",remarks);
			}else{
				$("#"+attrId).html("Enter Remarks");
				$("#"+attrId).attr("attr_remarks","");
			}
			
			 setTimeout(function(){ 
					$("#editConductedBtnModal").modal('hide');
			 }, 1000);
		}else{
			$("#errorId").css("color", "red");
			$("#errorId").html("some problem occured while updating.");
		}
	});	
}
/* $(document).on("click",".editConductedBtn",function(){
	
	$("#isConductedUpdateId").show();
	$("#dateUpdateId").hide();
	$("#remarksUpdateId").hide();
	
	var isConducted = $(this).attr("attr_isConducted");
	
	$("#isConductedUpdateId").val(isConducted);
		
	$("#editConductedBtnModal").modal('show');
}); */
$(document).on("click",".editConductedDateBtn",function(){
	
	$("#isConductedUpdateId").hide();
	$("#dateUpdateId").show();
	$("#remarksUpdateId").hide();
	
	var conductedDate = $(this).attr("attr_conductedDate");
	var meetingId = $(this).attr("attr_meetingId");
	var id = $(this).attr("id");
	
	$("#hiddenMeetingId").val(meetingId);
	$("#hiddenAttrId").val(id);
	
	var cndDate='';
	if(conductedDate !=null && conductedDate.length>0){		
		cndDate = conductedDate.split("-")[1]+"/"+conductedDate.split("-")[2]+"/"+conductedDate.split("-")[0];	
		$("#dateValueId").val(cndDate);					
	}else{
		$("#dateValueId").val("");	
	}
	
	$("#errorId").html("<b>Select Date</b>");
	//$('errorId').removeAttr('style');
	$("#editConductedBtnModal").modal('show');
});
$(document).on("click",".editRemarksBtn",function(){
	$("#isConductedUpdateId").hide();
	$("#dateUpdateId").hide();
	$("#remarksUpdateId").show();
	
	var remarks = $(this).attr("attr_remarks");
	var meetingId = $(this).attr("attr_meetingId");
	var id = $(this).attr("id");
		
	$("#hiddenMeetingId").val(meetingId);
	$("#hiddenAttrId").val(id);
	
	if(remarks !=null && remarks.length>0){
		$("#remarksUpdateId").val(remarks);			
	}else{
		$("#remarksUpdateId").val("");
	}
	
	$("#errorId").html('<b>Enter Remarks</b>');
	//$('errorId').removeAttr('style');
	$("#editConductedBtnModal").modal('show');
});

$(document).on("change",".updateConductedCls",function(){
	
	var idValue = $(this).attr("id");
	var status = $("#"+idValue).val();
	var meetingId = $(this).attr("attr_meetingId");
	
	updateConductedStatus(meetingId,status);
});

$(document).on("click","#updateDetailsBtnId",function(){
	if($('#dateUpdateId').is(':visible')){
		var  dateValue = $("#dateValueId").val();		
		updateConductedDate(dateValue);
	}else if($('#remarksUpdateId').is(':visible')){
		var remarksVal = $("#remarksUpdateId").val();
		updateConductedReason(remarksVal);
	}	
});
 function getMandalsForDistrictId(){
	 $("#manTowDivId  option").remove();
	var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
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
				$("#manTowDivId").append("<option value='0'>ALL</option>");
			   for(var i in result){
				   $("#manTowDivId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
  }
function getVillagesForDistrictId(){
	$("#villWardId  option").remove();
	 var districtId = $("#districtId").val();
			var districtId = [];
			if(districtId==0){
				if(distArr.length>0){
					districtId = distArr;
				}else{
					districtId.push(0);
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
				$("#villWardId").append("<option value='0'>ALL</option>");
				for(var i in result){
				   $("#villWardId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			   }
		   }
   });	
 }
 var levelId = '';
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
			levelId = $(this).val();	
			}
		});
 function handleFunctions(){
	getLevelWiseMeetingDetails(); 
		$("#loadingImgForLevelId").show();
		$(".stateCls").each(function(){
			if($(this).prop('checked')==true){
				 if($(this).val()==1){
					$("#statesDivId").html("");
					//$("#statesDivId").append('<option value=1> All </option>');
					$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}else if($(this).val()==36){
					$("#statesDivId").html("");
					//$("#statesDivId").append('<option value=36> All </option>');
					$("#statesDivId").append('<option value=36> Telangana </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}else if($(this).val()==0){
					$("#statesDivId").html("");
					$("#statesDivId").append('<option value=0> All </option>');
					$("#statesDivId").append('<option value=1> Andhra Pradesh </option>');
					$("#statesDivId").append('<option value=36> Telangana </option>');
					getDistrictsForStates($( "#statesDivId" ).val());
					meetingLevelWiseHideShow();
				}
			}
		});
 }
	   var globalImgsMdlCnt = 0;
	  var jsonGlob1 = "";
	$(document).on("click",".allMomAtrCountsCls ",function(){
		$("#modelBody").html("");
		var id =$(this).attr("attr_action_id");
		var locationName;
		var allImages ="";
				globalImgsMdlCnt = 1;
		if(id == 1){
			var modalType = "first";
			if($("#meetingLocationLevel").val() != null && $("#meetingLocationLevel").val() != 0)
			 locationName =$("#meetingLocationLevel").val();
			$("#modelTitle").html(locationName+" "+"Monthly Co-Ordination Meeting");
		var type = $(this).attr("attr_type");
		jsonGlob["type"]=$(this).attr("attr_type");
		$.ajax({
			type:"POST",
			url :"getAllMomAtrClickDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsonGlob)}
		}).done(function(result){
			if(result != null && result.length > 0){
				var str='';
			if(type == "MINUTE" || type == "ATR"){
			        buildAllMonAtrClickDetails(result,type,"","",modalType,"");
				}else if(type == "momText" || type == "atrText"){
					for(var i in result){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result[i].name+'</div>';
					}
					$("#modelBody").html(str);
				    $("#myModal").modal("show");
				}
			}
		});
	}else if(id == 2){
		var modalType = "second";
		 locationName =$(this).attr("attr_location_name");
			$("#modelTitle").html(locationName+" "+" Co-Ordination Meeting");
		var meetingType = 0;
		var locationLevel = $(this).attr("attr_level_id");
		var districtId=[0];
		var constituencyId=[0];
		var mandalTownDivisonId=[0];
		var villageWardId =[0];
		var dates = $("#datePickerBlockId").val();
		var startDate = "";
		var endDate ="";
		if(dates !=null && dates.length>0){
			startDate = dates.split("-")[0];
			endDate = dates.split("-")[1];
		}
		var stateId =[1,36];
		var type = $(this).attr("attr_type");
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						sateId:stateId,
						districtId:districtId,
						constituencyId:constituencyId,
						mandalTownDivisonId:mandalTownDivisonId,
						villageWardId:villageWardId,
						startDate:startDate,
						endDate:endDate,
						type :type
					}
		jsonGlob1 = jsObj;
		$.ajax({
			type:"POST",
			url :"getAllMomAtrClickDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsonGlob1)}
		}).done(function(result){
			if(result != null && result.length > 0){
				var str='';
			if(type == "MINUTE" || type == "ATR"){
			        buildAllMonAtrClickDetails(result,type,"","",modalType,locationLevel);
				}else if(type == "momText" || type == "atrText"){
					for(var i in result){
						str+='<div style="border-bottom:1px dashed; padding:5px;">'+result[i].name+'</div>';
					}
					$("#modelBody").html(str);
				    $("#myModal").modal("show");
				}
			}
		});
	}
	});
	
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
					
				<c:if test="${sessionScope.USER.isAdmin == 'true'}">
				setTimeout(function(){getMandalVillageDetails(4);}, 2000);
				</c:if>
				
				<c:if test="${sessionScope.USER.isAdmin == 'false'}">
				setTimeout(function(){getMandalsForDistrictId();}, 2000);
				</c:if>
				
				$("#stateShowId").show();
				$("#DistrictShowId").show();
				$("#ConstShowId").show();
				$("#ManTwnDivShowId").show();
				$("#VillWardShowId").hide();
				
			}else if($("#meetingLocationLevel").val()== 7 || $("#meetingLocationLevel").val()== 8){
				
				getConstituenciesForDistricts("");
				/* start */
				
				<c:if test="${sessionScope.USER.isAdmin == 'true'}">
				getMandalVillageDetails(4);
				</c:if>
				/* end */
				
				<c:if test="${sessionScope.USER.isAdmin == 'false'}">
				getMandalsForDistrictId();
				getVillagesForDistrictId();
				</c:if>
				
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
</script>
<script>
$(document).on("click",".allMomAtrCountsTypeCls ",function(){
	$("#modelBody1").html("");
	$("#myModal1").modal("show");
	var meetingName =$(this).attr("attr_name");
	$("#modelTitle1").html(meetingName+" "+"Images");
	var modalType= $(this).attr("attr_modal_type");
	var allImages ="viewAll";
	var meetingId =$(this).attr("attr_id");
	if(modalType == "first"){
		var type = $(this).attr("attr_type");
		var meetingId =$(this).attr("attr_id");
		jsonGlob["type"]=$(this).attr("attr_type");
		$.ajax({
			type:"POST",
			url :"getAllMomAtrClickDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsonGlob)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAllMonAtrClickDetails(result,type,allImages,meetingId,modalType,"");
			}
		});
	}else if(modalType == "second"){
		var meetingType = 0;
		var locationLevel=$(this).attr("attr_location_level");
		var districtId=[0];
		var constituencyId=[0];
		var mandalTownDivisonId=[0];
		var villageWardId =[0];
		var dates = $("#datePickerBlockId").val();
		var startDate = "";
		var endDate ="";
		if(dates !=null && dates.length>0){
			startDate = dates.split("-")[0];
			endDate = dates.split("-")[1];
		}
		var stateId =[1,36];
		var type = $(this).attr("attr_type");
		var jsObj =	{
						meetingType:meetingType,
						locationLevel:locationLevel,
						sateId:stateId,
						districtId:districtId,
						constituencyId:constituencyId,
						mandalTownDivisonId:mandalTownDivisonId,
						villageWardId:villageWardId,
						startDate:startDate,
						endDate:endDate,
						type :type
					}
		jsonGlob1 = jsObj;
		$.ajax({
			type:"POST",
			url :"getAllMomAtrClickDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsonGlob1)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAllMonAtrClickDetails(result,type,allImages,meetingId,modalType,locationLevel);
			}
		});
	}
	});
function buildAllMonAtrClickDetails(result,type,allImages,meetingId,modalType,locationLevel){
	var str ='';
	var extension;
	str +='<div class="col-sm-12">';

		if(type == "MINUTE" || type == "ATR"){
		if(allImages != "viewAll"){
			str+='<div class="table-responsive">';
	        str +='<table class="table table-bordered" id="tableId">'; 
	        str +='<thead>';
	        str +='<th style="text-transform: uppercase;"> partyMeeting Name</th>';
	        str +='<th style="text-transform: uppercase;"> partyMeeting Date</th>';
	        str +='<th style="text-transform: uppercase;"> Images</th>';
	        str +='</thead>';
	        str +='<tbody>';
			for(var i in result){		
			str+='<tr>';
			if(result[i].partyMetingName != null){
			str+='<td style="text-transform: uppercase;">'+result[i].partyMetingName+'</td>';
		    }else{
			str+='<td>-</td>';
		   }
		if(result[i].date != null){
			str+='<td style="text-transform: uppercase;">'+result[i].date+'</td>';
		      }else{
			 str+='<td>-</td>';
		  }
			str+='<td>';
			if(result[i].imageList.length>0){
				str+='<ul class="list-inline ">';
				 extension = getExtension(''+result[i].imageList[0]+'');
				if(result[i].imageList[0] != null){
					if(extension == 'pdf'){
						str+='<li>';
							str+='<object data="https://mytdp.com/DocFiles/'+result[i].imageList[0]+'" type="application/pdf" width="80px" height="80px">';
							  str+='alt : <a href="https://mytdp.com/DocFiles/'+result[i].imageList[0]+'" style="width: 80px;height: 80px;">pdf</a>';
							str+='</object>';
						str+='</li>';
					}else{
						str+='<li>';
							str+='<img src="https://mytdp.com/DocFiles/'+result[i].imageList[0]+'" style="width:80px;height:80px;background-color: #672f0a;" />';
						str+='</li>';
					   
					}
				}
		  /*  if(result[i].imageList[1]!= null){
				 extension = getExtension(''+result[i].imageList[1]+'');
				 if(extension == 'pdf'){
					 str+='<li>';
						str+='<object data="https://mytdp.com/DocFiles/'+result[i].imageList[1]+'" type="application/pdf"  width="80px" height="80px">';
						  str+='alt : <a href="https://mytdp.com/DocFiles/'+result[i].imageList[1]+'" style="width: 80px;height: 80px;">pdf</a>';
						str+='</object>';
					str+='</li>';
				 }else{
					 str+='<li>';
						str+='<img src="https://mytdp.com/DocFiles/'+result[i].imageList[1]+'"  style="width:80px;height:80px;background-color: #672f0a;"/>';
					str+='</li>';

				 }
			}*/
				str+='<li>';
					str+='<span class="allMomAtrCountsTypeCls" style="cursor: pointer; background-color: rgb(103, 47, 10); display: inline-block;padding:10px;color: white;font-weight: bold" attr_type ="'+type+'" attr_id="'+result[i].id+'" attr_name ="'+result[i].partyMetingName+'" attr_location_level="'+locationLevel+'" attr_modal_type="'+modalType+'">View All</span>';
				str+='</li>';
		     str+='</ul>';
			}
			 str+='</td>';
		     str+='</tr>';
			}
			 str +='</tbody>';
	         str +='</table>';
	        str +='</div>';
			}else if(allImages == "viewAll" ){
				str+='<ul class="list-inline modalImagesUl">';
			   for(var i in result){
               if(result[i].id == meetingId){
					var resultLength=result[i].imageList.length;
					if(result[i].imageList.length>0){
						for(var k=0;k<result[i].imageList.length;k++){
							extension = getExtension(''+result[i].imageList[k]+'');
					if(extension == 'pdf'){
						 str+='<li>';
							str+='<object data="https://mytdp.com/DocFiles/'+result[i].imageList[k]+'" type="application/pdf" width="500px" height="500px">';
							  str+='alt : <a href="https://mytdp.com/DocFiles/'+result[i].imageList[k]+'">pdf</a>';
							str+='</object>';
						str+='</li>';
					}else{
						 str+='<li>';
							str+='<img src="https://mytdp.com/DocFiles/'+result[i].imageList[k]+'"  style="width:100%;height:500px;background-color: #672f0a;"/>';
						str+='</li>';
					}
				}
				 }
				 
				}
				}
				str+='</ul>';
			}
		}
	str +='</div>';
if(allImages == "viewAll"){
	$("#modelBody1").html(str);
}else{
	$("#modelBody").html(str);
}
$("#tableId").dataTable();
if(resultLength>2)
			{
			$(".modalImagesUl").slick({
			 slide: 'li',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 variableWidth: true
		});
			}
	$("#myModal").modal("show");      
}
function getExtension(path) {
    var basename = path.split(/[\\/]/).pop(),  // extract file name from full path ...
                                               // (supports `\\` and `/` separators)
        pos = basename.lastIndexOf(".");       // get last position of `.`

    if (basename === "" || pos < 1)            // if file name is empty or ...
        return "";                             //  `.` not found (-1) or comes first (0)

    return basename.slice(pos + 1);            // extract extension ignoring `.`
}
$(document).on('click','.imageCloseBtnCls',function(){ 
	if(globalImgsMdlCnt > 0){
		setTimeout(function(){
			$('body').addClass("modal-open");
		}, 500);
	}
});	
function buildSummaryForAMeeting(result,meetingId,type){
	if(result!=null && result.docsList!=null && result.docsList.length>0){
		var length = result.docsList;
		var str='';
	str+='<div class="table-responsive">';
	        str +='<table class="table table-bordered" id="summaryTableId">'; 
	        str +='<thead>';
	        str +='<th style="text-transform: uppercase;"> partyMeeting Name</th>';
	        str +='<th style="text-transform: uppercase;"> partyMeeting Date</th>';
	        str +='<th style="text-transform: uppercase;"> Images</th>';
	        str +='</thead>';
	        str +='<tbody>';
			str+='<tr>';
		if(result.docsList[0].subName != null){
			str+='<td style="text-transform: uppercase;">'+result.docsList[0].subName+'</td>';
		    }else{
			str+='<td>-</td>';
		   }
		  if(result.docsList[0].startDateStr!= null){
			str+='<td style="text-transform: uppercase;">'+result.docsList[0].startDateStr+'</td>';
		      }else{
			 str+='<td>-</td>';
		   }	
			str+='<td>';
			if(result.docsList.length>0){
				str+='<ul class="list-inline ">';
				 extension = getExtension(''+result.docsList[0].path+'');
				if(result.docsList[0].path != null){
					if(extension == 'pdf'){
						str+='<li>';
							str+='<object data="https://mytdp.com/DocFiles/'+result.docsList[0].path+'" type="application/pdf" width="80px" height="80px">';
							  str+='alt : <a href="https://mytdp.com/DocFiles/'+result.docsList[0].path+'" style="width: 80px;height: 80px;">pdf</a>';
							str+='</object>';
						str+='</li>';
					}else{
						str+='<li>';
							str+='<img src="https://mytdp.com/DocFiles/'+result.docsList[0].path+'" style="width:80px;height:80px;background-color: #672f0a;" />';
						str+='</li>';
					   
					}
				}
				str+='<li>';
					str+='<span class="locationWiseCountCls" style="cursor: pointer; background-color: rgb(103, 47, 10); display: inline-block;padding:10px;color: white;font-weight: bold"  attr_meeting_Id="'+meetingId+'" attr_type ="'+type+'" >View All</span>';
				str+='</li>';
		     str+='</ul>';
			}
			 str+='</td>';
		     str+='</tr>';
			 str +='</tbody>';
	         str +='</table>';
	        str +='</div>';
			$("#modelBody").html(str);
			$("#summaryTableId").dataTable();
}
      $("#modelTitle").html(result.docsList[0].subName);
				$("#myModal").modal('toggle');
}
$(document).on('click','.locationWiseCountCls', function() {
	  var fromType = $(this).attr("attr_type");
	  var meetingId = $(this).attr("attr_meeting_Id");
	  var jsObj =	{
			meetingId : meetingId,
			type:fromType
			}
			
		$.ajax({
			type: "POST",
			url:"getSummaryForAMeetingAction.action",
			data:{task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildLocationWiseCount(result);
		});
	 
  });
function buildLocationWiseCount(result){
	if(result!=null && result.docsList!=null && result.docsList.length>0){
					var str='';
					str+='<ul class="list-inline modalImagesUl">';
					for(var i in result.docsList){
						 extension = getExtension(''+result.docsList[i].path+'');
						if(extension == 'pdf'){
						str+='<li>';
							str+='<object data="https://mytdp.com/DocFiles/'+result.docsList[i].path+'" type="application/pdf" width="500px" height="500px">';
							  str+='alt : <a href="https://mytdp.com/DocFiles/'+result.docsList[i].path+'">pdf</a>';
							str+='</object>';
						str+='</li>';
						}else{
							str+='<li>';
							str+='<img src="https://mytdp.com/DocFiles/'+result.docsList[i].path+'"  style="width:100%;height:500px;background-color: #672f0a;"/>';
						    str+='</li>';
						}
					
					}
					str+='</ul>';
					$("#modelBody1").html(str);
				}
		if(result.docsList.length>2)
			{
			$(".modalImagesUl").slick({
			 slide: 'li',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 variableWidth: true
		});
			}
				$("#modelTitle1").html(result.docsList[0].subName);
				$("#myModal1").modal('toggle');
}
</script>
</body>
</html>