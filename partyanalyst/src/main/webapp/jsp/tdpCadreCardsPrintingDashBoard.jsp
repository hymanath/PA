<!DOCTYPE html>
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
		
		.demo-flot-chart {width: 100%;  height: 250px;}
		.donut-label { font-size: 12px; color: #FFF; background: rgba(0, 0, 0, 0.5); text-align: center;  padding: 1px;line-height:15px;}
	</style>
	 
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <!-- Include all compiled plugins (below), or include individual files as needed -->
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="js/cardsDashBoard/js2.3.2/bootstrap.min.js"></script>
	  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<!---Bootstrap Swich-->
	 <script src="js/cardsDashBoard/js2.3.2/bootstrap-switch.js"></script>
	
  </head>
  <body>
	<div class="container">
		<div class="row">
			<div class="span12 widget text-center">
				<h2>PRINTING STATUS REPORT DASHBOARD</h2>
			</div>
		</div>
		<div id="cadrePopupDiv" style="display:none;"><div id="cadrePopupInnerDiv"></div></div>
		<div class="row">
			<div class="span4 ">
				<div class="widget">
					<div class="widget-heading">
						<h4>State Wise Cards Prints Overview</h4>
					</div>
					<div class="widget-body">
							<input type="checkbox" name="my-checkbox" checked>			
						<div class="demo-flot-chart" id="demo-donut-chart"></div>
					</div>
					
				</div>
			</div>
			
			<div class="span8">
				<div class="widget">
					<div class="widget-heading">
						<h4>Constituency Wise Cards Prints Overview <input class="pull-right input-medium" placeholder="Enter Constituency Name" type="search" style="margin-top:-5px;"></h4>
					</div>
					<div class="widget-body scrollable_div" style="width:97%; height:280px;overflow:auto;">
						<div id="accordion2" class="accordion">
							<div class="accordion-group">
							  <div class="accordion-heading">
			<s:if test="%{zebraPrintDetailsVO.zebraPrintDetailsVOList != null && zebraPrintDetailsVO.zebraPrintDetailsVOList.size() > 0}">
			<c:forEach var="cadreVO" items="${zebraPrintDetailsVO.zebraPrintDetailsVOList}" varStatus="commentLoop">

			<a href="#collapseOne" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
				 ${cadreVO.name} <span id="mini-pie-chart1" class="pull-right mini-pie-chart"></span>
			</a>
								
			</c:forEach>
			</s:if>
								<a href="#collapseOne" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
								  Achampet <span id="mini-pie-chart1" class="pull-right mini-pie-chart"></span>
								</a>
							  </div>
							  <div class="accordion-body collapse" id="collapseOne" style="height: 0px;">
								<div class="accordion-inner">
									<table class="table table-striped table-bordered table-condensed border-radius-0">
										<thead>
											<tr>
												<th>
													Total Registered
												</th>
												<th>
												Sent to print 
												</th>
												<th>
													Cards Printed 
												</th>
												<th>
													Printing Error
												</th>
												<th>
													Pending Cards <small> (For Print)</small>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>2000</td>
												<td>1900</td>
												<td>1500</td>
												<td><a onclick="getCadreDetails('ERROR');">100</a></td>
												<td>400 <small>(100 Errors)</small> /1900</td>
											</tr>
										</tbody>
									</table>
								</div>
							  </div>
							</div>
							<div class="accordion-group">
							  <div class="accordion-heading">
								<a href="#collapseTwo" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
								 Achanta  <span id="mini-pie-chart2" class="pull-right mini-pie-chart"></span>
								</a>
							  </div>
							  <div class="accordion-body collapse" id="collapseTwo" style="height: 0px;">
								<div class="accordion-inner">2</div>
							  </div>
							</div>
							
							
						 </div>
					</div>
					
				</div>
			</div>
			
		</div>
		
	</div>
	
	
	
	<!--scrollator-->
	 <script src="js/cardsDashBoard/scrollator/fm.scrollator.jquery.js"></script>
	
	
	<script src="js/cardsDashBoard/assets/js/plugins/stat/flot/jquery.flot.min.js"></script>
	<script src="js/cardsDashBoard/assets/js/plugins/stat/flot/jquery.flot.resize.min.js"></script>
	<script src="js/cardsDashBoard/assets/js/plugins/stat/flot/jquery.flot.pie.min.js"></script>
	<script src="js/cardsDashBoard/assets/js/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>	
	<script src="js/cardsDashBoard/assets/js/king-chart-stat.js"></script>
	
	<script>
		//scrollator
		$('.scrollable_div').scrollator();
		$("[name='my-checkbox']").bootstrapSwitch();
		function getCadreDetails(status)
		{
			 $('#cadrePopupInnerDiv').html('');
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
			$("#cadrePopupDiv").css("display","block");
			var str='';
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
			 $('#cadrePopupInnerDiv').html(str);
			$('#cadrePopupDiv').dialog({                   
		    modal: true,
            title: "<b>Cadre Details</b>",
			width: 600,
            height: 500
     });
		}
	</script>
	
  </body>
 </html>