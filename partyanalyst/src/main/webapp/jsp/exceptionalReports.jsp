<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Constituency Page</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">  
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/print.css" rel="stylesheet" type="text/css"/>
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style>
.eventsheader
{
	display:none;
}
header
{
	background-color:#FFCC00;
	border-bottom:8px solid red;
}
.background-head {
    background-color: #fed501;
    height: 270px;
    content: ' 
    position: absolute;
    width: 100%;
    z-index: -1;
}
.black-border
{
	background-color:#333;
	margin-bottom:20px;
}
</style>
</head>
<body>
<div class="background-head headerDisPlayNone" style="height:auto;"></div>
<header class="headerDisPlayNone">
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<img src="coreApi/img/logo.png" class="img-responsive"/>
			</div>
			<div class="col-sm-6">
				<img src="coreApi/img/right_side_head.png" class="img-responsive pull-right"/>
			</div>
		</div>
	</div>
</header>
<div class="black-border headerDisPlayNone">
	<div class="container">
		<h3 class="text-center text-capital" style="color:#fff;padding:10px;">Exceptional Reports</h3>
	</div>
</div>
<div id="printableArea">
<section id="printcontent">
	<div class="container">
		<div class="row">
			<div class="col-sm-12" >
				<!--<button class="btn btn-md btn-success printViewCls pull-right headerDisPlayNone" attr_divId="printableArea">Print</button>-->
			</div>
		<div class="row">
				<div class="col-sm-12" id="mainDivHeaderId">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Meetings - <small class="text-muted"><span id="exceptionReportMeetingDateId">  </span></small>	
									</h4>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-12 meetingblockOpen headerDisPlayNone">
									<span class="meetingsExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
									
									 <span class="MeetingsExSettingsIcon pull-right">
									   <i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
									 </span>
									 <span class="input-group pull-right dateRangePickerCls" style="width:200px;">
										<input type="text" id="meetingExDateRangePickerId" style="width:200px" class="form-control" />
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-calendar"></i>
											</span>
									</span>
									 <div class="debatesSettingsBody" style="display:none;top:15px;right: 39px;">
										<div class="row">
											<div class="col-sm-12">
											 <i class="glyphicon glyphicon-remove meetingExSettingsCloseBody" style="cursor: pointer; position: absolute; right: 6px; z-index: 99; top: -9px;"></i>
											 </div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<ul style="padding:0px;" class="list-inline line_heightCls">
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="constituencyId" value="meetingBlocksConstituencyDivId" class="getLevelValCls" attr_party_type_id="3" checked />Constituency Level Meeting</h5>
														</label>
													</li>
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="mandalId"  value="meetingBlocksMandalDivId" class="getLevelValCls" attr_party_type_id="15" checked />Mandal Level Meeting</h5>
														</label>
													</li>
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="villageId"  value="meetingBlocksVillageDivId" class="getLevelValCls" attr_party_type_id="14" checked />Village Level Meeting</h5>
														</label>
													</li>
												</ul>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 m_top10">
												<button type="button" class="btn btn-primary btn-sm levelWiseMeetingCls">Submit</button>
											</div>
										</div>
									 </div>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="meetingBlocksConstituencyDivId"></div>
								<div id="meetingBlocksMandalDivId"></div>
								<div id="meetingBlocksVillageDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-12" id="mainDivHeaderId">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Committees - <small class="text-muted">Over All</small>
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 committeeblockOpen headerDisPlayNone">
									<span class="committeesExRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
									 <span class="committeesExSettingsIcon pull-right">
									   <i class="fa fa-gears" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings"></i>
									 </span>
									 <div class="debatesSettingsBody" style="display:none;top:15px;right: 39px;">
										<div class="row">
											<div class="col-sm-12">
											 <i class="glyphicon glyphicon-remove committeesExSettingsCloseBody" style="cursor: pointer; position: absolute; right: 6px; z-index: 99; top: -9px;"></i>
											 </div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<ul style="padding:0px;" class="list-inline line_heightCls">
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="mandalTownDivision" value="mandalTownDivisionCommDivId" class="getCommLevelValCls" checked />Mandal Level Committee</h5>
														</label>
													</li>
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="villageWard"  value="villageWardCommDivId" class="getCommLevelValCls"  checked />Village Level Committee</h5>
														</label>
													</li>
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="boothComm"  value="boothCommDivId" class="getCommLevelValCls" checked />Booth Level Committee</h5>
														</label>
													</li>
													<li>
														<label class="checkbox-inline">
															<h5><input type="checkbox" id="affmandal"  value="affmandalTownDivision" class="getCommLevelValCls" checked />Affiliated Level Committee</h5>
														</label>
													</li>
													
												</ul>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 m_top10">
												<button type="button" class="btn btn-primary btn-sm commlevelWiseCls">Submit</button>
											</div>
										</div>
									 </div>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="mandalTownDivisionCommDivId"></div>
								<div id="villageWardCommDivId"></div>
								<div id="boothCommDivId"></div>
								<div id="affmandalTownDivision"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/training.png" class="iconClass" style="background-color:none;"/>
											Training Camp - <small class="text-muted">Over All</small>
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 headerDisPlayNone">
									<span class="trainingCampExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="overAllAndTop5ParliamentDivId"></div>
								<div id="top5ConstituencyWithPoorPerDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Tours 
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 headerDisPlayNone">
									<span class="tourExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
								
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body" style="padding-left: 0px;padding-right: 0px;">
							
								<div id="overAllTourDetailsDivId"></div>
								
								<div class="row">
									<div class="pull-right m_top10">
									    <div class ="col-md-6 m_top10">
											<span class="input-group pull-right dateRangePickerCls" style="width:200px;">
											<input type="text" id="tourNewExDateRangePickerId" style="width:200px" class="form-control" />
											<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
											</span>
											</span>
										</div>
										<div class="col-sm-4">
											<label>No Of Months</label>
											<select class="form-control" id="noofMonthsId">
											</select>
										</div>
									</div>
								</div>
								<div id="toursSubmittedNoOfMonthsId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Dalitha Tejam - <small class="text-muted">Over All</small>			
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 headerDisPlayNone">
									<span class="dalithaExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="overAllAndTop5PoorParliamentDivId"></div>
								<div id="top10ConstituencyInchAndWhatsImageDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Alert - <small class="text-muted">Over All</small>			
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 headerDisPlayNone">
									<span class="alertExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="overAllAlertsDivId"></div>
								<div id="assignedParliamentWiseAlertsDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-9">
									<h4 class="panel-title text-capital">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Kaizala - <small class="text-muted">Over All</small> 		
									</h4>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-12 headerDisPlayNone">
									<span class="kaizalaExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
								</div>  
							</div>
						</div>
						<div class="container-fluid">
							<div class="panel-body">
								<div id="overAllKaizalaDivId"></div>
								<div id="performanceKaizalaParliamentWiseDetailsDivId"></div>
								<div id="performanceKaizalaConstituencyWiseDetailsDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
		
</section>
</div>
<script src="coreApi/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="coreApi/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="exceptionalReport/partyMeetingExceptionalReport.js" type="text/javascript"></script>
<script src="exceptionalReport/tourExceptionalReport.js" type="text/javascript"></script>
<script src="exceptionalReport/activityExceptionalReport.js" type="text/javascript"></script>
<script src="exceptionalReport/trainingCampExceptionalReport.js" type="text/javascript"></script>  
<script src="exceptionalReport/alertExceptionalReport.js" type="text/javascript"></script>         
<script src="exceptionalReport/committeeExceptionalReport.js" type="text/javascript"></script>
<script src="exceptionalReport/kaizalaExceptionalReport.js" type="text/javascript"></script> 
 <script type="text/javascript">
 $(document).on("click",".printViewCls",function(){
	printDiv();
});
function printDiv() {
	 var printContents = document.getElementById('printableArea').innerHTML;
	 var originalContents = document.getElementById("printcontent").innerHTML;
	 document.title = "";
     document.getElementById("printcontent").innerHTML = printContents;
	 window.print();
     document.getElementById("printcontent").innerHTML = originalContents;
	 
}
 </script>
</body>
</html>