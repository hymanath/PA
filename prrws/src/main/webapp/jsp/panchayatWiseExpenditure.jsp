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
				<div class="white_block">
					<label><input type="radio" name="radio" value="TOT" checked class="radioBtnCls"/>&nbsp;Total&nbsp;</label> 
					<label><input type="radio" name="radio" value="WAGE" class="radioBtnCls"/>&nbsp;Wage&nbsp;</label> 
					<label><input type="radio" name="radio" value="MAT" class="radioBtnCls"/>&nbsp;Material&nbsp;</label> 
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
getPanchayatsExpenditure("TOT");
getPanchatVsExpData("TOT");
function getPanchayatsExpenditure(radioType)
{
	$("#panchtExptDivId").html(spinner)
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
		buildPanchayatsExpenditure(ajaxresp);
	}
  });
}

function buildPanchayatsExpenditure(result){
	var str='';
		str+='<h4 class="text-capital font_weight">Panchayaties Vs Expenditure Comments</h4>';
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
					str+=' <tr>';
						str+=' <td>Grand Total</td>';
						for(var i in result){
							if(result[i].count != null && result[i].count != 0){
								str+='<td>'+result[i].count+'</td>';
							}else{
								str+='<td>'+result[i].count+'</td>';
							}
						}
					str+='</tr>';
					str+=' <tr>';
						str+=' <td>Percentage</td>';
						for(var i in result){
							str+='<td>'+result[i].totl+'</td>';
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#panchtExptDivId").html(str);
}

function getPanchatVsExpData(radioType)
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
		buildLabourBudgetPanExpData(ajaxresp)
    }
  });
}

function buildLabourBudgetPanExpData(result){
			var str='';
				str+='<h4 class="font_weight text-capital">Panchayat Details</h4>';
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
									str+='<td>-</td>';
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
		$("#panchtsDetailsDivId").html(str);
		$("#larBudExpTableId").dataTable({
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o"></i>',
					titleAttr: 'CSV',
					title:	   'Labour Budget',
					filename:  'Labour Budget'+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o"></i>',
					titleAttr: 'PDF',
					title:	   'Labour Budget',
					filename:  'Labour Budget'+moment().format("DD/MMMM/YYYY  HH:MM"),
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
		$("#panchayatId").html(panchayatName+"  "+"Panchayats Vs Expenditure Comments Updating");
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
	$("#loadingId").html(smallSpinner);
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
		var json = {
			locationId:componentId,
			sourceId :statusId,
			category :comment,
			assetType :actionType,
			displayType : uniqueCode
			
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
				$("#UpdatedMsgId").html("Comments Updated Successfully.")
				//alert("Comments Updated Successfully.");
				setTimeout(function(){
					$("#panchayatModalId").modal("hide");
					getPanchatVsExpData();
				}, 1500);
			}
	});
}
$(document).on("click",".radioBtnCls",function(){
	var radioType = $(this).val();
	getPanchayatsExpenditure(radioType);
	getPanchatVsExpData(radioType);
});
</script> 
</body>
</html>