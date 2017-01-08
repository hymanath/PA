<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ADMIN CARD PRINTING</title>

<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="dist/css/dataTables.css"/>
<script src="js/sha512.js"></script>
<style>
.tableHeadingStyle thead tr th{font-size:12px !important;padding:6px !important;}
.text_capital{text-transform:uppercase;}
</style>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-10 col-xs-12 col-sm-12 col-md-offset-1">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<h4 class="panel-title">CONSTITUENCY WISE PRINT DETAILS UPDATION</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div id="errorMsgDivId" style="color:red"></div>
						
						   <div class="col-md-4 col-xs-12 col-sm-4">
								<label>SELECT VENDOR:<span style="color:red"> *</span></label>
								<select id="vendorId" class="form-control">
									<option value="0">Select Vendor</option>
									 <c:if test="${basicVOList!= null && fn:length(basicVOList) gt 0}">  
	                                       <c:forEach var="vendor" items="${basicVOList}"> 
										     <option value="${vendor.id}">${vendor.name}</option>
									        </c:forEach>   
	                                 </c:if>
								</select>
						   </div>
						   
							<div class="col-md-4 col-xs-12 col-sm-4">
								<label>SELECT CONSTITUENCY:<span style="color:red"> *</span></label>
								<select id="constituencyId" class="form-control">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							
							<div class="col-md-4 col-xs-12 col-sm-4">
								<button class="buttonCls btn btn-success" onclick="updatePrintDetailsToTdpCadreCardPrint()" style="margin-top: 23px; margin-left: 20px;">SUBMIT</button>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<span id="processingImg"></span>
							<div id="successMsgDivId"></div>
							</div>
					</div>
				</div>
			</div>
				<div class="panel panel-default m_top10">
					<div class="panel-heading">
						<h4 class="panel-title">ADMIN PRINT DASHBOARD</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="col-md-6 col-xs-12 col-sm-12">
									<div id="adminSummaryDetailsDiv"></div>
								</div>
								<div class="col-md-6 col-xs-12 col-sm-12">
									<div id="adminPrintStatusWiseRecordsDiv"></div>
								</div>
								
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<button type="button" class="btn btn-primary btn-sm adminConstituencyWisePrintDtsCls"><h5>View Constituency Wise Report</h5></button>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<div id="adminConstituencyWisePrintReportDiv"></div>
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



  $('#vendorId').click(function(){
	  getConstituenciesByPrintVendor();
  });  
	
function getConstituenciesByPrintVendor(){
	 
	 $("#constituencyId option").remove();
	 $("#constituencyId").append('<option value="0"> Select Constituency</option>');
	 
	 var printVendorId = $("#vendorId").val();
	 if(printVendorId == 0){
		 return;
	 }
	 
	var jsObj = {  printVendorId : printVendorId }
	$.ajax({
	     type:'GET',
         url:'getConstituenciesByPrintVendorAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result != null){
			  for(var i in result){
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			  }
		  }
	  });
}

function updatePrintDetailsToTdpCadreCardPrint(){
	$("#errorMsgDivId").html("");
	$("#successMsgDivId").html("");
	
	var printVendorId = $("#vendorId").val();
	var constituencyId = $("#constituencyId").val();
	
	if(printVendorId == 0){
		$("#errorMsgDivId").html("Select Vendor");
		return;
	}
	if(constituencyId == 0){
		$("#errorMsgDivId").html("Select Constituency");
		return;
	}
	$("#processingImg").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj = { 
	    printVendorId  : printVendorId,
		constituencyId : constituencyId
	}
	$.ajax({
		 type:'POST',
		 url:'updatePrintDetailsToTdpCadreCardPrintAction.action',
		 dataType: 'json',
		 data: {task:JSON.stringify(jsObj)}
	  }).done(function(result){
		  $("#processingImg").html('');
		 if(result != null){
		    
			if(result.resultCode != null && result.resultCode == 1 && result.id != null && result.id == 1){
				$("#successMsgDivId").html("<h4 style='color:green'> Constituency Print Details Updated Successfully...</h4>");
			}else if( result.resultCode != null && result.resultCode == 1 && result.id != null && result.id == 0){
				$("#successMsgDivId").html("<h4 style='color:blue'> Updating To Local DataBase Success.. Updating To Live DataBase Failure..</h4>");
			}else if( (result.resultCode != null && result.resultCode == 0) || (result.resultCode == null)){
				$("#successMsgDivId").html("<h4 style='color:red'> Exception Occurred.. try Later..</h4>");
			}
		 }else{
			 $("#successMsgDivId").html("<h4 style='color:red'> Exception Occurred.. try Later..</h4>");
		 }
		 
	  })
}

   getPrintStatusWiseConstitCount();
   getPrintStatusWiseRecordCount();
 
   
	function getPrintStatusWiseConstitCount(){
		$("#adminSummaryDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
			$.ajax({
				 type:'POST',
				 url:'getPrintStatusWiseConstitCountAction.action',
				 dataType: 'json',
				 data: {}
			  }).done(function(result){
				  $("#adminSummaryDetailsDiv").html('');
				  buildPrintStatusWiseConstitCount(result);
			  });
	}
	
	function buildPrintStatusWiseConstitCount(result){
		if(result !=null && result.length>0){
			var str ='';
					var totalMaxConstituencies =0;
					var totalZebraConstituencies =0;
					str+='<h5><b>SUMMARAY</b></h5>';
					str+='<div class="table-responsive m_top10" >';
						str+='<table class="table table-bordered">';
							str+='<thead>';
								str+='<tr>';
								str+='<th>Status</th>';
								str+='<th style="text-align:center;">Max Print Constituencies</th>';
								str+='<th style="text-align:center;">Zebra Print Constituencies</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									totalMaxConstituencies =totalMaxConstituencies+result[i].maxCount;
									totalZebraConstituencies = totalZebraConstituencies+result[i].zebraCount;
									str+='<tr>';
										str+='<td>'+result[i].name+'</td>';
										if(result[i].maxCount !=null && result[i].maxCount>0){
											str+='<td style="text-align:center;">'+result[i].maxCount+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										if(result[i].zebraCount !=null && result[i].zebraCount>0){
											str+='<td style="text-align:center;">'+result[i].zebraCount+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										
									str+='</tr>';
								}
								str+='<tr style="background-color:#efefef">';
									str+='<th><b>Total</b></th>';
									str+='<th style="text-align:center;">'+totalMaxConstituencies+'</th>';
									str+='<th style="text-align:center;">'+totalZebraConstituencies+'</th>';
								str+='</tr>';
								
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				
			
			$("#adminSummaryDetailsDiv").html(str);		
		}else{
			
			$("#adminSummaryDetailsDiv").html("NO DATA AVAILABLE...");		
		}
	}	
	function getPrintStatusWiseRecordCount(){
		$("#adminPrintStatusWiseRecordsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');	
			$.ajax({
				 type:'POST',
				 url:'getPrintStatusWiseRecordCountAction.action',
				 dataType: 'json',
				 data: {}
			  }).done(function(result){
				  $("#adminPrintStatusWiseRecordsDiv").html('');
				  buildPrintStatusWiseRecordCount(result);
			  });
	}
	
	function buildPrintStatusWiseRecordCount(result){
		if(result !=null && result.length>0){
			var str ='';
					
					str+='<h5><b>PRINT STATUS WISE RECORDS</b></h5>';
					str+='<div class="table-responsive m_top10">';
						str+='<table class="table table-bordered">';
							str+='<thead>';
								str+='<tr>';
								str+='<th>Print Status</th>';
								str+='<th style="text-align:center;">Max Count</th>';
								str+='<th style="text-align:center;">Zebra Count</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									str+='<tr>';
										str+='<td>'+result[i].status+' - '+result[i].name+'</td>';
										if(result[i].maxCount !=null && result[i].maxCount>0){
											str+='<td style="text-align:center;">'+result[i].maxCount+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										if(result[i].zebraCount !=null && result[i].zebraCount>0){
											str+='<td style="text-align:center;">'+result[i].zebraCount+'</td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				
			
			$("#adminPrintStatusWiseRecordsDiv").html(str);		
		}else{
			
			$("#adminPrintStatusWiseRecordsDiv").html("NO DATA AVAILABLE...");		
		}
	}
		
	$(document).on("click",".adminConstituencyWisePrintDtsCls",function(){
		   constWisePrintStatusWiseRecordCount();
		  $('html, body').animate({
			scrollTop: $('#adminConstituencyWisePrintReportDiv').offset().top
		}, 2000);
	});	
	function constWisePrintStatusWiseRecordCount(){
		$("#adminConstituencyWisePrintReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');	
		
		$.ajax({
			 type:'POST',
			 url:'constWisePrintStatusWiseRecordCountAction.action',
			 dataType: 'json',
			 data: {}
		  }).done(function(result){
			  $("#adminConstituencyWisePrintReportDiv").html('');
			buildconstWisePrintStatusWiseRecordCount(result);
		  });
	}
	
	function buildconstWisePrintStatusWiseRecordCount(result){
			if(result !=null && result.length>0){
				var str='';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h5><b>CONSTITUENCY WISE REPORT</b></h5>';
					str+='<div class="table-responsive m_top10">';
						str+='<table class="table table-bordered AdminConstituencyWiseDataTableId tableHeadingStyle">';
							str+='<thead>';
								str+='<tr>';
									str+='<th >Vendor</th>';
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
											str+='<td>'+result[i].vendorName+'</td>';
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
				
				$("#adminConstituencyWisePrintReportDiv").html(str);
				$('.AdminConstituencyWiseDataTableId').DataTable();
				$('.AdminConstituencyWiseDataTableId').removeClass("dataTable");
			}
	}
		
		
	
</script>

</body>
</html>