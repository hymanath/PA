<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Core Dashboard</title>
<link href="js/dataReconsolidationOverview/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="js/dataReconsolidationOverview/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="js/dataReconsolidationOverview/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="js/dataReconsolidationOverview/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>

</head>
<body>
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
					
						<select  class="form-control" id="stateOverViewId" >
							<option value="0">Select State</option>
							<option value="1">ANDHRA PRADESH</option>
							<option value="36">TELANGANA</option>
						</select>
					</div>
						
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
						<select  class="form-control" id = "districtOverViewId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
						<select  class="form-control" id ="constituencyOverViewId" >
							<option value="0">Select Constituency</option>
						</select>
				   </div>
						
					 <div class="col-md-3 col-xs-12 col-sm-6 pull-right">
							<div class="input-group inputGCustom">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								 <input type="text" class="form-control datePicker"/>
							</div>
					</div>
					</div>
					<input type="button" class ="btn btn-success pull-right" value ="submit" id="submitId" style="margin-bottom: 0px; margin-top: 5px;">
					<div id = "errorDivId" style = "color:red"></div>
				</div>
				
			</div>
		
				<div class="panel panel-default m_top10" id="dataReconsalationOverviewId">
					<!--<h4 class=" headingStyle text-capital"><b>DATA RECONSALATION OVERVIEW</b></h4>
					<div class="panel-body" style="padding: 25px;">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 ">
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Total Smart Devices</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Total Registration</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-2 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Data Synced Records</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6 border_right">
									<p class="text_bold">Data Synced Pending Records</p>
									<p class="text_bold">2000</p>
								</div>
								<div class="col-md-3 col-xs-12 col-sm-6">
									<p class="text_bold">Data Synced Pending Devices</p>
									<p class="text_bold">2000</p>
								</div>
							</div>
						</div>
					</div>-->
				</div>
				<div class="panel panel-default" id="userWiseTotalViewId">
					
			   </div>
			 </div>  

	

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document" style="width: 85%;">
		<div class="modal-content">
		 <input type="button" class="btn btn-success pull-right" value ="ExportToExcel" style="margin-left: 0px; width: 152px;" id="exportToExcelId" onClick ="generateExcelReport()" ></input>
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="text-capital" id="userHeadinId"></h4>
		  </div>
		  <div class="modal-body">
			<table class="table table-condensed" id ="tabUserWiseRegistionDetilsId">
			</table>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		  </div>
		</div>
	  </div>
</div>
<script src="js/dataReconsolidationOverview/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/js/bootstrap.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/js/dataReConsolidationOverview.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
$(".datePicker").daterangepicker({
	opens :'left',
	maxDate:moment()
});

$(document).on('click','.openPopUpModel',function(){
	$("#myModal").modal("show");
});

function generateExcelReport()
{	
 tableToExcel("tabUserDetailsId", 'Tab user wise Registration Report');
}
</script>
<script>
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>

</body>
</html>