<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Cadre Transactions Details </title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
		<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>
		
		<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.f-16{font-size: 16px;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	
		#daywiseReportTab  thead th {
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 15px !important;
		
		
	</style>
   
	
</head>
  <body  class="bgc">
	
		  	<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
		
	<div class="container ">	
		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 ">
				<h3 class="text-center text-uppercase"> Money Report & Transaction Details </h3>
			</div>
		</div><!-- Title Row End-->
		
		<!--  -->
		<div class="row-fluid " id="PreviousmembersCount">
			<div class="span6 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>
							
								<td><h2>${surveyTransactionReportVO.todayRecords}</h2><p>Today Live Records</p></td>
								<td><h2>${surveyTransactionVO.weekCount}</h2><p>This Week Records</p></td>
								<td><h2>${surveyTransactionReportVO.totalRecords}</h2><p>Total Records</p></td>
								
							</tr>
							
						</tbody>
					</table>
			</div>
			
			<div class="span6 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p>Total Teams Count</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.totalUsers}</h2></td></tr>
								<td style="width:70%;"><p>Today Teams Count</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayUsers}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
		</div><!--  -->
		
		<div class="row-fluid ">			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p>Today OTP Requested</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.otpReqCount}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p>Today OTP transaction completed</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayotpTransactionCompleted}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p>Today OTP transaction Pending</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayotpTransactionPending}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
		</div>	
		<div class="row-fluid ">
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p class="m_top10">With out OTP transaction completed count</p></td><td  style="width:30%;"><h2>${surveyTransactionVO.otpNonRequestCount}</h2></td></tr>
							
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row-fluid " >	
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Today Total Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayMoneyCollected}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Today Submitted Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayPaidAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Pending Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.todayPendingAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
		</div>
		
			<div class="row-fluid " >	
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>YesterDay Total Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.yesterDayMoneyCollected}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>YesterDay Submitted Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.yesterDayPaidAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Pending Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionReportVO.yesterDayPendingAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row-fluid " >	
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				<div id="daywiseReportDiv"></div>
			</div>
		</div>
		<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="errMsgDiv"></div>
				  <table  style="margin-left: 270px;">
				     <tr>
					   <td><b>Select Report Level :</b></td>
					   <td><select id="locationsDispalyId" onchange="showCorrespondingLocs(this.value);">
							 <option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Parliament Constituency</option>
					       </select> 
					   </td>
				     </tr>
				     <tr id="statesDispalyMainDiv">
						 <td><b>Select State :</b></td>
						 <td>  
						   <select id="statesDispalyId" onchange="getLocationDetailsForState(this.value);">
							 <option value="0">All</option>
							 <option value="1">Andhra Pradesh</option>
							 <option value="2">Telangana</option>
						   </select> 
						 </td>
					 </tr>
					 <tr id="districtsDispalyMainDiv" style="display:none;">
						 <td><b>Select District :</b></td>
						 <td>  
						   <select id="districtsDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="constituencyDispalyMainDiv" style="display:none;">
						 <td><b>Select Constituency :</b></td>
						 <td>  
						   <div id="constituencySelectDIV"><select id="constituencyDispalyId">
						   </select></div>
						 </td>
					 </tr>
					 <tr id="mandalDispalyMainDiv" style="display:none;">
						 <td><b>Select Parliament :</b></td>
						 <td>  
						   <select id="mandalDispalyId">
						   </select> 
						 </td>
					 </tr>
					 
					  <tr id="formDateDiv" >
						 <td><b>From Date :</b></td>
						 <td>  
						   <input type="text" id="fromDate" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					  <tr id="toDateDiv">
						 <td><b>To Date :</b></td>
						 <td>  
						    <input type="text" id="toDate" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls "  placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					 
					 <tr>
					 <td></td>
					 <td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="locationWiseTransactionReport();" value="Submit"/>
					 <img id="ajaxImgStyleNew" style="display:none;margin-left:10px; margin-top:10px;" src="images/icons/search.gif"/></td>
					 </tr>
				  </table>
				  <div id="locationWiseTransactionDiv"></div>
			  </div>
		   </div>
		
	</div>
	
		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		
	<script>
	$(document).ready(function(){
		//daywiseReport();
		locationWiseTransactionReport();
		 $("#fromDate,#toDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#fromDate,#toDate").datepicker("setDate", new Date());
		
	});	
	
	function getLocationDetailsForState()
	{
	  var locationLvl = $('#locationsDispalyId').val();
	  var divId = '';
	  var stateId =  $('#statesDispalyId').val();
	  var dataType = '';
	  var selectOption = '';
	 
	  if(locationLvl == 3)
	  {
		divId = 'districtsDispalyId';
		dataType = "district";
		selectOption = 'Select District ';
	  }
	  else if(locationLvl == 4)
	  {
	  	divId = 'constituencyDispalyId';
		dataType = "assembly";
		selectOption = 'Select Constituency ';
	  }
	  else if(locationLvl == 5)
	  {
		divId = 'mandalDispalyId';	
		dataType = "parliament";
		selectOption = 'Select Parliament ';
	  }
	  
	  if(locationLvl != 2)
	  {	 
		  var jsObj = {
				searchType :dataType,
				stateTypeId : stateId,
				tesk:"locationWiseTransactionReport"            
		   }
	  
			$.ajax({
				type : "POST",
				url : "getParliamentsForStateAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> '+selectOption+' </option>');
				if(result != null && result.length >0)
				{
					for(var i in result)
					{
						$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
					}
				}
			});
		}
	}
	function showCorrespondingLocs(locationLvl)
	{
	  $("#statesDispalyMainDiv").show();
	  $("#statesDispalyId").val(0);
	  
	  if(locationLvl == 3)
	  {
		$("#districtsDispalyMainDiv").show();
		$("#districtsDispalyId").val(0);
		$("#constituencyDispalyMainDiv").hide();
		$("#mandalDispalyMainDiv").hide();
		
	  }else if(locationLvl == 4)
	  {
		$("#districtsDispalyMainDiv").hide();
		$("#constituencyDispalyMainDiv").show();
		$("#mandalDispalyMainDiv").hide();
	  }
	 else if(locationLvl == 5)
	  {
		$("#mandalDispalyMainDiv").show();
		$("#districtsDispalyMainDiv").hide();
		$("#constituencyDispalyMainDiv").hide();		
	  }
	getLocationDetailsForState();
   }
	
	function locationWiseTransactionReport()
	{	
	var locationIdsArr = new Array();	
	locationIdsArr.push(232);
	$('#locationWiseTransactionDiv').html('');
	$('#ajaxImgStyleNew').show();
		var jsObj = {
			fromDate:"2014-10-10",
			toDate : "2014-10-30",
			searchType :"assembly",
			locationIds : locationIdsArr,
			tesk:"locationWiseTransactionReport"            
	   }	
		$.ajax({
			type : "POST",
			url : "locationWiseTransactionReportAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$('#ajaxImgStyleNew').hide();
			if(result !=null && result.surveyTransactionVOList != null && result.surveyTransactionVOList.length >0)
			{
				buildlocationWiseTransactionReports(result);	
			}
			else
			{
				$('#locationWiseTransactionDiv').html(' No Data Available...');
			}			
		});			
	}
	
	function buildlocationWiseTransactionReports(result)
	{
		var str ='';
		str+='<h4 align="center"> Location Wise Transaction Report </h4>';
		str +='<table class="table " id="locationWiseReportTab">';
		str +='<thead>';
		str +='<tr>';
		str +='<th> User Name </th>';
		str +='<th> Constituency Name </th>';
		str +='<th> No of Days Worked </th>';
		str +='<th> No of OTP Payments Done </th>';
		str +='<th> No of records collected As per live  </th>';
		str +='<th> No of records collected As per OTP Recieved  </th>';
		str +='<th> Payments Recieved  </th>';
		str +='</tr>';
		str +='</thead>';
		str +='<tbody>';
		
		for(var i in result.surveyTransactionVOList)
		{
			str +='<tr>';
			str +='<td></td>';
			str +='<td></td>';
			str +='<td></td>';
			str +='<td></td>';
			str +='<td></td>';
			str +='<td></td>';
			str +='<td></td>';
			str +='</tr>';		
		}
		str +='</tbody>';
		str +='</table>';
		$('#locationWiseTransactionDiv').html(str);
		$('#locationWiseReportTab').dataTable({});
	}
	function daywiseReport()
	{	
	$('#daywiseReportDiv').html('');
		var jsObj = {
		fromDate:"2014-10-10",
		toDate : "2014-10-15",
		 tesk:"daywiseTransactionReports"            
	   }	
		$.ajax({
			type : "POST",
			url : "daywiseTransactionReportsAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result !=null && result.surveyTransactionVOList != null && result.surveyTransactionVOList.length >0)
			{
				buildDadyWiseTransactionReport(result);	
			}
			else
			{
				$('#daywiseReportDiv').html(' No Data Available...');
			}
			
		});
			
	}
	
	function buildDadyWiseTransactionReport(result)
	{
		var str ='';
		str+='<h4 align="center"> Day wise Transaction Report </h4>';
		str +='<table class="table " id="daywiseReportTab">';
		str +='<thead>';
		str +='<tr>';
		str +='<th> Date </th>';
		str +='<th> Field Staff </th>';
		str +='<th> Submitted Records </th>';
		str +='<th> Field Staff OTP Payment Recieved </th>';
		str +='<th> Amount Recieved  </th>';
		str +='</tr>';
		str +='</thead>';
		str +='<tbody>';
		
		for(var i in result.surveyTransactionVOList)
		{
			str +='<tr>';
			str +='<td>'+result.surveyTransactionVOList[i].surveyDate+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].teamSize+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].submittedCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].otpRequestCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].depositedAmount+'</td>';
			str +='</tr>';		
		}
		str +='</tbody>';
		str +='</table>';
		$('#daywiseReportDiv').html(str);
		$('#daywiseReportTab').dataTable({});
	}
	</script>

  </body>
</html>