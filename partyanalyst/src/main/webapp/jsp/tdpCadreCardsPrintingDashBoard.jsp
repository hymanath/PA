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
		.widget{background:#f9f9f9; border:1px solid #ddd; margin-bottom:15px;padding:0px !important;}
		.widget-heading{border-bottom:1px solid #c1c1c1; background:#ddd; padding:1px 10px;}
		.widget-body{padding:10px;}
		.widget-footer{border-top:1px solid #ddd;  padding:0px 10px;}
		.widget-table{overflow:auto;}
		.border-radius-0{border-radius:0px;}
		.width-50{width:50px!important;}
		
		.pad-0{padding:0px !important;}
		.pad-0-10{padding:0px 10px;}
		.margin-0{margin:0px;}
		.demo-flot-chart {width: 100%;  height: 250px;}
		.donut-label { font-size: 12px; color: #FFF; background: rgba(0, 0, 0, 0.5); text-align: center;  padding: 1px;line-height:15px;}
		
		.text-uppercase{text-transform: uppercase;}
		.f-bold{ font-weight: bold;;}
		.widget h2 {border-bottom: none !important;}
		.height-auto{height:auto !important;}
		.height-0{height:0px !important;}
		
	</style>
	 
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
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
	var sentPerc = '${zebraPrintDetailsVO.sentPerc}';
	var dataValues = [{label: "Printed ",  data: printPerc},{label: "Errors ", data: errorPerc},{label: "Pending ", data: pendingPerc}];

	
	</script>
	
	
  </head>
  <body>
  	
	<div class="container">
		<div class="row">
			<div class="span12 widget text-center">
				<h2 style="padding:15px 20px;font-size: 20px;">PRINTING STATUS REPORT DASHBOARD</h2>
			</div>
		</div>
		<!-------------->
		<div id="cadrePopupDiv"></div>
		
		<div class="row">
			<div class="span4 ">
				<div class="widget">
					<div class="widget-heading">
						<h4>State Wise Cards Prints Overview</h4>
					</div>
					<div class="widget-body"  style="height:483px">
						
						<!--<input type="radio" name="radio1" checked value="ALL" class="radio1" data-size="mini" data-label-text="AP & TS">
						<input type="radio" name="radio1" value="AP" class="radio1"  data-size="mini" data-label-text="AP">
						<input type="radio" name="radio1" value="TS" class="radio1" data-size="mini" data-label-text="TS">-->
						
						<c:if test="${sessionScope.USER.isAdmin == 'true'}">
						<input style="margin-top:-2px;margin-left:60px;" type ="radio"  class="stType" name="stateType" value="both" checked="checked">&nbsp;AP & TS&nbsp;&nbsp;&nbsp;</input>
						<input type ="radio" style="margin-top:-2px;" class="stType" name="stateType" value="ap" >&nbsp;AP&nbsp;&nbsp;&nbsp;</input>
						<input style="margin-top:-2px;" type ="radio" class="stType" name="stateType" value="ts">&nbsp;TS </h4></input>
						</c:if>
						<div align="center"><img style="width:70px;height:60px;display:none;" id="mainImg" class="" src="images/Loading-data.gif"></div>
						<div id="mainChartDiv">					
							
						<div class="demo-flot-chart" id="demo-donut-chart"></div>
						<table class="table table-bordered" style="margin-top:10px;">
							<tbody>
								<tr>
									<th class="alert-success" > Registrations </th> <td class="alert-success"> ${zebraPrintDetailsVO.rowCount}<span class="pull-right label label-info">${zebraPrintDetailsVO.registeredPerc} %</span></td>
								</tr>
								<tr>
									<th class="alert-info"> Sent to print </th><td class="alert-info"> ${zebraPrintDetailsVO.totalPushCount} <span class="pull-right label label-info">${zebraPrintDetailsVO.sentPerc} %</span></td>
								</tr>
								<tr>
									<th class="alert-info"> Printed </th><td class="alert-info"> ${zebraPrintDetailsVO.printStatusCount}<span class="pull-right label label-info">${zebraPrintDetailsVO.printPerc} %</span></td>
								</tr>
								<tr>
									<th class="alert-info"> Errors </th><td class="alert-info">  ${zebraPrintDetailsVO.errorStatusCount}<span class="pull-right label label-info">${zebraPrintDetailsVO.erroPerc} %</span></td>
								</tr>
								<tr>
									<th class="alert-info"> Pending </th><td class="alert-info">${zebraPrintDetailsVO.remainingCount}<span class="pull-right label label-info">${zebraPrintDetailsVO.pendingPerc} %</span> </td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					
				</div>
			</div>
			
			<div class="span8">
				<div class="widget">
					<div class="widget-heading">
						<h4>Constituency Wise Cards Prints Overview <select class="pull-right input-medium" id="constituencyList" style="margin-top:-5px;" onChange="searchByName(this.id,'CONSTITUENCY')"><option value="0"> All </option></select></h4>
					</div>
					<div class="widget-body scrollable_div" style="width:97%; height:481px;overflow:auto;">		
						<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg" class="" src="images/Loading-data.gif"></div>					
						<div id="accordion2" class="accordion"> </div>						
					</div>	
				</div>
					
			</div>
		</div>	
		 
		<div class="row">
				<!--<div class="span4 ">
					<div class="widget">
						<div class="widget-heading">
							<h5>Day Wise Cards Prints Overview <span class="pull-right btn btn-mini"><i class="icon-calendar"></i></span></h5>
						</div>
						<div class="widget-body">
							
						</div>
					</div>
				</div>-->
				
				<div class="span6 ">
					<div class="widget">
						<div class="widget-heading">
							<h5>District Wise Cards Prints Overview<select class="pull-right input-medium" id="districtList" style="margin-top:-5px;" onChange="searchByName(this.id,'DISTRICT')"><option value="0"> All </option></select></h5>
						</div>						
						<div class="widget-body" style="width:95%;height:230px;overflow:auto;">
							<div align="center"><img style="width:70px;height:60px;display:none;" id="ajaxImg" src="images/Loading-data.gif"></div>											
							<div id="districtAccordion" class="accordion"></div>
						</div>
					</div>
				</div>
				
				<div class="span6 ">
					<div class="widget">
						<div class="widget-heading">
							<h5>Parliament Wise Cards Prints Overview<select class="pull-right input-medium" id="parlConstiList" style="margin-top:-5px;" onChange="searchByName(this.id,'MP')"><option value="0"> All </option></select></h5>
						</div>
						<div class="widget-body"  style="width:95%;height:230px;overflow:auto;">						
								<div align="center"><img style="width:70px;height:60px;display:none;" id="ajaxImg1" src="images/Loading-data.gif"></div>											
								<div id="parliamentAccordion" class="accordion"></div>					
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>

	<!--scrollator-->
	<script src="js/cardsDashBoard/js2.3.2/Chart.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/Chart.min.js"></script>
	<script src="js/cardsDashBoard/scrollator/fm.scrollator.jquery.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.resize.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/stat/flot/jquery.flot.pie.min.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>	
	<script src="js/cardsDashBoard/js2.3.2/king-chart-stat.js"></script>

	<script>
	//scrollator
	//$('.scrollable_div').scrollator();
	//$("[name='my-checkbox']").bootstrapSwitch();
	//$("[name='radio1']").bootstrapSwitch();
	
			
    function getTotalPrintingStatusCount(type,searchTypeId){
		 $('#accordion2').html('');
		 $('#districtAccordion').html('');
		 $('#parliamentAccordion').html('');
		 $('#searchDataImg').show();
		 $('#ajaxImg').show();	
		 $('#ajaxImg1').show();
		 if(searchTypeId == 1){
			$('#mainChartDiv').html('');
			$('#mainImg').show();						
		}
		 var stateTypeId = $('input:radio[name=stateType]:checked').val();
		
		 var stateId = 0;
		 if(stateTypeId == "both")
			stateId = 0;
		else if(stateTypeId == "ap")
			stateId = 1;
		else if(stateTypeId == "ts")
			stateId = 2;
		var jsObj = 
		{
			  stateTyleId : stateId,
			  type : type,
			  task:"totalPrintingCount"             
		}	
		$.ajax({
			type : "POST",
			url : "getPrintingStatusDetialsAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		if(result != null)
		{
			if(type == "CONSTITUENCY"){
				$('#searchDataImg').hide();			
				buildConstituencyWiseResults(result,1);
			}else if(type == "DISTRICT"){
				$('#ajaxImg').hide();			
				buildDistrictWiseResults(result,1);
				if(searchTypeId == 1)
				{
					$('#mainImg').hide();		
					buildMainChart(result);
				}
			}else if(type == "MP"){
				$('#ajaxImg1').hide();			
				buildParliamentWiseResults(result,1);
			}
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
	if(type !=0){
		$('#constituencyList').append('<option value="'+result.zebraPrintDetailsVOList[i].id+'">'+result.zebraPrintDetailsVOList[i].name+'</option>');			
	}	
		str+=' <div class="accordion-group">';
		str+=' <div class="accordion-heading">';
		str+=' <a onclick="hideConstiDiv();toggleDiv(\'collapse'+i+'\');" href="#collapse'+i+'" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">'+result.zebraPrintDetailsVOList[i].name+'  <span id="mini-pie-chart'+i+'" class="pull-right mini-pie-chart"></span></a>';
		str+=' </div>';
		str+=' <div class="accordion-body collapse toggleCls" id="collapse'+i+'" style="height: 0px;">';
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
			if(result.zebraPrintDetailsVOList[i].totalPushCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'CONSTITUENCY\',\'SENT\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCY'+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCYImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].totalPushCount+'</a></td>';
			}
			else
		    str+=' <td>'+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].printStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'CONSTITUENCY\',\'PRINTED\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCY'+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCYImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].printStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].printStatusCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].errorStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'CONSTITUENCY\',\'ERROR\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCY'+result.zebraPrintDetailsVOList[i].id+'\',\'CONSTITUENCYImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</td>';
			str+=' <td>'+result.zebraPrintDetailsVOList[i].remainingCount+'</td>';

			str+=' </tr>';
			str+=' </tbody>';
		str+=' </table>';
		str+='<div id="CONSTITUENCYImg'+result.zebraPrintDetailsVOList[i].id+'" style="display:none;"><img style="width:70px;height:60px;"  src="images/Loading-data.gif"></div>';
		str+='<div id="CONSTITUENCY'+result.zebraPrintDetailsVOList[i].id+'" class="ConstiremoveCls"></div>';
		str+='</div>';
		str+=' </div>';
		str+=' </div>';
		var details = [result.zebraPrintDetailsVOList[i].printStatusCount,result.zebraPrintDetailsVOList[i].remainingCount,result.zebraPrintDetailsVOList[i].errorStatusCount];
		constiteuncyInfoArr.push(details);
	}

	$('#accordion2').html(str);
	
	if( $('.mini-pie-chart').length > 0 ) {
		var visitData = constiteuncyInfoArr;
		var params = {
			type: "pie",
			 sliceColors: ["#0B3B0B", "#B18904", "#610B21"],

		}
		for(var k in result.zebraPrintDetailsVOList){
			$('#mini-pie-chart'+k+'').sparkline(visitData[k], params);
		}
	}
  }
  getTotalPrintingStatusCount("CONSTITUENCY",0);
  getTotalPrintingStatusCount("DISTRICT",0);
  getTotalPrintingStatusCount("MP",0);
  function getCadreDetails(status)
  {
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
	
	function buildDistrictWiseResults(result,type)
	{
		var  districtInfoArr = [];
		if(type !=0)
		{
			$('#districtList').find('option').remove();
			$('#districtList').append('<option value="0"> All </option>');			
		}

		var str='';
		for(var i in result.zebraPrintDetailsVOList)
		{		
		if(type !=0){
			$('#districtList').append('<option value="'+result.zebraPrintDetailsVOList[i].id+'">'+result.zebraPrintDetailsVOList[i].name+'</option>');			
		}
			str+=' <div class="accordion-group">';
			str+=' <div class="accordion-heading">';
			str+=' <a onclick="hideDistDiv();toggleDiv(\'collapseDistrict'+i+'\');" href="#collapseDistrict'+i+'" data-parent="#districtAccordion" data-toggle="collapse" class="accordion-toggle collapsed">'+result.zebraPrintDetailsVOList[i].name+'  <span id="mini-pie-chart-district'+i+'" class="pull-right mini-pie-chart-district"></span></a>';
			str+=' </div>';
			str+=' <div class="accordion-body collapse toggleCls" id="collapseDistrict'+i+'" style="height: 0px;">';
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
				if(result.zebraPrintDetailsVOList[i].totalPushCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'DISTRICT\',\'SENT\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICT'+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICTImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].totalPushCount+'</a></td>';
			}
			else
		    str+=' <td>'+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].printStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'DISTRICT\',\'PRINTED\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICT'+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICTImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].printStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].printStatusCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].errorStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'DISTRICT\',\'ERROR\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICT'+result.zebraPrintDetailsVOList[i].id+'\',\'DISTRICTImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].remainingCount+'</td>';

				str+=' </tr>';
				str+=' </tbody>';
			str+=' </table>';
			str+='<div id="DISTRICTImg'+result.zebraPrintDetailsVOList[i].id+'"  style="display:none;"><img style="width:70px;height:60px;"  src="images/Loading-data.gif"></div>';
			str+='<div id="DISTRICT'+result.zebraPrintDetailsVOList[i].id+'" class="DistremoveCls"></div>';
			str+='</div>';
			str+=' </div>';
			str+=' </div>';
			var details = [result.zebraPrintDetailsVOList[i].printStatusCount,result.zebraPrintDetailsVOList[i].remainingCount,result.zebraPrintDetailsVOList[i].errorStatusCount];
			districtInfoArr.push(details);
		}

		$('#districtAccordion').html(str);
		
		if( $('.mini-pie-chart-district').length > 0 ) {
			var visitData = districtInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904", "#610B21"],

			}
			for(var k in result.zebraPrintDetailsVOList){
				$('#mini-pie-chart-district'+k+'').sparkline(visitData[k], params);
			}
		}
  }
  var parliamentInfoArr = [];
  function buildParliamentWiseResults(result,type)
  {
		var  districtInfoArr = [];
		if(type !=0)
		{
			$('#parlConstiList').find('option').remove();
			$('#parlConstiList').append('<option value="0"> All </option>');			
		}

		var str='';
		for(var i in result.zebraPrintDetailsVOList)
		{		
		if(type !=0){
			$('#parlConstiList').append('<option value="'+result.zebraPrintDetailsVOList[i].id+'">'+result.zebraPrintDetailsVOList[i].name+'</option>');			
		}
			str+=' <div class="accordion-group">';
			str+=' <div class="accordion-heading">';
			str+=' <a onclick="hideParlDiv();toggleDiv(\'collapseParliament'+i+'\');" href="#collapseParliament'+i+'" data-parent="#parliamentAccordion" data-toggle="collapse" class="accordion-toggle collapsed">'+result.zebraPrintDetailsVOList[i].name+'  <span id="mini-pie-chart-parliament'+i+'" class="pull-right mini-pie-chart-parliament"></span></a>';
			str+=' </div>';
			str+=' <div class="accordion-body collapse toggleCls" id="collapseParliament'+i+'" style="height: 0px;">';
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
				if(result.zebraPrintDetailsVOList[i].totalPushCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'Parliament\',\'SENT\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'Parliament'+result.zebraPrintDetailsVOList[i].id+'\',\'ParliamentImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].totalPushCount+'</a></td>';
			}
			else
		    str+=' <td>'+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].printStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'Parliament\',\'PRINTED\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'Parliament'+result.zebraPrintDetailsVOList[i].id+'\',\'ParliamentImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].printStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].printStatusCount+'</td>';
			/*if(result.zebraPrintDetailsVOList[i].errorStatusCount > 0)
			{
			str+=' <td><a style="cursor:pointer" onclick="getDayWiseCardPrintedCount(\'Parliament\',\'ERROR\',\''+result.zebraPrintDetailsVOList[i].id+'\',\'Parliament'+result.zebraPrintDetailsVOList[i].id+'\',\'ParliamentImg'+result.zebraPrintDetailsVOList[i].id+'\');">'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</a></td>';
			}
			else*/
			str+=' <td>'+result.zebraPrintDetailsVOList[i].errorStatusCount+'</td>';
				str+=' <td>'+result.zebraPrintDetailsVOList[i].remainingCount+'</td>';

				str+=' </tr>';
				str+=' </tbody>';
			str+=' </table>';
			str+='<div id="ParliamentImg'+result.zebraPrintDetailsVOList[i].id+'"  style="display:none;"><img style="width:70px;height:60px;"  src="images/Loading-data.gif"></div>';
			str+='<div id="Parliament'+result.zebraPrintDetailsVOList[i].id+'" class="ParlremoveCls"></div>';
			str+='</div>';
			str+=' </div>';
			str+=' </div>';
			var details = [result.zebraPrintDetailsVOList[i].printStatusCount,result.zebraPrintDetailsVOList[i].remainingCount,result.zebraPrintDetailsVOList[i].errorStatusCount];
			parliamentInfoArr.push(details);
		}

		$('#parliamentAccordion').html(str);
		
		if( $('.mini-pie-chart-parliament').length > 0 ) {
			var visitData = parliamentInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904", "#610B21"],

			}
			for(var k in result.zebraPrintDetailsVOList){
				$('#mini-pie-chart-parliament'+k+'').sparkline(visitData[k], params);
			}
		}
  }
	function searchByName(id,loctype){
	
		var locId = $('#'+id).val();		
		 var stateTypeId = $('input:radio[name=stateType]:checked').val();
		 var stateId = 0;
		 if(stateTypeId == "both")
			stateId = 0;
		else if(stateTypeId == "ap")
			stateId = 1;
		else if(stateTypeId == "ts")
			stateId = 2;
		if(loctype == "CONSTITUENCY"){
		$('#accordion2').html('');
		$('#searchDataImg').show();
		}else if(loctype == "DISTRICT"){
		$('#districtAccordion').html('');
		$('#ajaxImg').show();
		}else if(loctype == "MP"){
		$('#parliamentAccordion').html('');
		$('#ajaxImg1').show();
		}
		
		var jsObj = 
			   {
				  locationId : locId,
				  searchType :loctype,
				  stateTypeId : stateId,
				  task:"totalPrintingCount"             
			   }	
				$.ajax({
					type : "POST",
					url : "getPrintDetailsInfoByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				if(result != null)
				{
					if(loctype == "CONSTITUENCY"){
					$('#searchDataImg').hide();
					buildConstituencyWiseResults(result,0);
					}else if(loctype == "DISTRICT"){
					$('#ajaxImg').hide();
					buildDistrictWiseResults(result,0);
					}else if(loctype == "MP"){
					$('#ajaxImg1').hide();
					buildParliamentWiseResults(result,0);
					}
				}
			  });			  	
	}
	
	$(".stType").change(function(){		
		getTotalPrintingStatusCount("CONSTITUENCY",1);
		getTotalPrintingStatusCount("DISTRICT",1);
		getTotalPrintingStatusCount("MP",1);	
   });
   
   function buildMainChart(result){
     var mainArr=[];
		$("#mainChartDiv").html("");
		
		var str="";
	
		str+='<canvas id="myChart" style="margin-bottom: 28px; height: 175px; width: 339px; margin-left: -61px; margin-top: 29px;" height="190" width="380"></canvas>';
		str+='<table class="table table-bordered" style="margin-top:10px;">';
		str+=' <tbody>';
		str+='<tr>';
		str+='<th class="alert-success">Registrations  </th><td class="alert-success">'+result.rowCount+'<span class="pull-right label label-info">'+result.registeredPerc+'%</span></td>';		
		str+=' </tr>';
		str+='<tr>';
		str+=' <th class="alert-info" > Sent to print  </th><td class="alert-info">'+result.totalPushCount+'<span class="pull-right label label-info">'+result.sentPerc+'%</span></td>';
		str+=' </tr>';
		str+=' <tr>';
		str+=' <th class="alert-info" > Printed </th><td class="alert-info">'+result.printStatusCount+'<span class="pull-right label label-info">'+result.printPerc+'%</span></td>';
		str+=' </tr>';
		str+=' <tr>';
		str+=' <th class="alert-info" > Errors </th><td class="alert-info">'+result.errorStatusCount+'<span class="pull-right label label-info">'+result.erroPerc+'%</span></td>';
		str+=' </tr>';
		str+=' <tr>';
		str+=' <th class="alert-info" > Pending </th><td class="alert-info">'+result.remainingCount+'<span class="pull-right label label-info">'+result.pendingPerc+'%</span></td>';	
		str+=' </tr>';
		str+=' </tbody>';
		str+=' </table>';
		
		$("#mainChartDiv").html(str);
	
		options = {
		
        animateRotate : false,
        animateScale : false,
			   
		 onAnimationProgress: function()
        {
            this.showTooltip(this.segments, true);			
        },
        tooltipEvents: [],
		tooltipYPadding:2,
		tooltipXPadding: 2,
		tooltipXOffset: 1000,
		tooltipYOffset: 1000,   
		tooltipFillColor: "rgba(0,0,0,0.5)",
		tooltipCaretSize: 0,
		tooltipCornerRadius:0,
		tooltipFontSize: 12,
		tooltipFontStyle: "normal",
        showTooltips: true
  
        };
		
		var data = [
		{
        value: result.pendingPerc,
        color:"#B18904",
        highlight: "#D7C482",
        label: "Pending",
		labelFontSize: '12'
		},
		{
        value: result.erroPerc,
        color: "#610B21",
        highlight: "#FF5A5E",
        label: "Errors",
		labelFontSize: '12'
		},
		{
        value: result.printPerc,
        color: "#0B3B0B",
        highlight: "#859D85",
        label: "printed",
		labelFontSize: '12'
		}
		
	]

	  ctx = $("#myChart").get(0).getContext("2d");
	  myNewChart = new Chart(ctx).Doughnut(data,options);
		
   }
  	function getDayWiseCardPrintedCount(type,status,id,divId,processId)
		{
			
			$("#"+processId).show();
			$("#"+divId).html('');
		var jObj = {
		Id:id,
		status:status,
		type:type,
		task:"dayWiseCount"
		}
		 $.ajax({
          type:'GET',
          url: 'getDayWiseCardPrintedCountAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
			  
			$("#"+processId).hide();
		buildDayWiseTable(result,divId,status);
		});
		}
		function buildDayWiseTable(result,divId,status)
		{
			var str ='';
			str+='<table class="table table-striped table-bordered table-condensed border-radius-0">';
			str+='<thead>';
			str+='<tr class="">';
			str+='<th colspan="5" class="alert-success border-radius-0 text-center"><center>Day Wise Card Printed Status</center></th>';												
			str+='</tr>';
			str+='<tr class="alert-info">';
			str+='<th>Batch NO</th>';
			if(status =='SENT')
			{
				str+='<th>Sent to Print</th>';
				str+='<th>Error in Print</th>';
				str+='<th>Printed Date</th>';
				str+='<th>Printed</th>';
				
			}
			
			str+='</tr>	';											
			str+='</thead>';
			str+='<tbody>';

			for(var i=0;i<result.length;i++)
			{
				var rspan = result[i].dataPushDetailsList.length;
				if(rspan == 0)
					rspan = 1;
				str+='<tr>';
				
				if(result[i].updatedDate != '')
					str+='<td rowspan='+rspan+'>'+result[i].updatedDate+'</td>';
				else
					str+='<td rowspan='+rspan+'>-</td>';
			
				str+='<td rowspan='+rspan+'>'+result[i].totalPushCount+'</td>';
				if(result[i].errorStatusCount != null)
				str+='<td rowspan='+rspan+'>'+result[i].errorStatusCount+'</td>';
				else
				str+='<td rowspan='+rspan+'>0</td>';

				if(result[i].dataPushDetailsList != null && result[i].dataPushDetailsList.length > 0)
				{
					for(var j=0;j<result[i].dataPushDetailsList.length;j++)
					{
						if(j!=0)
							str+='<tr>';
						if(result[i].dataPushDetailsList[j].updatedDate != '')
							str+='<td>'+result[i].dataPushDetailsList[j].updatedDate+'</td>';
						else
							str+='<td>-</td>';
						str+='<td>'+result[i].dataPushDetailsList[j].printStatusCount+'</td>';
						str+='</tr>';
					}
				}
				else
				{
					str+='<td>-</td>';
					str+='<td>0</td>';
					str+='</tr>';
				}
				
				
				
			}
			str+='</tbody>';
			str+='</table>';
			$('#'+divId).html(str);
		}
		function hideConstiDiv()
		{
		$(".ConstiremoveCls").html('');
		
		}
		function hideDistDiv()
		{
		$(".DistremoveCls").html('');
		}
		function hideParlDiv()
		{
		$(".ParlremoveCls").html('');
		}
		/*var showflag=true;
		function toggleDiv(id)
		{
			alert(showflag)
			$(".toggleCls").removeClass("heightCls");
			if(showflag) {
				alert('a');
			showflag=false;
			$("#"+id).addClass("heightCls");
			}
			else {
				alert('b');
				$("#"+id).removeClass("heightCls");
				$("#"+id).addClass("heightCls1");
				showflag = true;
			}
		}*/
		function toggleDiv(id)
		{
			var height = $("#"+id).css('height').match(/\d+/);
			$(".toggleCls").removeClass("height-auto");
			if(height == 0 || $("#"+id).hasClass('height-0'))
			{
			$("#"+id).addClass("height-auto");
			$("#"+id).removeClass("height-0");
			
			}
			else
			{
				$("#"+id).removeClass("height-auto");
				$("#"+id).addClass("height-0");
				
			}
		}

  </script>	
 </body>
 </html>