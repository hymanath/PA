<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Data Reconsolidation Overview</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>

</head>
<body>
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<h4 class="panel-title">HEASIND</h4>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
				<div class="row">
				<div class="col-md-3 col-xs-12 col-sm-6 pull-left">
					 <label>STATE<span style="color:red"> * </span></label>
					 <span id="stateDivIdImg"  style="display:none;"><img src="images/search.gif"/></span>
						<select  class="form-control" id="stateOverViewId" >
							<option value="0">Select State</option>
							<option value="1">ANDHRA PRADESH</option>
							<option value="36">TELANGANA</option>
						</select> 
					</div>
						
					<div class="col-md-2 col-xs-12 col-sm-5 ">
					<label>DISTRICT<span style="color:red"> * </span></label>
					<span id="districtDivIdImg"  style="display:none;"><img src="images/search.gif"/></span>
						<select  class="form-control" id = "districtOverViewId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 ">
					<label>CONSTITUENCY</label>
						<span id="constituencyDivIdImg"  style="display:none;"><img src="images/search.gif"/></span>
						<select  class="form-control" id ="constituencyOverViewId">
							<option value="0">Select Constituency</option>
						</select>
				   </div>
						
					 <div class="col-md-3 col-xs-12 col-sm-6 ">
					 <div><label>DATE</label></div>
							<div class="input-group inputGCustom">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								 <input type="text" class="form-control datePicker"/>
							</div>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-1">
					<label></label>
					<button type="button" class ="btn btn-success m_top20" value ="submit" id="submitId">SUBMIT</button>
					<!--<span id="submitDivIdImg"  style="display:none;"><img src="images/search.gif"/></span>-->
					</div>
					</div>
					<div id = "errorDivId" style = "color:red"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
					<div id="dataReconsalationOverviewId"></div>
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="locWiseSmartDivCount" style="display:none;">
					<!--<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Data Reconsolidation</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<th>District</th>
										<th>Count</th>
									</thead>
									<tbody>
										<td>District</td>
										<td>Count</td>
									</tbody>
								</table>
							</div>
						</div>
					</div>-->
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
					<div id="userWiseTotalViewId"></div>
				</div>
				
			</div>
				
				
		 </div>  
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
   <div class="modal-dialog modal-lg" role="document" style="width: 85%;">
	  <div class="modal-content">
		    <div class="modal-header">
			   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			    <h4 class="text-capital" id="userHeadinId"></h4> <button type="button" class="btn btn-success pull-right" 
			    style="margin-left: 1px; width: 126px; padding-left: 10px; border-left-width: 0px; border-top-width: 0px;" id="exportToExcelId" onClick ="generateExcelReport()" ><img src ="img/Excel.png" style="height:18px;width:18px;"/>&nbsp;&nbsp;ExportToExcel</button>
		    </div>
		<div class="modal-body">
			   <div class="table table-condensed" id ="tabUserWiseRegistionDetilsId">
			   </div>
			   <div class="table table-condensed" id ="tabUserWiseExportExcelDivId" style="display:none;">
			   </div>
		</div>
		<div class="modal-footer">
			 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	 </div>
  </div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/Date/daterangepicker.js" type="text/javascript"></script>
<script src="js/dataReconsolidationOverview1/js/dataReConsolidationOverview.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
var defaultDate = moment().startOf('month').format("MM/DD/YYYY")+' -'+moment().startOf('month').format("MM/DD/YYYY")
$(".datePicker").val(defaultDate)
$(".datePicker").daterangepicker({
	opens :'left',
	startDate:moment().startOf('month'),
	endDate:moment().startOf('month'),
	maxDate:moment()
});

$(document).on('click','.openPopUpModel',function(){
	$("#myModal").modal("show");
});

function generateExcelReport()
{	
 tableToExcel("tabUserExportExcelId", 'Tab user wise Registration Report');
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