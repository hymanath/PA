<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
  <head>
	 <!-- Bootstrap -->
    <link href="js/cardsDashBoard/css2.3.2/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="js/cardsDashBoard/css2.3.2/style.css" rel="stylesheet">
	
	<!-- bootstrap switch Styles-->
    <link href="js/cardsDashBoard/css2.3.2/bootstrap-switch.css" rel="stylesheet">	
	
	<!-- scrollator -->
	<link href="js/cardsDashBoard/scrollator/fm.scrollator.jquery.css" rel="stylesheet">	
	
	<link href="js/cardsDashBoard/assets/css/smain.css" rel="stylesheet" type="text/css" media="screen">
	<style>
		.widget{background:#f9f9f9; border:1px solid #ddd; margin-bottom:15px;}
		.widget-heading{border-bottom:1px solid #c1c1c1; background:#ddd; padding:1px 10px;}
		.widget-body{padding:10px;}
		.widget-footer{border-top:1px solid #ddd;  padding:0px 10px;}
		.widget-table{overflow:auto;}
		.border-radius-0{border-radius:0px;}
		.width-50{width:50px!important;}
		
		.pad-0-10{padding:0px 10px;}
		.margin-0{margin:0px;}
		.demo-flot-chart {width: 100%;  height: 250px;}
		.donut-label { font-size: 12px; color: #FFF; background: rgba(0, 0, 0, 0.5); text-align: center;  padding: 1px;line-height:15px;}
	</style>
	 
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="js/cardsDashBoard/js2.3.2/bootstrap.min.js"></script>
	<!---Bootstrap Swich-->
	 <script src="js/cardsDashBoard/js2.3.2/bootstrap-switch.js"></script>
	
	<script>
	var totalRegistered = '${zebraPrintDetailsVO.rowCount}';
	var pushForPrint = '${zebraPrintDetailsVO.totalPushCount}';
	var printedCount = '${zebraPrintDetailsVO.printStatusCount}';
	var errorCount = '${zebraPrintDetailsVO.errorStatusCount}';
	var pendingCount = '${zebraPrintDetailsVO.remainingCount}';
	
	var printPerc = '${zebraPrintDetailsVO.printPerc}';
	var errorPerc = '${zebraPrintDetailsVO.erroPerc}';
	var pendingPerc = '${zebraPrintDetailsVO.pendingPerc}';
	
	var dataValues = [{label: "Printed ",  data: printPerc},{label: "Errors ", data: errorPerc},{label: "Pending ", data: pendingPerc}];

	
	</script>
  </head>
  <body>
  
	<div class="container">
		<div class="row">
			<div class="span12 widget text-center">
				<h2>PRINTING STATUS REPORT DASHBOARD</h2>
			</div>
		</div>
		<div id="cadrePopupDiv"></div>
		
		<div class="row">
			<div class="span4 ">
				<div class="widget">
					<div class="widget-heading">
						<h4> Total Cards Prints Overview</h4>
					</div>
					<div class="widget-body">
						  <!---<input id="switch-size"  name="my-checkbox" type="checkbox" checked data-handle-width="136" data-on-color="success" data-off-color="success" data-size="default" data-on-text="Andhra Pradesh" data-off-text="Telangana">
						  --->	
						  <!--
							<input type="radio" name="radio1" checked class="switch-radio1" data-size="mini" data-label-text="AP & TS" onclick="getDetailsForState(0)" value="0">
							<input type="radio" name="radio1" class="switch-radio1" data-size="mini" data-label-text="AP"onclick="getDetailsForState(1)" value="1">
							<input type="radio" name="radio1" class="switch-radio1" data-size="mini" data-label-text="TS" onclick="getDetailsForState(2)" value="2">
								-->	

						
						<table class="table table-bordered" style="margin-top:20px;">
							<tbody>
								<tr>
									<th class="alert-success" > Registrations </th> <td class="alert-success"> ${zebraPrintDetailsVO.rowCount}</td>
								</tr>
								<tr>
									<th class="alert-info"> Sent to print </th><td class="alert-info"> ${zebraPrintDetailsVO.totalPushCount} </td>
								</tr>
								<tr>
									<th class="alert-info"> Printed </th><td class="alert-info"> ${zebraPrintDetailsVO.printStatusCount}</td>
								</tr>
								<tr>
									<th class="alert-info"> Errors </th><td class="alert-info">  ${zebraPrintDetailsVO.errorStatusCount}</td>
								</tr>
								<tr>
									<th class="alert-info"> Pending </th><td class="alert-info">${zebraPrintDetailsVO.remainingCount} </td>
								</tr>
							</tbody>
						</table>
						<div class="demo-flot-chart" id="demo-donut-chart"></div>
						
					</div>

				</div>
			</div>
			
			<div class="span8">
				<div class="widget">
					<div class="widget-heading">
						<h4>Constituency Wise Cards Prints Overview <select id="constituencyList"><option value="0"> All </option></select></h4>
					</div>
					<div class="widget-body scrollable_div" style="width:97%; height:515px;overflow:auto;">		
					<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg" class="" src="images/Loading-data.gif"></div>					
					<div id="accordion2" class="accordion"> </div>						
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!--scrollator-->
		 <script src="js/cardsDashBoard/scrollator/fm.scrollator.jquery.js"></script>
	
	
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.resize.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.pie.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>	
	<script src="js/cardsDashBoard/js2.3.2/king-chart-stat.js"></script>
	
	<script>
	
	$('document').ready(function(){
		
		$('#constituencyList').change(function(){
		var constituencyId = $(this).val();
		var stateTypeId = 0;
		
		 $('#accordion2').html('');
		 $('#searchDataImg').show();
		var jsObj = 
			   {
				  locationId : constituencyId,
				  searchType :"constituency",
				  stateTypeId : stateTypeId,
				  task:"totalPrintingCount"             
			   }	
				$.ajax({
					type : "POST",
					url : "getPrintDetailsInfoByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				if(result != null)
				{
				$('#searchDataImg').hide();
					buildConstituencyWiseResults(result,0);
				}
			  });
			  
		});
		
	});
		//scrollator
		$('.scrollable_div').scrollator();
		//$("[name='my-checkbox']").bootstrapSwitch();
		$("[name='radio1']").bootstrapSwitch();
		 function getTotalPrintingStatusCount(){
		 $('#accordion2').html('');
		 $('#searchDataImg').show();
		var jsObj = 
			   {
				  stateTyleId : 0,
				  task:"totalPrintingCount"             
			   }	
				$.ajax({
					type : "POST",
					url : "getPrintingStatusDetialsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				if(result != null)
				{
				$('#searchDataImg').hide();
					buildConstituencyWiseResults(result,1);
				}
			  });
	  }

	  function buildConstituencyWiseResults(result,type)
	  {
		var  constiteuncyInfoArr = [];
		if(type !=0)
		{
			$('#constituencyList').find('option').remove();
			$('#constituencyList').append('<option value="0"> All </option>');			
		}
		
		
		var str='';
		for(var i in result.zebraPrintDetailsVOList)
		{		
		if(type !=0)
		{
			$('#constituencyList').append('<option value="'+result.zebraPrintDetailsVOList[i].id+'">'+result.zebraPrintDetailsVOList[i].name+'</option>');			
		}
		
			str+=' <div class="accordion-group">';
			str+=' <div class="accordion-heading">';
			str+=' <a href="#collapse'+i+'" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">'+result.zebraPrintDetailsVOList[i].name+'  <span id="mini-pie-chart'+i+'" class="pull-right mini-pie-chart"></span></a>';
			str+=' </div>';
			str+=' <div class="accordion-body collapse" id="collapse'+i+'" style="height: 0px;">';
			str+=' <div class="accordion-inner">';
			str+=' <table class="table table-bordered">';
				str+=' <thead>';
				str+=' <tr>';
				str+=' <th class="alert-success"> Total Registered  </th>';
				str+=' <th class="alert-info" > Sent to print  </th>';
				str+=' <th class="alert-info" > Cards Printed </th>';
				str+=' <th class="alert-info" > Printing Error  </th>';
				str+=' <th class="alert-info"  style="width:200px"> Pending Cards  </th>';
				str+=' </tr>';
				str+=' </thead>';
				str+=' <tbody>';
				str+=' <tr>';

				str+=' <td>'+result.zebraPrintDetailsVOList[i].rowCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].printStatusCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].remainingCount+'</td>';
				
				
				str+=') </td>';
				str+=' </tr>';
				str+=' </tbody>';
				str+=' </table>';
			str+='</div>';
			str+=' </div>';
			str+=' </div>';
			var details = [result.zebraPrintDetailsVOList[i].printStatusCount,result.zebraPrintDetailsVOList[i].remainingCount,result.zebraPrintDetailsVOList[i].errorStatusCount];
			constiteuncyInfoArr.push(details);
		}

		$('#accordion2').html(str);
		
		if( $('.mini-pie-chart').length > 0 ) 
		{
			var visitData = constiteuncyInfoArr;
			var params = {
				type: "pie",
				sliceColors: ["#0B3B0B", "#B18904", "#610B21"],
			}

			for(var k in result.zebraPrintDetailsVOList)
			{
				$('#mini-pie-chart'+k+'').sparkline(visitData[k], params);
			}
		}
	  }
		getTotalPrintingStatusCount();
		function getCadreDetails(status)
		{
			 //$('#cadrePopupInnerDiv').html('');
		var jObj = {
		Id:2,
		status:status,
		task:"cadreInfo"
		}
		 $.ajax({
          type:'GET',
          url: 'getCadreDetailsByStatusAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildPopup(result);
		});
		}

		function buildPopup(result)
		{
			//$("#cadrePopupDiv").css("display","block");
			var str='';
			str+='<div id="myModal" class="modal" aria-labelledby="myModalLabel" >';
			 str+='<div class="modal-header">';
			str+='<button type="button" class="close" data-dismiss="modal" onclick="closePopup();">×</button>';
			str+='<h3 id="myModalLabel">Cadre Details</h3>';
			str+='</div>';
			str+='<div class="modal-body">';
			str+='<table  class="table table-bordered table-striped" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;text-align:center;">';
			 str +='<tr>';
			  str +='<th>Name</th>';
			  str +='<th>Relative Name</th>';
			  str +='<th>Mobile</th>';
			  str +='<th>MembershipNo</th>';
			  str +='<th>Status</th>';
			  str +='</tr>';
			  for(var i in result.zebraPrintDetailsVOList)
			  {
				str +='<tr>';
				str +='<td>'+result.zebraPrintDetailsVOList[i].name+'</td>';
				str +='<td>'+result.zebraPrintDetailsVOList[i].relativeName+'</td>';
				str +='<td>'+result.zebraPrintDetailsVOList[i].mobileNo+'</td>';
				str +='<td>'+result.zebraPrintDetailsVOList[i].membershipNo+'</td>';
				str +='<td>'+result.zebraPrintDetailsVOList[i].printStatus+'</td>';
				str +='</tr>'; 
			  }
			  str+='</table>';
			str+='</table>';
			str+='</div></div>';
			 $('#cadrePopupDiv').html(str);
			 $('#myModal').modal('hide')
			$('#myModal').modal('show');
		}
		function closePopup()
		{
		    $('#myModal').modal('hide');
		}
	</script>
	
  </body>
 </html>