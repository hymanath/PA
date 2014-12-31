<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">	
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
 <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootStrapDateRange/moment.js"></script>
<script type="text/javascript" src="js/bootStrapDateRange/daterangepicker.js"></script>

 <link rel="stylesheet" type="text/css" href="css/daterangepicker-bs2.css"/>
 <script src="js/cardsDashBoard/js2.3.2/Chart.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/Chart.min.js"></script>
<script type="text/javascript" src="js/exportexcel.js"></script>

<title>IVR Report</title>
<style>
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	
	.width35p{width:185px !important;}
	.width25p{width:125px !important;}
	.width15p{width:150px !important;}
	.border-radius-0{border-radius:0px;}
	.pad-0{padding:0px;}
	.m-0{margin:0px;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.m_top10{margin-top:10px;}
	.m_top20{margin-top:20px;}
	.Constituency-name-nav>li{padding:0px !important;}
	.Constituency-name-nav>li a{width:270px;height:80px;color:#333333;display:block; background:#eee;padding:10px !important; border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 5px 0px 0px 0px;text-decoration:none; font-size:14px; display: inline-table;}
	.Constituency-name-nav>li a:hover{color:#333333; background:#fff !important; border:1px solid #ffcc00;box-shadow:0px 20px 30px #222; background-color:rgb(204,204,204);}
	.accordion-group {border: medium none;}
	.table-condensed td{padding:0px 10px; line-height: 14px;}
	.dataTables_filter label{margin-left: 20px !important;
    margin-top: 10px !important; }
	.dataTables_length select {width:100px !important;margin-top:10px !important;}
	.dataTables_filter input{width:100px !important;}
	  #slider{height:0.4em !important;}
	  .ui-slider .ui-slider-handle {width:0.5em !important;}
	.rangeSliderDiv{
	  margin-top: 10px;
	}
	.prev ,.next{
    background: none !important;
	}
	.daterangepicker .calendar-date {
	 margin-right: -10px !important;
	 }
.daterangepicker.opensleft .calendar {
	margin-left: 10px !important;
	}
	.table-condensed th, .table-condensed td {
    padding: 4px 5px;
}
	</style>
</head>

<body>
	<div class="container ">	
	<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-0 ">
				<h3 class="text-center text-uppercase">IVR  STATUS REPORTS DASHBOARD</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Filters Row -->
		<div id="fadeInDown" class="row-fluid">
			<div class="span12 well well-small  border-radius-0 mb-10 ">
				
		<ul class="inline" style="margin-bottom: 0px;"><li>
		<input type="radio" class="radioCls" style="margin-top: -2px;" name="stateradio" value="All" checked> </li>All
			<li><input type="radio" class="radioCls"  value="AP" name="stateradio" style="margin-top: -2px;"> Andhra Pradesh  </li>
			
			<li><input type="radio" class="radioCls"  value="TS" name="stateradio" style="margin-top: -2px;"> Telangana  </li>
			
			</ul>
				<!-----date picker----->
				<div style="background: none repeat scroll 0% 0% rgb(255, 255, 255); cursor: pointer; padding: 5px 10px; border: 1px solid rgb(204, 204, 204); margin-top: -25px;" class="pull-right" id="daterange">
                  <i class="icon icon-calendar"></i>
                  <span id="selectedDate"></span> <b class="caret m_top10"></b>
               </div><!-----/date picker----->
			   
			</div>
		</div><!-- /Filters Row End-->
		
		<!-- Top -->
		
			
		<div class="row" style="clear:both;">
			<!-----Total In AP & TS ----->
			<div class="span12">
			
				<table class="table table-bordered border-radius-0 mb-0">
					<tr>
						<td style="width:173px;text-align:center;">
						<img style="height:18px;width:16px;margin-top:80px;margin-left:1px;" src="./images/icons/search.gif" id="ajaxImg" class="offset1"/>
						<div id="registrationTD">
						</div>
						</td>
						<td style="width:100px;" rowspan="2" id="totalIvrTD">
							
						</td>
						<td class="width25p" rowspan="2"  id="successIvrTD">
							
						</td>
						<td class="width25p" rowspan="2" style="230px !important;"  id="errorIvrTD">
							
						</td>	
							
					</tr>
					<tr>
						<td class="width25p" id="printingTD" style="text-align:center;">
							
						</td>
						
					</tr>
					
				</table>
			</div>	
			<!----- /Total In AP & TS ----->
			
		</div>	
		
		<div class="row-fluid m_top10">
			<div class="span12 well well-small border-radius-0 mb-0 ">
				<h4 class="m-0" style=" display: inline;"><span id="districtConstituencyHeading">District Wise Card Not Received Response</span></h4> 			
				
				<input type="button" id="constiDistMainDiv" onclick="getOtherLocationsInfo();" class="btn btn-medium btn-success border-radius-0 pull-right " style="margin-right: 10px; margin-top: -5px; margin-bottom: -5px;" value="Click to view Constituency Wise" />&nbsp; &nbsp; 
			</div>
			<table class="table table-bordered table-condensed border-radius-0 mb-0 "  style=" box-shadow: inset 0 0 5px 4px rgba(0,0,0,0.1);">
				<tr>
					<td><span style="width: 5px; height: 5px; background-color: #55A855; display: inline-block; padding-top: 6px; padding-right: 0px; margin-right: 5px;"></span>VERY GOOD</td>
					<td><span style="width: 5px; height: 5px; background-color: #419FBB; display: inline-block; padding-top: 6px; padding-right: 0px; margin-right: 5px;"></span>GOOD</td>
					<td><span style="width: 5px; height: 5px; background-color: #0B82BE; display: inline-block; padding-top: 6px; padding-right: 0px; margin-right: 5px;"></span>OK</td>
					<td><span style="width: 5px; height: 5px; background-color: #FAAE43; display: inline-block; padding-top: 6px; padding-right: 0px; margin-right: 5px;"></span>POOR</td>
					<td><span style="width: 5px; height: 5px; background-color: #BD3C37; display: inline-block; padding-top: 6px; padding-right: 0px; margin-right: 5px;"></span>VERY POOR</td>					
				</tr>
				<tr>
					<td><div id="veryGoodPercDiv" /></td>
					<td><div id="goodPercDiv" /></td>
					<td><div id="okPercDiv" /></td>
					<td><div id="poorPercDiv" /></td>
					<td><div id="veryPoorPercDiv" /></td>					
				</tr>
			</table>
			<div id="locationWiseAllPercDiv" style="max-height: 400px;overflow-y: scroll;"></div>
		</div>	
		
		<div  id="allErrorsInfoLocationWiseOuter" class="row-fluid" style="outline:6px solid #DDEFD7;margin-top:20px;">			
		  <div class="span12">
			<h4 class="text-center text-uppercase alert-success m-0 border-radius-0"> Constituency Wise Info<button onclick="closeDIV('allErrorsInfoLocationWiseOuter');" class="close" type="button" style="margin-top: 1px;">x</button></h4>
			<!--<span class="text-center well well-small m-0 border-radius-0" style="width:98%;display:inline-block;">
				<label class="radio inline">
				 View Details by 
				</label>
				<label class="radio inline">
				  <input type="radio" checked="checked" name="locationSelRdo" value="mandal" > Mandal
				</label>				
				<label class="radio inline">
				  /or/ &nbsp; &nbsp;
				</label>
				<label class="radio inline">
				  <input type="radio" name="locationSelRdo" value="panchayat"> Panchayat
				</label>
				
			</span>-->
			   <div id="allErrorsInfoLocationWise" />
		
			</div>
		</div>
		
		
	</div>
<script>

	 $(document).ready(function() {
			 dateRange();	 
	 });
	function dateRange()
	{
		 var cb = function(start, end, label) {
                    $('#daterange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
				  }

                  var optionSet1 = {
					
                    showDropdowns: true,
                  
					ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')]
                       
                    },
                    opens: 'left',
					format: 'MM/DD/YYYY',
                    separator: ' to ',
                    locale: {
                        applyLabel: 'Submit',
                        cancelLabel: 'Clear',
                        fromLabel: 'From',
                        toLabel: 'To',
                        customRangeLabel: 'Custom',
                        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
                        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                        firstDay: 1
                    }
                  };
				  $('#daterange').daterangepicker(optionSet1, cb);
				 
					 $('#daterange').on('show.daterangepicker', function() { 
					   $('#daterange span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
					getDateWiseIVRCount();
					selectedLocation ="district";
						$("#constiDistMainDiv").attr("value","Click to view Constituency Wise");
						$("#districtConstituencyHeading").html("District Wise Card Not Received Response");
						getDistrictConstiWisePerformance("district","");
						getLocationWisePerformance(232,$('input[name=stateradio]:checked').val());
						$("#allErrorsInfoLocationWiseOuter").html("");
                    });
					   

                  $('#daterange').on('hide.daterangepicker', function() { 
					   
						
					   });
				
                  $('#daterange').on('apply.daterangepicker', function(ev, picker) { 
					   
                  
					  getDateWiseIVRCount();
					  selectedLocation ="district";
						$("#constiDistMainDiv").attr("value","Click to view Constituency Wise");
						$("#districtConstituencyHeading").html("District Wise Card Not Received Response");
						getDistrictConstiWisePerformance("district","");
						getLocationWisePerformance(232,$('input[name=stateradio]:checked').val());
						$("#allErrorsInfoLocationWiseOuter").html("");
                  });
				 
                  $('#daterange').on('cancel.daterangepicker', function(ev, picker) {  });
			$("#selectedDate").html(''); 
				
	}

		$(".radioCls").click(function() {
		
			 dateRange();
			getIvrBasicCount();
			getDateWiseIVRCount();
			selectedLocation ="district";
			$("#constiDistMainDiv").attr("value","Click to view Constituency Wise");
			$("#districtConstituencyHeading").html("District Wise Card Not Received Response");
			getDistrictConstiWisePerformance("district","");
			getLocationWisePerformance(232,$('input[name=stateradio]:checked').val());
			$("#selectedDate").html('');
		});
		function getIvrCount()
		{
			
			 $("#cadreDetailsDiv").html('');
			 $("#IvrHeading").html('');
			 $("#paginationDivId").html('');
			var date = '';
			var Id = 0;
			var value =$('input:radio[name=searchType]:checked').val();
		
			if(value == 1)
			{
				date = $("#dateId").val();
				if(date == 0)
				{
				$("#errorDiv").html("Select Date").css("color","red");
				return;
				}
			}
			else
			{
				 Id = $("#constituencyId").val();
				 if(Id == 0)
				{
				$("#errorDiv").html("Select Constituency").css("color","red");;
				return;
				}
			}
			$("#errorDiv").html('');
			$("#ajaxImage").show();

			var jsObj = {	
			date:date,
			Id:Id,
			task:"count"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getcadreIvrReportAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			 $("#ajaxImage").hide();
			 $("#countDiv").show();
			 buildCount(result,Id,date);
		});
		}
		function getIvrCadreDetails(searchType,totRecords,Id,date,strIndex)
		{
			$("#ajaxImage1").show();
			$("#cadreDetailsDiv").html('');
			var jsObj = {	
			date:date,
			Id:Id,
			searchType:searchType,
			strIndex : strIndex,
			maxIndex : 50,
			task:""             
		}
			   
		$.ajax({
			type : "POST",
			url : "getcadreIvrReportAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			 $("#ajaxImage1").hide();
			 buildDetails(result,totRecords,jsObj);
		});
		}

		function buildDetails(resultList,totRecords,jsObj)
		{
		  $("#cadreDetailsDiv").show();
		var result = resultList.subList;
		var str = '';
		str+='<table class="table table-bordered">';
		str+='<thead  class="alert-success">';
		str+='<tr>';
		str+='<th>Constituency</th>';
		str+='<th>Mandal/Muncipality</th>';
		str+='<th>Panchayat</th>';
		str+='<th>Name</th>';
		str+='<th>Mobile No</th>';
		if(jsObj.date !='')
		str+='<th>CurrentStatus</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
			{
			str+='<tr>';
			str+='<td>'+result[i].constituencyName+'</td>';	
			if(result[i].locationName != '')
			str+='<td>'+result[i].locationName+'</td>';	
			else
			str+='<td>'+result[i].localbodyName+'</td>';

			if(result[i].panchayatName != '')
			str+='<td>'+result[i].panchayatName+'</td>';
			else
			str+='<td></td>';

			str+='<td>'+result[i].name+'</td>';	
			str+='<td>'+result[i].mobileNo+'</td>';	
			if(jsObj.date !='')
			str+='<td>'+result[i].currentStatus+'</td>';	
			str+='</tr>';
			}
		str+='</tbody>';
		str+='</table>';
	    $("#cadreDetailsDiv").html(str);
		var itemsCount=totRecords;
	    var maxResults=50;
	   
	     if(jsObj.strIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getIvrCadreDetails(jsObj.searchType,totRecords,jsObj.Id,jsObj.date,num);
			}
		});
		}
		}
	function buildCount(result,Id,date)
	{
		if(Id == 0)
		$("#IvrHeading").html("Date Wise IVR Status");
		else
		$("#IvrHeading").html("Constituency Wise IVR Status");
		if(result.total > 0)
		$("#totalCnt").html('<a onclick="getIvrCadreDetails(\'total\',\''+result.total+'\',\''+Id+'\',\''+date+'\',0);" style="cursor:pointer" >'+result.total+'</a>');
		else
		$("#totalCnt").html(''+result.total+'');
		if(result.responseCnt > 0)
		$("#totalreceivedCalls").html('<a onclick="getIvrCadreDetails(\'Response\',\''+result.responseCnt+'\',\''+Id+'\',\''+date+'\',0);" style="cursor:pointer" >'+result.responseCnt+'</a>');
		else
		$("#totalreceivedCalls").html(''+result.responseCnt+'');
		if(result.received > 0)
		$("#receivedCalls").html('<a onclick="getIvrCadreDetails(\'Received\',\''+result.received+'\',\''+Id+'\',\''+date+'\',0);" style="cursor:pointer" >'+result.received+'</a>');
		else
		$("#receivedCalls").html(''+result.received+'');
		if(result.notReceived > 0)
		$("#notreceivedCalls").html('<a onclick="getIvrCadreDetails(\'NotReceived\',\''+result.notReceived+'\',\''+Id+'\',\''+date+'\',0)" style="cursor:pointer" >'+result.notReceived+'</a>');
		else
			$("#notreceivedCalls").html(''+result.notReceived+'');
		if(result.notRegistered > 0)
		$("#notregisteredCalls").html('<a onclick="getIvrCadreDetails(\'NotRegistered\',\''+result.notRegistered+'\',\''+Id+'\',\''+date+'\',0)" style="cursor:pointer" >'+result.notRegistered+'</a>');
		else
		$("#notregisteredCalls").html(''+result.notRegistered+'');
	}
	function getIvrBasicCount()
	{
		$("#ajaxImg").show();
		$("#printingTD").html('');
		$("#registrationTD").html('');
		var radioVal = $('input[name=stateradio]:checked').val();
		
			var jsObj = {	
			state:radioVal,
			task:"basicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		buildIvrCount(result,radioVal);
		});
	}
	function buildIvrCount(result,state)
	{
	$("#ajaxImg").hide();
	
		var str ='';
		
		str+='<canvas id="printPercChart" style="margin-top: 20px;" width="160px"; height=" 125px"; ></canvas>';
		str+='<div style="margin-top: 8px;">';
		str+='<h4 class="m-0">'+result.count+'</h4>';
		if(state == "All")
		str+='<p class="m-0" >Members Registered in Andhra Pradesh And Telangana</p>';
		else if(state == "AP")
		str+='<p class="m-0" >Members Registered in Andhra Pradesh </p>';
		else if(state == "TS")
		str+='<p class="m-0" >Members Registered in Telangana</p>';
		str+='</div>';
		$("#registrationTD").html(str);
		var str1='';
		str1+='<canvas id="piep" style="margin-top: 20px;" height="125px" width="160px"></canvas>';
		str1+='<div  style="margin-top: 8px;">';
		str1+='<h4 class="m-0">'+result.printingCompleted+'</h4>';
		if(state == "All")
		str1+='<p class="m-0" >Cards Printed in Andhra Pradesh And Telangana</p>';
		else if(state == "AP")
		str1+='<p class="m-0" >Cards Printed in Andhra Pradesh </p>';
		else if(state == "TS")
		str1+='<p class="m-0" >Cards Printed in Telangana</p>';
		str1+='</div>';
		$("#printingTD").html(str1);
		
		
		showPieChart1(result);
	}
	function showPieChart1(result){
		var ivrReadyPerc = (result.ivrReady / result.printingCompleted * 100);
		var totalIvrPerc = (result.total / result.printingCompleted * 100);
		var printingPerc = (result.printingCompleted / result.tgCount * 100);
		var errorPerc = (result.totalError / result.tgCount * 100); //tgCount - total push count in zebra
		var pendingCount = result.tgCount - result.printingCompleted;
		var pendingPerc = (pendingCount / result.tgCount * 100);
		
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
		tooltipCaretSize: 8,
		tooltipCornerRadius:0,
		tooltipFontSize: 12,
		tooltipFontStyle: "normal",
        showTooltips: true
  
        };
		

		
		var data = [
		{
        value: ivrReadyPerc,
        color:"#D14741",
        //highlight: "#D7C482",
        label: "Send To IVR",
		labelFontSize: '8'
		},
		{
        value: totalIvrPerc,
        color: "#5CB65C",
        //highlight: "#FF5A5E",
        label: "Avail for IVR",
		labelFontSize: '8'
		},
		/*{
        value: 20,
        color: "#0B3B0B",
        //highlight: "#859D85",
        label: "printed",
		labelFontSize: '8'
		}*/
		
	]

	var data1=[ 
	{value: printingPerc,
        color:"#5CB65C",
        //highlight: "#D7C482",
        label: "Cards Printed",
		labelFontSize: '8'
		},
		{value: errorPerc,
        color:"#D14741",
        //highlight: "#D7C482",
        label: "Errors",
		labelFontSize: '8'
		},
		{value: pendingPerc,
        color:"#005580",
        //highlight: "#D7C482",
        label: "Pending",
		labelFontSize: '8'
		}
		
		]
		
		
	  ctx = $("#piep").get(0).getContext("2d");
	  myNewChart = new Chart(ctx).Doughnut(data,options);
	   ctx = $("#printPercChart").get(0).getContext("2d");
	  myNewChart = new Chart(ctx).Doughnut(data1,options);
	  
	  }
	  
	  function getDateWiseIVRCount()
	{
		$("#successIvrTD").html('');
		$("#errorIvrTD").html('');
		$("#totalIvrTD").html('');
		var radioVal = $('input[name=stateradio]:checked').val();
		var fromDate ='';
		var toDate ='';
		var selectedDate = $("#selectedDate").text().split('-');
		if(selectedDate != '')
		{
		 fromDate = selectedDate[0];
		 toDate = selectedDate[1];
		}
		var jsObj = {
			state:radioVal,
			fromdate:fromDate,
			todate:toDate,
			task:"datewiseBasicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRCountByDateAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildIvrCountByDate(result,state);
		});
	}
	

function buildIvrCountByDate(result,state)
	{
		var answerPerc = result[0].answeredPerc ;
		var errorPerc = (result[0].totalError / result[0].total * 100);
		
		var str ='';
		str+='<canvas id="answerErrorChart" style="width: 140px; height: 180px; margin-left:0px; padding-left: 29px; margin-right: -42px; margin-top: 35px;" width="140px" height="180px"></canvas>';
		str+='<div class="text-center">';
		str+='<h2 class="m-0">'+result[0].total+'</h2>';
		str+='<p>Calls IVR Total <br>Dailled</p>';
		str+='</div>';
		$("#totalIvrTD").html(str);
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
		tooltipCaretSize: 8,
		tooltipCornerRadius:0,
		tooltipFontSize: 12,
		tooltipFontStyle: "normal",
        showTooltips: true
  
        };
	  var data =[
		{
		value: answerPerc,
        color:"#5CB65C",
        //highlight: "#D7C482",
        label: "IVR Answered",
		labelFontSize: '8'
		},
			{
		value: errorPerc,
        color:"#D14741",
        //highlight: "#D7C482",
        label: "IVR Error",
		labelFontSize: '8'
		},
		
		]
		
		 ctx = $("#answerErrorChart").get(0).getContext("2d");
	     myNewChart = new Chart(ctx).Doughnut(data,options);
		buildProgressBarIVR(result);
	}
	function buildProgressBarIVR(result)
	{
	var selectedOptPerc = 0;
	if(result[0].receivedPerc == null)
		result[0].receivedPerc =0;
	if(result[0].notReceivedPerc == null)
		result[0].notReceivedPerc =0;
	if(result[0].notMemberPerc == null)
		result[0].notMemberPerc =0;
	if(result[0].wrongOptionPerc == null)
		result[0].wrongOptionPerc =0;
	if(result[0].noOptionPerc == null)
		result[0].noOptionPerc =0;
	if(result[0].otherErrorPerc == null)
		result[0].otherErrorPerc =0;
	if(result[0].noAnswerPerc== null)
		result[0].noAnswerPerc=0;
	if(result[0].userBusyPerc == null)
		result[0].userBusyPerc = 0;
	if(result[0].switchCongestionPerc == null)
		result[0].switchCongestionPerc = 0;
		if(result[0].answeredCnt > 0)
	selectedOptPerc = 100 - result[0].noOptionPerc;
	var str='';
	str+='<div class="row-fluid">';
	str+='<h4 class="m-0" style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;color:#5cb55c;">ANSWERED CALLS</h4>';
	str+='<h6 class="label" style="width: 180px; margin-left: 2px; margin-right: -55px;">Selected Any Option : '+selectedOptPerc+' %</h6>';
	str+='<ul class="unstyled">';
	str+='<li>';
	str+='<b>Cards Received <span>&nbsp;&nbsp;'+result[0].receivedPerc+'%</span></b>';
	str+='<div style="height:5px;" class="progress progress-success mb-10">';
	str+='<div class="bar" style="width: '+result[0].receivedPerc+'%"></div>';
	str+='</div>';
	str+='</li>';
	str+='<li>';
	str+='<b>Cards Not Received<span>&nbsp;&nbsp;'+result[0].notReceivedPerc+'%</span></b>';
	str+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str+='<div class="bar" style="width: '+result[0].notReceivedPerc+'%"></div>';
	str+='</div>';
	str+='</li>';
	str+='<li>';
	str+='<b>Not Registered Member Phone Numbers<span>&nbsp;&nbsp;'+result[0].notMemberPerc+'%</span></b>';
	str+='<div style="height:5px;" class="progress progress-info mb-10">';
	str+='<div class="bar" style="width: '+result[0].notMemberPerc+'%"></div>';
	str+='</div>';
	str+='</li>';
	str+='<li  style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;">';
	str+='<b>Wrong option selected <span>&nbsp;&nbsp;'+result[0].wrongOptionPerc+'%</span></b>';
	str+='<div style="height:5px;" class="progress progress-warning mb-10">';
	str+='<div class="bar" style="width: '+result[0].wrongOptionPerc+'%"></div>';
	str+='</div>';
	str+='</li>';
	str+='<h6 class="label" style="width: 180px; margin-left: 2px; margin-right: -55px;">Not Selected Any Option<span>&nbsp;&nbsp;'+result[0].noOptionPerc+'%</h6>';
	//str+='<li>';
	//str+='<b>Not selected any option<span>&nbsp;&nbsp;'+result[0].noOptionPerc+'%</span></b>';
	//str+='<div style="height:5px;" class="progress progress-muted mb-10">';
	//str+='<div class="bar" style="width: '+result[0].noOptionPerc+'%"></div>';
	str+='</div>';
	str+='</li>';
	str+='</ul>';
	str+='</div>';
	$("#successIvrTD").html(str);
	var str1='';
	str1+='<div class="row-fluid">';
	str1+='<h4 class="m-0" style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;color:#d54a45;">IVR ERROR CALLS</h4>';
	str1+='<ul class="unstyled">';
	str1+='<li>';
	str1+='<b>User Busy  <span>&nbsp;&nbsp;'+result[0].userBusyPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-success mb-10">';
	str1+='<div class="bar" style="width: '+result[0].userBusyPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li>';
	str1+='<b>No Answer<span>&nbsp;&nbsp;'+result[0].noAnswerPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-warning mb-10">';
	str1+='<div class="bar" style="width: '+result[0].noAnswerPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li>';
	str1+='<b>Switch Congestion <span>&nbsp;&nbsp;'+result[0].switchCongestionPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].switchCongestionPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li>';
	str1+='<b>Other <span>&nbsp;&nbsp;'+result[0].otherErrorPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].otherErrorPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='</ul>';
	str1+='</div>';
	$("#errorIvrTD").html(str1);
	}
	
		
function dynamicSort(property){
    var sortOrder = 1;
		if(property[0] === "-"){
			sortOrder = -1;
			property = property.substr(1);
		}
		return function (a,b) {
			var result = (a[property] > b[property]) ? -1 : (a[property] < b[property]) ? 1 : 0;
			return result * sortOrder;
		}
	}
	
	
	
	
	
	function getCandidateDataCollectionInfo(id,type,state){
	var perc = $("#"+id).attr("key");
      var reqid="APmandalDiv";	
	if(id == "amount1"){
	   reqid="APmandalDiv";
	}else if(id == "amount2"){
	  reqid="APpanchayatDiv";
	}else if(id == "amount3"){
	  reqid="TGmandalTableDiv";
	}else if(id == "amount4"){
	  reqid="TGpanchayattableDiv";
	}
 
     if(type == "panchayat"){
	     getPanchayatWiseIVRCount(perc,state);
	 }else{
	    getTehsilWiseIVRCount(perc,state);
	 }
 
 
	}
function getDistrictConstiWisePerformance(locationType,locations){
            var radioVal = $('input[name=stateradio]:checked').val();
			if(radioVal =="TS"){
			    radioVal ="TG";
			}
			var fromDate ='';
			var toDate ='';
			var selectedDate = $("#selectedDate").text().split('-');
			if(selectedDate != '')
			{
			 fromDate = selectedDate[0];
			 toDate = selectedDate[1];
			}
            $("#veryGoodPercDiv").html('<img src="images/icons/search.gif" style="margin-left:70px;"/>');
			$("#goodPercDiv").html('<img src="images/icons/search.gif" style="margin-left:35px;"/>');
			$("#okPercDiv").html('<img src="images/icons/search.gif" style="margin-left:12px;"/>');
			$("#poorPercDiv").html('<img src="images/icons/search.gif" style="margin-left:30px;"/>');
			$("#veryPoorPercDiv").html('<img src="images/icons/search.gif" style="margin-left:75px;"/>');
			$("#locationWiseAllPercDiv").html('<div class="table table-bordered table-hover border-radius-0" style=" box-shadow: inset 0 0 5px 4px rgba(0,0,0,0.1);"><img src="images/icons/loading.gif" style="margin-bottom: 20px;margin-left: 424px;margin-top: 20px;"/></div>');
			
      var jsObj = {	
			locationType:locationType,
			state:radioVal,
			locations:locations,
			fromDate : fromDate,
			toDate : toDate             
		}
     $.ajax({
			type : "POST",
			url : "getLocationWisePercInfo.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		    $("#constiDistMainDiv").removeAttr("disabled");
			$("#veryGoodPercDiv").html(result.totalCalls);
			$("#goodPercDiv").html(result.totalCallsPerc);
			$("#okPercDiv").html(result.received);
			$("#poorPercDiv").html(result.receivedPerc);
			$("#veryPoorPercDiv").html(result.notReceived);
			var str='';
			if(result.apList != null && result.apList.length > 0){
				str+='<table class="table table-bordered table-hover border-radius-0" style=" box-shadow: inset 0 0 5px 4px rgba(0,0,0,0.1);">';
				  for(var i in result.apList){		
						str+='<tr>';
						str+='	<td>';
						str+='		<b>'+result.apList[i].name+' <span>&nbsp;&nbsp;'+result.apList[i].notReceivedPerc+'%</span></b>';
						if(locationType=="constituency"){
						   str+='<i class=" icon-eye-open" title="Click here to view Mandal,Panchayat and Booth wise details" onclick="getSubLocationInfo('+result.apList[i].id+',\''+result.apList[i].name+'\')" style="cursor:pointer;"></i>';
						}
						if(result.apList[i].notReceivedPerc <= 10){
							  str+='<div class="progress progress-success mb-10" style="height:3px; box-shadow: none;">';
						}else if(result.apList[i].notReceivedPerc > 10 && result.apList[i].notReceivedPerc <= 20 ){
							  str+='<div class="progress progress-info mb-10" style="height:3px; box-shadow: none;">';
						}else if(result.apList[i].notReceivedPerc > 20 && result.apList[i].notReceivedPerc <= 40 ){
							  str+='<div class="progress progress-primary mb-10" style="height:3px; box-shadow: none;">';
						}else if(result.apList[i].notReceivedPerc > 40 && result.apList[i].notReceivedPerc <= 60 ){
							  str+='<div class="progress progress-warning mb-10" style="height:3px; box-shadow: none;">';
						}else if(result.apList[i].notReceivedPerc > 60){
							  str+='<div class="progress progress-danger mb-10" style="height:3px; box-shadow: none;">';
						}
						str+='			<div style="width: '+result.apList[i].notReceivedPerc+'%" class="bar"></div>';
						str+='		</div>';
						str+='	</td>';					
						str+='</tr>';
					}
				str+='<table>';	
			}else{
			   str+='<div class="table table-bordered table-hover border-radius-0" style=" box-shadow: inset 0 0 5px 4px rgba(0,0,0,0.1);"><div style="font-weight:bold;padding:20px;margin-left:375px;">No Data Available</div></div>';
			}
					$("#locationWiseAllPercDiv").html(str);
		});

}
function getLocationWisePerformance(constituencyId,locationType,name){

    if(locationType == "mandal" || locationType == "panchayat" || locationType == "booth"){
	 var strn ='';
      strn+='<div class="span12">';
	  strn+='		<h4 class="text-center text-uppercase alert-success m-0 border-radius-0"> '+locationType+' Wise Info For '+name+' Constituency<button onclick="closeDIV(\'allErrorsInfoLocationWiseOuter\');" class="close" type="button" style="margin-top: 1px;">x</button></h4>';
	  strn+='		<span class="text-center well well-small m-0 border-radius-0" style="width:98%;display:inline-block;">';
	  strn+='		<label class="radio inline">';
	  strn+='		 View Details by ';
	  strn+='		</label>';
	  strn+='			<label class="radio inline">';
				 if(locationType == "mandal"){
	  strn+='			    <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'mandal\',\''+name+'\');" checked="checked" name="locationSelRdo" value="mandal" > Mandal';
				 }else{
	 strn+='		   <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'mandal\',\''+name+'\');"  name="locationSelRdo" value="mandal" > Mandal';
				 }
	strn+='			</label>	';			

	strn+='			<label class="radio inline">';
				 if(locationType == "panchayat"){
	strn+='			   <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'panchayat\',\''+name+'\');" checked="checked" name="locationSelRdo" value="panchayat"> Panchayat';
				  }else{
	strn+='			   <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'panchayat\',\''+name+'\');" name="locationSelRdo" value="panchayat"> Panchayat';
				  }
	strn+='			</label>';
	strn+='			<label class="radio inline">';
				if(locationType == "booth"){
	strn+='			   <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'booth\',\''+name+'\');" checked="checked" name="locationSelRdo" value="booth"> Booth';
				 }else{
	strn+='			   <input type="radio" onclick="getLocationWisePerformance('+constituencyId+',\'booth\',\''+name+'\');" name="locationSelRdo" value="booth"> Booth';
				  }
	strn+='			</label>';
	strn+='		</span>';
	strn+='		   <div id="allErrorsInfoLocationWise" />';
	strn+='	</div>';
		$("#allErrorsInfoLocationWiseOuter").html(strn);
	}else{
	   $("#allErrorsInfoLocationWiseOuter").html('<div class="span12"><h4 class="text-center text-uppercase alert-success m-0 border-radius-0"> Constituency Wise <button onclick="closeDIV(\'allErrorsInfoLocationWiseOuter\');" class="close" type="button" style="margin-top: 1px;">x</button></h4><div id="allErrorsInfoLocationWise" /></div>');
	}
		
     var fromDate ='';
	 var toDate ='';
	 var selectedDate = $("#selectedDate").text().split('-');
	 if(selectedDate != '')
	  {
		 fromDate = selectedDate[0];
		 toDate = selectedDate[1];
	 }
     $("#allErrorsInfoLocationWise").html('<div class="table table-bordered border-radius-0 table-condensed" style="font-size:10px;"><img src="images/icons/loading.gif" style="margin-bottom: 20px;margin-left: 424px;margin-top: 20px;"/></div>');
      var jsObj = {	
			locationType:locationType,
			constituencyId:constituencyId,
			fromDate : fromDate,
			toDate : toDate          
		}
     $.ajax({
			type : "POST",
			url : "getLocationWisePercErrorInfo.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		    var str ="";
			if(result.apList != null && result.apList.length > 0){
			    str+='<input type="button"  style="margin-bottom: 10px;margin-top: 10px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'ivrStatusReportTab\');" value="Click Here To Generate Excel"/>';
				str+='<div style="max-height: 400px;overflow-y: scroll;">';
				str+='<table class="table table-bordered border-radius-0 table-condensed" id="ivrStatusReportTab" style="font-size:10px;">';
				str+='  <thead>';
				str+='	   <tr class="well">';
				if(locationType =="All" || locationType =="AP" || locationType =="TS"){
				   str+='				<th rowspan="2">Constituency</th>';
				}else{
				  str+='				<th rowspan="2">'+locationType+'</th>';
				}
				str+='				<th rowspan="2">Registred Count</th>';
				str+='				<th rowspan="2">Cards Printed Count</th>';
				str+="				<th rowspan='2'>Job ID's</th>";
				str+='				<th rowspan="2">IVR Calls Dailed</th>';
				str+='				<th colspan="2">Answered Calls</th>';
				str+='				<th colspan="2">Not Selected Any option</th>';
				str+='				<th colspan="2">Error Calls</th>';
				str+='				<th colspan="2">Card Received</th>';
				str+='				<th colspan="2">Card Not Received</th>';
				str+='				<th colspan="2">Member NOT Registered</th>';
				str+='				<th colspan="2">Wrong option Selected</th>';
				str+='		</tr>';
				str+='	    <tr class="well">';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='		</tr>';
				str+='	</thead>';
				str+='	<tbody>';
				for(var i in result.apList){
				  str+='<tr>';
				  str+='  <td>'+result.apList[i].name+'</td>';
				  str+='  <td>'+result.apList[i].registeredCount+'</td>';
				  str+='  <td>'+result.apList[i].printedCount+'</td>';
				  str+='  <td>'+result.apList[i].jobCode+'</td>';
				  str+='  <td>'+result.apList[i].totalIvrCalls+'</td>';
				  str+='  <td>'+result.apList[i].totalAnswerdCalls+'</td>';
				  str+='  <td>'+result.apList[i].totalAnswerdPerc+'</td>';
				  str+='  <td>'+result.apList[i].noOptionSel+'</td>';
				  str+='  <td>'+result.apList[i].noOptionSelPerc+'</td>';
				  str+='  <td>'+result.apList[i].errorCalls+'</td>';
				  str+='  <td>'+result.apList[i].errorCallsPerc+'</td>';
				  str+='  <td>'+result.apList[i].received+'</td>';
				  str+='  <td>'+result.apList[i].receivedPerc+'</td>';
				  str+='  <td>'+result.apList[i].notReceived+'</td>';
				  str+='  <td>'+result.apList[i].notReceivedPerc+'</td>';
				  str+='  <td>'+result.apList[i].notMember+'</td>';
				  str+='  <td>'+result.apList[i].notMemberPerc+'</td>';
				  str+='  <td>'+result.apList[i].wrongOptionSel+'</td>';
				  str+='  <td>'+result.apList[i].wrongOptionSelPerc+'</td>';
				  str+='</tr>';
				}
				str+='	</tbody>';
				str+='</table></div>';
				$("#allErrorsInfoLocationWise").html(str);
				/*$("#ivrStatusReportTab").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});*/
			}else{
			   $("#allErrorsInfoLocationWise").html('<div class="table table-bordered table-hover border-radius-0" style=" box-shadow: inset 0 0 5px 4px rgba(0,0,0,0.1);"><div style="font-weight:bold;padding:20px;margin-left:375px;">No Data Available</div></div>');
			}
					
		});

}
function closeDIV(id){
   $("#"+id).html("");
}
var selectedLocation ="district";
var tgIds ="8,4,295,296,2,5,1,3,7,6,10,13,15,12,18,16,342,343,11,321,23,322,318,26,30,24,20,323,31,319,21,320,40,35,36,32,37,41,39,337,336,34,57,367,345,346,347,56,348,349,351,350,55,58,60,59,50,49,315,47,314,51,316,46,317,44,43,54,313,52,53,66,335,68,64,369,69,73,63,62,70,61,65,71,67,77,338,79,339,78,84,82,75,81,85,74,89,87,362,86,91,93,94,363,364,97,365,92,324,107,101,104,103,325,105,102,326,100,80";
var apIds ="111,352,117,114,116,108,109,112,353,113,360,124,125,122,120,121,361,129,127,368,354,355,356,357,358,134,136,359,138,133,141,135,140,137,163,157,156,307,155,147,308,159,153,146,160,310,152,309,303,304,305,149,306,172,366,181,174,173,167,179,178,177,180,169,170,171,176,168,194,193,184,185,187,327,328,182,329,330,196,331,195,191,192,186,210,215,206,211,217,213,216,209,212,312,311,199,208,214,207,203,205,344,221,228,218,219,229,227,223,225,226,222,224,232,241,233,340,341,236,231,237,239,238,242,252,243,246,248,251,245,244,250,249,254,332,261,260,263,262,333,257,264,258,265,334,253,255,276,279,297,278,277,298,272,299,273,270,275,300,267,271,290,285,294,286,280,291,289,288,283,301,281,302,284,282";
function getOtherLocationsInfo(){
  $("#constiDistMainDiv").attr("disabled","disabled");
 if(selectedLocation == "district"){
    selectedLocation = "constituency";
 }else{
    selectedLocation ="district";
 }
 if(selectedLocation == "district"){
   $("#constiDistMainDiv").attr("value","Click to view Constituency Wise");
   $("#districtConstituencyHeading").html("District Wise Card Not Received Response");
   getDistrictConstiWisePerformance("district","");
 }else{
   $("#constiDistMainDiv").attr("value","Click to view District Wise");
   $("#districtConstituencyHeading").html("Constituency Wise Card Not Received Response");
   var radioVal = $('input[name=stateradio]:checked').val();
   if(radioVal == "All"){
     getDistrictConstiWisePerformance("constituency",apIds+","+tgIds);
   }else if(radioVal == "AP"){
     getDistrictConstiWisePerformance("constituency",apIds);
   }else{
     getDistrictConstiWisePerformance("constituency",tgIds);
   }
  } 
   
}	
function getSubLocationInfo(id,name){
  getLocationWisePerformance(id,"mandal",name);

}
function generateExcel(reqId){
     tableToExcel(reqId, 'IVR Status');
 }
</script>
<script>
getIvrBasicCount();
getDateWiseIVRCount();
getDistrictConstiWisePerformance("district","");
getLocationWisePerformance(232,"All");
</script>



</body>
</html>