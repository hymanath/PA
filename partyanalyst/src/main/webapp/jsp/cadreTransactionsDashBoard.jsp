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
			<input type="text" id="fromDate" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="From Date"  readOnly="true" style="cursor: text; float: left; margin-top: 5px; width: 100px;" ></input>
				<h3 class="text-center text-uppercase">    Money Report & Transaction Details <a href="javascript:{getReportsForCadre();}" class="btn btn-success pull-right"> Get Reports </a> </h3>
				
			</div>
		</div><!-- Title Row End-->
		
		<!--  -->
		 <div> <img id="ajaxImgStyleNew" style="display:none;margin-left:10px; margin-top:10px;" src="images/icons/search.gif"/></div>
		<div id="basicTransactionDiv">
		
		<div class="row-fluid " id="PreviousmembersCount">
			<div class="span6 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>
								<td><h2>${surveyTransactionVO.recordsCount}</h2><p> Total Live Records </p></td>
								<td><h2>${surveyTransactionVO.weekCount}</h2><p> This Week Records </p></td>
								<td><h2>${surveyTransactionVO.yesterDayCount}</h2><p> Today Live Records </p></td>
								
							</tr>
							
						</tbody>
					</table>
			</div>
			
			<div class="span6 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p> Team Count <small> (Records Submitted)</small></p></td><td  style="width:30%;"><h2>${surveyTransactionVO.teamSize}</h2></td></tr>
								<td style="width:70%;"><p> Absent Team Count<small> (Records Not Submitted)</small></p></td><td  style="width:30%;"><h2>${surveyTransactionVO.idleTeamSize}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
		</div><!--  -->
		
		<div class="row-fluid ">			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p> OTP Requested Records Count </p></td><td  style="width:30%;"><h2>${surveyTransactionVO.otpRequestCount}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p> OTP Transaction Completed Records Count </p></td><td  style="width:30%;"><h2>${surveyTransactionVO.otpConfirmCount}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
							<tr>	
								<td style="width:70%;"><p> OTP Transaction Pending Records Count </p></td><td  style="width:30%;"><h2>${surveyTransactionVO.remainingOTPCount}</h2></td></tr>
								
						</tbody>
					</table>
			</div>
		</div>
<!--		
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
	-->	
		<div class="row-fluid " >	
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Total Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionVO.actualAmount}/-</h2></td></tr>							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Submitted Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionVO.depositedAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
			
			<div class="span4 show-grid well well-small border-radius-0 mb-10">
				<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
					<tbody>
						<tr>	
							<td style="width:70%;"><p>Pending Amount</p></td><td  style="width:30%;"><h2>${surveyTransactionVO.remainingAmount}/-</h2></td></tr>
							
					</tbody>
				</table>
			</div>
		</div>
		</div>
		<!--
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
	-->	
		
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
		$("#fromDate").datepicker({
			dateFormat: "yy-mm-dd",
			maxDate: new Date(),
			onSelect: function(date) {
				getTransactionDetailsForDate();
			}
		});
		$("#fromDate").datepicker("setDate", new Date());		
	});	
	function getReportsForCadre()
	{
		window.open('cadreTransactionReportsAction.action');
	}
	
	function getTransactionDetailsForDate()
	{
		var searchDate = $('#fromDate').val();
		$('#basicTransactionDiv').html('');
		$('#ajaxImgStyleNew').show();
		
		 var jsObj = {
				searchDate :searchDate,
				tesk:"getTransactionDetailsForDate"            
		   }
	  
			$.ajax({
				type : "POST",
				url : "getTransactionDetailsForDateAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#ajaxImgStyleNew').hide();
				if(result != null)
				{
					buildBasicTransactionReport(result);
				}
				else
				{
					
				}
			});
	}
	
	function buildBasicTransactionReport(result)
	{
		var str ='';

		str+='<div class="row-fluid " id="PreviousmembersCount">';
		str+='<div class="span6 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>';
		str+='<td><h2>'+result.recordsCount+'</h2><p> Total Live Records </p></td>';
		str+='<td><h2>'+result.weekCount+'</h2><p> This Week Records </p></td>';
		str+='<td><h2>'+result.yesterDayCount+'</h2><p> Today Live Records </p></td>';
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div class="span6 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p> Team Count <small> (Records Submitted)</small></p></td><td  style="width:30%;"><h2>'+result.teamSize+'</h2></td></tr>';
		str+='<td style="width:70%;"><p> Absent Team Count<small> (Records Not Submitted)</small></p></td><td  style="width:30%;"><h2>'+result.idleTeamSize+'</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='<div class="row-fluid ">			';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p> OTP Requested Records Count </p></td><td  style="width:30%;"><h2>'+result.otpRequestCount+'</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p> OTP Transaction Completed Records Count </p></td><td  style="width:30%;"><h2>'+result.otpConfirmCount+'</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p> OTP Transaction Pending Records Count </p></td><td  style="width:30%;"><h2>'+result.remainingOTPCount+'</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='<div class="row-fluid " >	';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p>Total Amount</p></td><td  style="width:30%;"><h2>'+result.actualAmount+'/-</h2></td></tr>';							
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p>Submitted Amount</p></td><td  style="width:30%;"><h2>'+result.depositedAmount+'/-</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div class="span4 show-grid well well-small border-radius-0 mb-10">';
		str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >';
		str+='<tbody>';
		str+='<tr>	';
		str+='<td style="width:70%;"><p>Pending Amount</p></td><td  style="width:30%;"><h2>'+result.remainingAmount+'/-</h2></td></tr>';
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		
		$('#basicTransactionDiv').html(str);
		
		$("#fromDate").datepicker({
			dateFormat: "yy-mm-dd",
			maxDate: new Date(),
			onSelect: function(date) {
				getTransactionDetailsForDate();
			}
		});
	}
	</script>

  </body>
</html>