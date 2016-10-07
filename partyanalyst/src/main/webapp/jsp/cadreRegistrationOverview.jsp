<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Overview</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
<link href="dist/scroll/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<div class="container">
<div class="row">
<div class="col-md-12 col-xs-12 col-sm-12 ">
<h4 class="text-capital" style ="text-align:center;">KUPPAM CONSTITUENCY DETAILED REPORT </h4>
<div class="col-md-3 col-xs-12 col-sm-12 m_top20">
</div>
<div class="col-md-3 col-xs-12 col-sm-12 m_top20">
	<button class="btn btn-success btn-block text-capital " id="cadreModalDivid">kuppam constituency<br/> detailed report</button>  
</div>
<div class="col-md-3 col-xs-12 col-sm-12 m_top20">
	<button class="btn btn-success btn-block text-capital " id="cadreModalTabDivid">Kuppam Constitency<br/> Tab User Detailed Report</button>
</div>
<div class="col-md-3 col-xs-12 col-sm-12 m_top20">  
</div>
</div>
</div>
</div>		


<!-- Modal -->
<div class="modal fade" id="cadreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog modal-lg" role="document" style="width:85%">
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">KUPPAM CONSTITUENCY DETAILED REPORT</h4>
      </div>
      <div class="modal-body">
		<div class="row tabModal" style="display:none;">
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="row m_top10">
					<div class="col-md-3 col-xs-12 col-sm-3">
						<select class="form-control" id="constituencySeletBoxId">
						  <option value="${constId}">${constName}</option>
						  <select>
						  <span id="constituencyErrorId" style="color:red"></span>    
					</div>
					<div class="col-md-3 col-xs-12 col-sm-3">
						<span class="input-group pull-right">
							<input type="text" id="dateRangeIdForCadre"	 class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</span>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-3" style="margin-top: -8px;">
						<button class="btn btn-success pull-right m_top10 tabUserWiseDetails" type="submit" style="margin-right: 16px;">Submit</button>
					</div>
				</div>
				<div class="row showTabUserWiseDetails" style="display:none">
				  <div class="col-md-12 col-xs-12 col-sm-12 m_top20 mtop-20">
				     <div id="notReceiveRegistrationFieldStaffDivId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div id="tabUserWiseReportDiv"></div>
					</div>
				</div>
			 </div>
		</div>
        <div class="row webModal">
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio1" value="overall" style="margin-top: 0px;" checked><h5>Over All</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio2" value="today" style="margin-top: 0px;"><h5>Today</h5>
					</label>  
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">     
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio1" value="booth" style="margin-top: 0px;" checked><h5>Booth Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio2" value="panchayat" style="margin-top: 0px;"><h5>Panchayat Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio3" value="mandal" style="margin-top: 0px;"><h5>Mandal Wise</h5>
					</label>
				</div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<div id="kupamRegDtlsId"></div>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- end-->  
<!-- Model for Cadre  Start-->
		<div class="modal" tabindex="-1" role="dialog" id="noOfSamplesModal">
		  <div class="modal-dialog modal-lg" style="width:65%">
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="tabUserInfoDetailsHeadingId"></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
				  <div class="col-md-12 col-xs-12 col-sm-12">
					<div id="noOfSamplesDetailsDiv" ></div>	
				  </div>
				   <div class="col-md-12 col-xs-12 col-sm-12">
					<div id="tabUserInfoDivId" ></div>	
				  </div>
				</div>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default closeModal" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<!--end-->    
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Rating/bootstrap-rating.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/debates.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/cadreRegistration.js" type="text/javascript"></script>  

<script src="js/cadreRegistration2016/cadreRegistration.js" type="text/javascript"></script>
<script type="text/javascript">
var globalConstId = "${constId}";  
var globalConstName = "${constName}";

</script>
</body>
</html>