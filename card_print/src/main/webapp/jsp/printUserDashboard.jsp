<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PRINT USER DASHBOARD</title>

<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="dist/css/dataTables.css"/>
<script src="js/sha512.js"></script>
<style>
.tableHeadingStyle thead tr th{font-size:13px !important;padding:4px !important;}
</style>
</head>
<body>

<div class="container" style="margin-top:20px;">
	<div class="row">
		<div class="col-md-10 col-xs-12 col-sm-12 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">PRINT DASHBOARD</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="col-md-6 col-xs-12 col-sm-12">
								<div id="overAllSummaryDetailsDiv"></div>
							</div>
							<div class="col-md-6 col-xs-12 col-sm-12">
								<div id="printStatusWiseRecordsDiv"></div>
							</div>
							
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<button type="button" class="btn btn-primary btn-sm ConstituencyWisePrintDtsCls"><h5>View Constituency Wise Report</h5></button>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div id="ConstituencyWisePrintReportDiv"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="dist/js/dataTables.js"></script>
<script type="text/javascript">
   
   var printVendorId = '${cardPrintVendorId}'; 
   getPrintStatusWiseConstitCountByLoggedUser();
   getPrintStatusWiseRecordCountByLoggedUSer();
  
   
   function getPrintStatusWiseConstitCountByLoggedUser(){
		 $("#overAllSummaryDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
			var jsObj = { printVendorId : printVendorId }
			$.ajax({
				 type:'POST',
				 url:'getPrintStatusWiseConstitCountByLoggedUserAction.action',
				 dataType: 'json',
				 data: {task:JSON.stringify(jsObj)}
			  }).done(function(result){
				   $("#overAllSummaryDetailsDiv").html('');
				  buildPrintStatusWiseConstitCountByLoggedUser(result);
			  });
		}
		
	function getPrintStatusWiseRecordCountByLoggedUSer(){
	   $("#printStatusWiseRecordsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
			var jsObj = { printVendorId : printVendorId }
			$.ajax({
				 type:'POST',
				 url:'getPrintStatusWiseRecordCountByLoggedUSerAction.action',
				 dataType: 'json',
				 data: {task:JSON.stringify(jsObj)}
			  }).done(function(result){
				   $("#printStatusWiseRecordsDiv").html('');
				  buildPrintStatusWiseRecordCountByLoggedUSer(result);
			  });
	}
	
	function buildPrintStatusWiseConstitCountByLoggedUser(result){
		if(result !=null && result.length>0){
			var str ='';
					
					str+='<h5><b>SUMMARAY</b></h5>';
					str+='<div class="table-responsive m_top10" >';
						str+='<table class="table table-bordered">';
							str+='<thead>';
								str+='<tr>';
								str+='<th>Status</th>';
								str+='<th style="text-align:center;">Constituencies</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									str+='<tr>';
										str+='<td>'+result[i].name+'</td>';
										if(result[i].count !=null && result[i].count>0){
											str+='<td style="text-align:center;">'+result[i].count+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				
			
			$("#overAllSummaryDetailsDiv").html(str);		
		}else{
			
			$("#overAllSummaryDetailsDiv").html("NO DATA AVAILABLE...");		
		}
	}
	function buildPrintStatusWiseRecordCountByLoggedUSer(result){
		if(result !=null && result.length>0){
			var str ='';
					
					str+='<h5><b>PRINT STATUS WISE RECORDS</b></h5>';
					str+='<div class="table-responsive m_top10">';
						str+='<table class="table table-bordered">';
							str+='<thead>';
								str+='<tr>';
								str+='<th>Print Status</th>';
								str+='<th style="text-align:center;">Records(Cadre Count)</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									str+='<tr>';
										str+='<td>'+result[i].status+' - '+result[i].name+'</td>';
										if(result[i].count !=null && result[i].count>0){
											str+='<td style="text-align:center;">'+result[i].count+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				
			
			$("#printStatusWiseRecordsDiv").html(str);		
		}else{
			
			$("#printStatusWiseRecordsDiv").html("NO DATA AVAILABLE...");		
		}
	}
	
	$(document).on("click",".ConstituencyWisePrintDtsCls",function(){
		 constWisePrintStatusWiseRecordCountByLoggedUSer();
		  $('html, body').animate({
			scrollTop: $('#ConstituencyWisePrintReportDiv').offset().top
		}, 2000);
	});
	
	function constWisePrintStatusWiseRecordCountByLoggedUSer(){
			$("#ConstituencyWisePrintReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
			var jsObj = { printVendorId : printVendorId }
			$.ajax({
				 type:'POST',
				 url:'constWisePrintStatusWiseRecordCountByLoggedUSerAction.action',
				 dataType: 'json',
				 data: {task:JSON.stringify(jsObj)}
			  }).done(function(result){
				  $("#ConstituencyWisePrintReportDiv").html('');
				 buildconstWisePrintStatusWiseRecordCountByLoggedUSer(result);
			  });
	}
	 
		function buildconstWisePrintStatusWiseRecordCountByLoggedUSer(result){
			if(result !=null && result.length>0){
				var str='';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h5><b>CONSTITUENCY WISE REPORT</b></h5>';
					str+='<div class="table-responsive m_top10">';
						str+='<table class="table table-bordered ConstituencyWiseDataTableId tableHeadingStyle">';
							str+='<thead>';
								str+='<tr>';
									str+='<th>District</th>';
									str+='<th>Constituency</th>';
									str+='<th>E&nbsp;-&nbsp;Verification&nbsp;Failed</th>';
									str+='<th>Y&nbsp;-&nbsp;Printed</th>';
									str+='<th>N&nbsp;-&nbsp;Not&nbsp;Printed</th>';
									str+='<th>F&nbsp;-&nbsp;Print&nbsp;Failed</th>';
									str+='<th>D&nbsp;-&nbsp;Allocated</th>';
									
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									str+='<tr>';
											str+='<td>'+result[i].districtName+'</td>';
											str+='<td>'+result[i].name+'</td>';
									if(result[i].subList !=null && result[i].subList.length>0){
										for(var j in result[i].subList){
											
											//str+='<td>'+result[i].subList[j].status+ '-' +result[i].subList[j].name+'</td>';
											if(result[i].subList[j].count !=null && result[i].subList[j].count>0){
												str+='<td>'+result[i].subList[j].count+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
											
										}
									}
								str+='</tr>';									
								}
							str+='</tbody>';
						str+='</table>';
					str+='<div>';
				str+='</div>';
				
				$("#ConstituencyWisePrintReportDiv").html(str);
				$('.ConstituencyWiseDataTableId').DataTable();
				$('.ConstituencyWiseDataTableId').removeClass("dataTable");
			}
		}
</script>

</body>
</html>