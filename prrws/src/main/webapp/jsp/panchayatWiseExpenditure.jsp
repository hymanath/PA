<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panchayat Wise Expenditure Dashboard</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/exportButtons.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<link href="Assests/css/print.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1 col-xs-3 pad_left0">
					<img src="Assests/images/aplogo.png" class="logo"/>
				</div>
				<div class="col-sm-4 m_top10 col-xs-9">
					<h4 class="text-capital">Panchayat Raj, RD & RWS</h4>
					<p>Panchayat Vs Expenditure - AP</p>
				</div>
				
				<div class="col-sm-2 col-sm-offset-4 m_top20">
					<a class="btn btn-success m_top5 pull-right" href="MGNREGSFieldLogout" style="display:inline-block" style="cursor:pointer;">LOGOUT</a>
				</div>
			</div>
		</div>
	</nav>
</header>
<main>
	<div class="container-fluid">
		<div class="row m_top10">
			<div class="col-sm-12">
				<div class="">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default panel-black">
						<div class="panel-heading" role="tab" id="heading">
						  <a role="button" class="panelCollapseIcon"  data-toggle="collapse" data-parent="#accordion2" href="#collapse2" aria-expanded="true" aria-controls="collapse2">
							<div class="row">
								<div class="col-sm-8">
									<h4 class="panel-title text-capital m_top10">Component Wise Comments/Action Plan Updating</h4>
								</div>
							</div>
							
						  </a>
						</div>
						<div id="collapse2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading">
						  <div class="panel-body">
								<div class="row">
									<div class="col-sm-8 labourBudgetRadioCls">
										<label><input type="radio" name="radio" value="TOT" checked class="radioBtnCls"/>&nbsp;Total&nbsp;</label> 
										<label><input type="radio" name="radio" value="WAGE" class="radioBtnCls"/>&nbsp;Wage&nbsp;</label> 
										<label><input type="radio" name="radio" value="MAT" class="radioBtnCls"/>&nbsp;Material&nbsp;</label> 
									</div>
									<div class="col-sm-4 pull-right">
										<div class="row">
											<div class="col-sm-4">
												<label>Component:</label>
											</div>
											<div class="col-sm-6" style="margin-left: -36px;">
												<select class="form-control chosen-select componentCls">
													<option value="Labour Budget">Labour Budget</option>
													<option value="Farm Ponds">Farm Ponds</option>
													<option value="IHHL">IHHL</option>
													<option value="Vermi Compost">Vermi Compost</option>
													<option value="Solid Waste Management">Solid Waste Management</option>
													<option value="Play fields">Play fields</option>
													<option value="Burial Grounds">Burial Grounds</option>
													<option value="Timely Payment">Timely Payment</option>
												</select>
											</div>
										</div>
										
									</div>
									
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<div class="white_block">
											<div id="panchtExptDivId"></div>
										</div>
									</div>
								</div>
								<div class="row m_top10">
									<div class="col-sm-12">
										<div class="white_block">
											<div id="panchtsDetailsDivId"></div>
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
	</div>
</main>
<div class="modal fade" tabindex="-1" id="panchayatModalId" role="dialog" style="z-index:99999;">
		<div class="modal-dialog" style="width:60%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeShowPdfCls1" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="panchayatId">Panchayats vs Expenditure comments updating</h4>  
				</div>
				<div class="modal-body">
					<div class="row">	
						<div class="col-sm-4">
						<label>Select Status</label>
							<select class="form-control chosen-select" id="statusModalId">
								<!--<option value="0">Select Status </option>-->
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
						  <label>Comment</label>
							<textarea class="form-control" rows="3" id="commentId"></textarea>
						</div>
						<div class="col-sm-6">
						   <label>Action Plan</label>
							<textarea class="form-control" rows="3" id="actionTypeId"></textarea>
						</div>
					</div>	
							
					<div class="row">
						<div class="col-sm-12 m_top10">
							<button type="button" class="btn btn-success btn-md" id="updateId" style="boder-radius:0px;">Submit</button> 
							<div id="errorId"  style="color:red;"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top10">
							<span id="loadingId"></span>
							<span id="UpdatedMsgId" style="color:green;"></span>
						</div>
					</div>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div><!--  /.modal-content -->  
		</div><!--  /.modal-dialog -->
	</div>
<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/exportButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/jsZip.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/pdf.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/v5font.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/htmlButtons.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTables.fixedColumns.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
<script type="text/javascript" src="http://cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js"></script>
<script> 
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var smallSpinner = '<img src="Assests/images/spinner.gif" style="width:25px;height:25px;"/>';
getPanchayatsExpenditure("TOT","Labour Budget");
getPanchatVsExpData("TOT","Labour Budget");
function getPanchayatsExpenditure(radioType,divType)
{
	$("#panchtExptDivId").html(spinner);
	var fromDate = "2017-04-01";
	var toDate = moment().format("YYYY-MM-DD");
	var json = {
		year : "2017",
		fromDate : fromDate,
        toDate : toDate,
        pType : radioType
	}
  $.ajax({
    url: 'getPanchayatsExpenditure',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		$("#panchtExptDivId").html('');
		buildPanchayatsExpenditure(ajaxresp,divType);
	}
  });
}

function buildPanchayatsExpenditure(result,divType){
	var str='';
	if(divType == "Labour Budget"){
		str+='<h4 class="text-capital font_weight" style="margin-left: 15px;">Panchayats Vs Expenditure Comments</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css">';
				str+='<thead>';
					 str+='<tr>';
						 str+='<th></th>';
						 for(var i in result){
							 str+='<th>'+result[i].name+'</th>';
						 }
					  str+='</tr>';
					 str+='</thead>'; 
					str+='<tbody>';
						str+=' <tr">';
							str+=' <td>As On Nov 30th</td>';
							for(var i in result){
								str+='<td>'+result[i].orderNo+'</td>';
							}
						str+='</tr>';
					str+=' <tr>';
						str+=' <td>As Of Today</td>';
						for(var i in result){
							if(result[i].count != null && result[i].count != 0){
								str+='<td>'+result[i].count+'</td>';
							}else{
								str+='<td>'+result[i].count+'</td>';
							}
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		str+='<div class="table-responsive m_top20">';
			str+='<h4><b>BELOW 20L EXPENDITURE PANCHAYATS CHANGED STATUS SUMMARY FROM NOV-30th TO TODAY</b></h4>';
			str+='<table class="table table-striped table-bordered table_labour_css m_top10">';
				str+='<thead>';
					str+='<tr>';
					str+='<th>Expenditure Ranges (In Lakhs)</th>';
					str+='<th>As On Nov 30th</th>';
					str+='<th>As Of Today</th>';
					str+='<th>Changed</th>';
					str+='<th>0</th>';
					str+='<th>Below 1</th>';
					str+='<th>1-5</th>';
					str+='<th>5-10</th>';
					str+='<th>10-20</th>';
					str+='<th>Above 20</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					if(result[0].subList != null && result[0].subList.length > 0){
						for(var i in result[0].subList){
							str+='<tr>';
								str+='<td>'+result[0].subList[i].name+'</td>';
								str+='<td>'+result[0].subList[i].orderNo+'</td>';
								str+='<td>'+result[0].subList[i].count+'</td>';
								str+='<td>'+result[0].subList[i].changedCount+'</td>';
								str+='<td>'+result[0].subList[i].zeroCount+'</td>';
								str+='<td>'+result[0].subList[i].belowOneCount+'</td>';
								str+='<td>'+result[0].subList[i].oneToFiveCount+'</td>';
								str+='<td>'+result[0].subList[i].fiveToTenCount+'</td>';
								str+='<td>'+result[0].subList[i].tenToTwentyCount+'</td>';
								str+='<td>'+result[0].subList[i].diffCount+'</td>';
							str+='</tr>';
						}
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}else{
		str+='<h4 class="text-capital font_weight" style="margin-left: 15px;">'+divType+' Achievement Comparision Summary From NOV-30th to Today</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css">';
				str+='<thead>';
					str+='<tr>';
						str+='<th></th>';
						str+='<th>Red</th>';
						str+='<th>Orange</th>';
						str+='<th>Green</th>';
						str+='<th>Gold</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<th>As Of Nov 30th</th>';
						str+=' <td>'+result.previousRedMandals+'</td>';
						str+=' <td>'+result.previousOrangeMandals+'</td>';
						str+=' <td>'+result.previousGreenMandals+'</td>';
						str+=' <td>'+result.previousGoldMandals+'</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<th>As Of Today</th>';
						str+=' <td>'+result.mandalsInRed+'</td>';
						str+=' <td>'+result.mandalsInOrange+'</td>';
						str+=' <td>'+result.mandalsInGreen+'</td>';
						str+=' <td>'+result.mandalsInGold+'</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		
		str+='<div class="table-responsive m_top20">';
			str+='<h4><b>ACHIEVEMENT PERCENTAGE BELOW 60% MANDALS CHANGED STATUS SUMMARY FROM NOV-30th TO TODAY</b></h4>';
			str+='<table class="table table-striped table-bordered table_labour_css m_top10">';
				str+='<thead>';
					str+='<tr>';
						str+='<th></th>';
						str+='<th>As On Nov 30th</th>';
						str+='<th>As Of Today</th>';
						str+='<th>Changed</th>';
						str+='<th>Red</th>';
						str+='<th>Orange</th>';
						str+='<th>Green</th>';
						str+='<th>Gold</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					if(result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].name+'</td>';
								str+='<td>'+result.subList[i].previousCount+'</td>';
								str+='<td>'+result.subList[i].presentCount+'</td>';
								str+='<td>'+result.subList[i].changedCount+'</td>';
								str+='<td>'+result.subList[i].mandalsInRed+'</td>';
								str+='<td>'+result.subList[i].mandalsInOrange+'</td>';
								str+='<td>'+result.subList[i].mandalsInGreen+'</td>';
								str+='<td>'+result.subList[i].mandalsInGold+'</td>';
							str+='</tr>';
						}
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}/* else{
		str+='<h4 class="text-capital font_weight" style="margin-left: 15px;">'+divType+' Overview</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css">';
				str+='<thead>';
					str+='<tr>';
						str+='<th></th>';
						str+='<th>Red</th>';
						str+='<th>Orange</th>';
						str+='<th>Green</th>';
						str+='<th>Gold</th>';
					str+='</tr>';
				str+='</thead>'; 
				str+='<tbody>';
					str+=' <tr">';
						str+=' <td>Mandals Count</td>';
						str+=' <td>'+result.mandalsInRed+'</td>';
						str+=' <td>'+result.mandalsInOrange+'</td>';
						str+=' <td>'+result.mandalsInGreen+'</td>';
						str+=' <td>'+result.mandalsInGold+'</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	} */
	$("#panchtExptDivId").html(str);
}

function getPanchatVsExpData(radioType,divType)
{
	$("#panchtsDetailsDivId").html(spinner)
	var fromDate = "2017-04-01";
	var toDate = moment().format("YYYY-MM-DD");
	var json = {
		year : "2017",
		fromDate : fromDate,
        toDate : toDate,
        pType : radioType
       
	}
  $.ajax({
    url: 'getPanchatVsExpData',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		$("#panchtsDetailsDivId").html('');
		buildLabourBudgetPanExpData(ajaxresp,divType);
    }
  });
}

function buildLabourBudgetPanExpData(result,divType){
	var str='';
	if(divType == "Labour Budget"){
		str+='<h4 class="font_weight text-capital">Below 20l expenditure Panchayats</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css" id="larBudExpTableId">';
				str+='<thead>';
					str+='<tr>';
					str+='<th>District</th>';
					str+='<th>Assembly</th>';
					str+='<th>Mandal</th>';
					str+='<th>Panchayat</th>';
					str+='<th>Range</th>';
					str+='<th>Total Expenditure</th>';
					str+='<th>Status</th>';
					str+='<th>Comment</th>';
					str+='<th>Action Plan</th>';
					str+='<th></th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].district+'</td>';
						str+='<td>'+result[i].constituency+'</td>';
						str+='<td>'+result[i].mandal+'</td>';
						str+='<td>'+result[i].panchayat+'</td>';
						str+='<td>'+result[i].range+'</td>';
						str+='<td>'+result[i].totalExpenditure+'</td>';
						
						if(result[i].status != null){
						str+='<td>'+result[i].status+'</td>';
						}else{
							str+='<td>Not Updated</td>';
						}	
						if(result[i].comments != null){
						str+='<td>'+result[i].comments+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].actionPlan != null){
						str+='<td>'+result[i].actionPlan+'</td>';
						}else{
							str+='<td>-</td>';
						}
						str+='<td><i class="glyphicon glyphicon-edit modalIconOpen"  style="cursor:pointer;" title="click here to update"  attr_unicode="'+result[i].uniqueCode+'" attr_componentId="'+result[i].componentId+'" attr_status_id="'+result[i].statusId+'" attr_comments ="'+result[i].comments+'" attr_actionPlan ="'+result[i].actionPlan+'" attr_panchayat="'+result[i].panchayat+'"></i></td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}else if(divType == "Timely Payment"){
		str+='<h4 class="font_weight text-capital">'+divType+' Low Achievement Mandals</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css" id="larBudExpTableId">';
				str+='<thead>';
					str+='<th>District</th>';
					str+='<th>Assembly</th>';
					str+='<th>Mandal</th>';
					str+='<th>Target (%)</th>';
					str+='<th>Achievement (%)</th>';
					str+='<th>Achieved (%)</th>';
					str+='<th>Status</th>';
					str+='<th>Comment</th>';
					str+='<th>Action Plan</th>';
					str+='<th></th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].district+'</td>';
						str+='<td>'+result[i].constituency+'</td>';
						str+='<td>'+result[i].mandal+'</td>';
						str+='<td>'+result[i].target+'</td>';
						str+='<td>'+result[i].achivement+'</td>';
						str+='<td>'+result[i].percentage+'</td>';
						
						if(result[i].status != null){
						str+='<td>'+result[i].status+'</td>';
						}else{
							str+='<td>Not Updated</td>';
						}	
						if(result[i].comments != null){
						str+='<td>'+result[i].comments+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].actionPlan != null){
						str+='<td>'+result[i].actionPlan+'</td>';
						}else{
							str+='<td>-</td>';
						}
						str+='<td><i class="glyphicon glyphicon-edit modalIconOpen"  style="cursor:pointer;" title="click here to update"  attr_unicode="'+result[i].uniqueCode+'" attr_componentId="'+result[i].componentId+'" attr_status_id="'+result[i].statusId+'" attr_comments ="'+result[i].comments+'" attr_actionPlan ="'+result[i].actionPlan+'" attr_panchayat="'+result[i].panchayat+'"></i></td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}else{
		str+='<h4 class="font_weight text-capital">'+divType+' Low Achievement Mandals</h4>';
		str+='<div class="table-responsive m_top20">';
			str+='<table class="table table_right_css" id="larBudExpTableId">';
				str+='<thead>';
					str+='<th>District</th>';
					str+='<th>Assembly</th>';
					str+='<th>Mandal</th>';
					str+='<th>Target</th>';
					str+='<th>Grounded</th>';
					str+='<th>Not Grounded</th>';
					str+='<th>Completed</th>';
					str+='<th>Achievement (%)</th>';
					str+='<th>Wage Exp</th>';
					str+='<th>Material Exp</th>';
					str+='<th>Total Exp</th>';
					str+='<th>Status</th>';
					str+='<th>Comment</th>';
					str+='<th>Action Plan</th>';
					str+='<th></th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].district+'</td>';
						str+='<td>'+result[i].constituency+'</td>';
						str+='<td>'+result[i].mandal+'</td>';
						str+='<td>'+result[i].target+'</td>';
						str+='<td>'+result[i].grounded+'</td>';
						str+='<td>'+result[i].notGrounded+'</td>';
						str+='<td>'+result[i].completed+'</td>';
						str+='<td>'+result[i].percentage+'</td>';
						str+='<td>'+result[i].wageExpenditure+'</td>';
						str+='<td>'+result[i].materialExpenditure+'</td>';
						str+='<td>'+result[i].totalExpenditure+'</td>';
						
						if(result[i].status != null){
						str+='<td>'+result[i].status+'</td>';
						}else{
							str+='<td>Not Updated</td>';
						}	
						if(result[i].comments != null){
						str+='<td>'+result[i].comments+'</td>';
						}else{
							str+='<td>-</td>';
						}
						if(result[i].actionPlan != null){
						str+='<td>'+result[i].actionPlan+'</td>';
						}else{
							str+='<td>-</td>';
						}
						str+='<td><i class="glyphicon glyphicon-edit modalIconOpen"  style="cursor:pointer;" title="click here to update"  attr_unicode="'+result[i].uniqueCode+'" attr_componentId="'+result[i].componentId+'" attr_status_id="'+result[i].statusId+'" attr_comments ="'+result[i].comments+'" attr_actionPlan ="'+result[i].actionPlan+'" attr_panchayat="'+result[i].panchayat+'"></i></td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	}
		
	$("#panchtsDetailsDivId").html(str);
	$("#larBudExpTableId").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   divType,
				filename:  divType+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   divType,
				filename:  divType+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}

$(document).on("click",".modalIconOpen",function(){
		$("#UpdatedMsgId").html("");
		$("#statusModalId").val('');
		$("#commentId").val('');
		$("#actionTypeId").val('');
		$("#errorId").html('');
		var unicode = $(this).attr("attr_unicode");
		var componentId = $(this).attr("attr_componentId");
		var statusId=$(this).attr("attr_status_id");
		var comments=$(this).attr("attr_comments");
		var actionPlan =$(this).attr("attr_actionPlan");
		getNregaComponentStatus(statusId,comments,actionPlan);
		var panchayatName=$(this).attr("attr_panchayat");
		$("#panchayatModalId").modal("show");
		var componentName = $(".componentCls").val();
		if(componentName == "Labour Budget")
			$("#panchayatId").html(panchayatName+"  "+"Panchayats Vs Expenditure Comments Updating");
		else
			$("#panchayatId").html("Comments Updating");
		$("#updateId").attr("attr_unicode",unicode)
		$("#updateId").attr("attr_componentId",componentId)
		/* var formRange = $(this).attr("attr_fromrange");
		var toRange = $(this).attr("attr_toRange");
		var locationType =$(this).attr("attr_locationType");
		var locationId = $(this).attr("attr_locationVal");
		var range=$(this).attr("attr_range");
		$("#updateId").attr("attr_fromrange",formRange)
		$("#updateId").attr("attr_toRange",toRange)
		$("#updateId").attr("attr_locationType",locationType)
		$("#updateId").attr("attr_locationVal",locationId)
		$("#updateId").attr("attr_range",range) */
});

function getNregaComponentStatus(statusId,comments,actionPlan){
		$("#statusModalId").empty();
		var json = {
			
			
		}
		$.ajax({                
			type:'POST',    
			url: 'getNregaComponentStatus',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				
				$("#statusModalId").append('<option value="0">Select Status</option>');
				for(var i in result){
					$("#statusModalId").append('<option value="'+result[i].sourceId+'">'+result[i].displayType+' </option>');
					$("#statusModalId").val(statusId);
					if(comments != 'undefined'){
					$("#commentId").val(comments);
					}
					if(actionPlan != 'undefined'){
		            $("#actionTypeId").val(actionPlan);
					}
				}
			}
		
			$("#statusModalId").chosen();
			$("#statusModalId").trigger('chosen:updated');
		});
	}
$(document).on("click","#updateId",function(){
	var statusId= $("#statusModalId").val();
	var commentId =$("#commentId").val();
	var actionTypeId =$("#actionTypeId").val();
	var uniqueCode = $(this).attr("attr_unicode");
	var componentId = $(this).attr("attr_componentId");
	/* var districtId = $("#selectedName").attr("attr_distid"); 
	var locationType = $(this).attr("attr_locationType");
	var locationId =$(this).attr("attr_locationVal");
	var fromRange = $(this).attr("attr_fromrange");
	var toRange = $(this).attr("attr_toRange");
	var range =$(this).attr("attr_range"); */
	savePanchayatComponentComments(statusId,commentId,actionTypeId,uniqueCode,componentId);
});	

function savePanchayatComponentComments(statusId,comment,actionType,uniqueCode,componentId){
	if(componentId == "undefined"){
		componentId = 0;
	}
	if($("#statusModalId").val() == 0){
		$("#errorId").html("Please Select Status");
		return;
	}else{
	 $("#errorId").html("");	
	}
	if($("#commentId").val().trim() == '' || $("#commentId").val().trim() == null){
		$("#errorId").html("Required Comment");
		return;
	}else{
	 $("#errorId").html("");	
	}
	if($("#actionTypeId").val().trim() == '' || $("#actionTypeId").val().trim() == null){
		$("#errorId").html(" Required Action Plan ");
		return;
	}else{
	 $("#errorId").html("");	
	}
	$("#loadingId").html(smallSpinner);
	var componentName = $(".componentCls").val();
		var json = {
			locationId:componentId,
			sourceId :statusId,
			category :comment,
			assetType :actionType,
			displayType : uniqueCode,
			divType : componentName
		}
		$.ajax({                
			type:'POST',    
			url: 'savePanchayatComponentComments',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			$("#loadingId").html('');
			if(result != null && result.displayType == "success"){
				$("#UpdatedMsgId").html("Comments Updated Successfully.");
				//alert("Comments Updated Successfully.");
				setTimeout(function(){
					$("#panchayatModalId").modal("hide");
					if(componentName == "Labour Budget"){
						getPanchatVsExpData("TOT",componentName);
					}else{
						getComponentWiseLocationData(componentName);
					}
				}, 1500);
			}
	});
}
$(document).on("click",".radioBtnCls",function(){
	var radioType = $(this).val();
	getPanchayatsExpenditure(radioType,"Labour Budget");
	getPanchatVsExpData(radioType,"Labour Budget");
});

$(document).on("change",".componentCls",function(){
	$(".labourBudgetRadioCls").hide();
	var component = $(this).val();
	if(component == "Labour Budget"){
		$(".labourBudgetRadioCls").show();
		 $("input[name='radio'][value='TOT']").attr('checked', true);
		getPanchayatsExpenditure("TOT",component);
		getPanchatVsExpData("TOT",component);
	}else{
		getcomponentWiseOverview(component);
		getComponentWiseLocationData(component)
	}
});

function getcomponentWiseOverview(divType)
{
	$("#panchtExptDivId").html(spinner);
	var fromDate = "2017-04-01";
	var toDate = moment().format("YYYY-MM-DD");
	var json = {
		year : "2017",
		fromDate : fromDate,
        toDate : toDate,
        divType : divType,
		program : "-1"
	}
  $.ajax({
    url: 'getComponentWiseOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		$("#panchtExptDivId").html('');
		buildPanchayatsExpenditure(ajaxresp,divType);
	}
  });
}

function getComponentWiseLocationData(divType)
{
	$("#panchtsDetailsDivId").html(spinner);
	var fromDate = "2017-04-01";
	var toDate = moment().format("YYYY-MM-DD");
	var json = {
		year : "2017",
		fromDate : fromDate,
        toDate : toDate,
        divType : divType,
		sublocaType : "mandal",
		program : "-1"
	}
  $.ajax({
    url: 'getComponentWiseLocationData',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		$("#panchtsDetailsDivId").html('');
		buildLabourBudgetPanExpData(ajaxresp,divType);
	}
  });
}

</script> 
</body>
</html>