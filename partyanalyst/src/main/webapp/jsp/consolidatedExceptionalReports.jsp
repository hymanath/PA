<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Exceptional Reports</title>
<link href="coreApi/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">  
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="coreApi/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
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

.DTFC_LeftBodyWrapper
{
	top:-13px !important;
}

.DTFC_LeftBodyLiner{
	overflow-y:hidden;
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
		<h3 class="text-center text-capital" style="color:#fff;padding:10px;">Consolidated view of Exceptional Reports</h3>
	</div>
</div>
<div id="printableArea">
<section id="printcontent">
	<div class="container-fluid">
		<div class="">
		<div class="row">
			<p class="text-center waitingMsgCls">Please Wait Page is Loading...</p>
		</div>	
		<div class="row m_top10">
				<div class="col-sm-12" id="">
					<div class="panel panel-default panelNewCustom">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-6">
									<h4 class="panel-title text-capital font_size20">
										<img src="newCoreDashBoard/img/meetings.png" class="iconClass" style="background-color:none;"/>
											Consolidated view of Exceptional Reports<small class="text-muted"><span id="exceptionReportMeetingDateId">  </span></small>	
									</h4>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-12 meetingblockOpen headerDisPlayNone">
									 <!--<span class="MeetingsExSettingsIcon pull-right">
									   <i class="fa fa-print printViewCls" data-toggle="tooltip" data-placement="top" title="Print" data-original-title="Print" attr_divId="printableArea"></i>
									 </span>-->
									<span class="meetingsExRRefresh pull-right">
										<i class="glyphicon glyphicon-refresh"></i>
									</span>
									 <span class="input-group pull-right dateRangePickerCls" style="width:200px;">
										<input type="text" id="meetingExDateRangePickerId" style="width:200px" class="form-control" />
											<span class="input-group-addon">
												<i class="glyphicon glyphicon-calendar"></i>
											</span>
									</span>
									
								</div>  
							</div>
						</div>
						<div class="panel-body">
							<div id="overAllMeetingLevelsDivId"></div>
							<div id="overAllMeetingLevelsDivId1"></div>
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
<script src="coreApi/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="exceptionalReport/consolidatedExceptionalReports.js" type="text/javascript"></script>

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
	 $("#meetingExDateRangePickerId").daterangepicker({
		opens: 'left',
		 startDate: customStartToursDateM,
		 endDate:customEndToursDateM,  
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: { ////moment().endOf('Year')
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay1()), 'days'), moment().subtract(parseInt(getDay1()), 'days')],
		   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay1()), 'days'), moment().subtract(parseInt(getDay1()), 'days')],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
		}
	});
	
	 
}
 </script>
</body>
</html>