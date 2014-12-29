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
						<td class="width35p">
						<img style="height:18px;width:16px;margin-top:80px;" src="./images/icons/search.gif" id="ajaxImg" class="offset1"/>
						<div id="registrationTD">
						</div>
						</td>
						<td class="width15p " rowspan="2" id="totalIvrTD">
							
						</td>
						<td class="width25p" rowspan="2"  id="successIvrTD">
							
						</td>
						<td class="width25p" rowspan="2" style="230px !important;"  id="errorIvrTD">
							
						</td>	
							
					</tr>
					<tr>
						<td class="width25p" id="printingTD">
							
						</td>
						
					</tr>
					
				</table>
			</div>	
			<!----- /Total In AP & TS ----->
			
		</div>	
		
		<!-- Top Div End -->
		<div class="row">
			<!-----Total In AP & TS ----->
			<div class="span12" id="APandTsDiv">
				
			</div>	
			<!----- /Total In AP & TS ----->
			
	
			<div class="span6 m_top20" style="outline:6px solid rgb(223, 240, 216);clear:both;" id="ApDataDiv">
		
				<!-----TS Constituency wise ------>
				<h4 class="alert alert-info text-center border-radius-0 m-0">AP DISTRICT WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="APdistrictableDiv" >
					<img style="width:20px;" src="./images/icons/search.gif" id="apDistImg" class="offset3"/>
				</div>
				<!----- /AP Constituency wise ------>
				
				<!------AP District wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">AP CONSTITUENCY WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="APconstituencyTableDiv">
					<img style="width:20px;" src="./images/icons/search.gif" id="apConstImg" class="offset3"/>
				</div>
				<!------/AP District wise -------->
				<!------AP Tehsil wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">AP MANDAL WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="APmandalDiv1">
					 <div class="rangeSliderDiv" style=" margin-left: 35px;width:400px;">       
			          <table><tr><td><div id="slider1" style="width:300px;" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div></td><td><input type="button"  class="btn btn-success" onclick="getCandidateDataCollectionInfo('amount1','mandal','AP');" value="Get"/></td></tr></table>
			          <span  id="amount1" key="0" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
		             </div>
					 <div id="APmandalDiv"></div>
				</div>
				<!------/AP Tehsil wise -------->
				<!------AP Panchayat wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">AP PANCHAYAT WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="APpanchayatDiv1">
					<div class="rangeSliderDiv" style=" margin-left: 35px;width:400px;">       
			          <table><tr><td><div id="slider2" style="width:300px;" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div></td><td><input type="button"  class="btn btn-success" onclick="getCandidateDataCollectionInfo('amount2','panchayat','AP');" value="Get"/></td></tr></table>
			          <span id="amount2" key="0" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
		             </div>
					 <div id="APpanchayatDiv"></div>
				</div>
				<!------/AP Panchayat wise -------->
			</div>
			
			<!----- /Total in AP ----->
			
			<!-------Total in TS------>
			<div class="span6 m_top20" style="outline:6px solid rgb(223, 240, 216);" id="TGDataDiv">
			
				<!-----TS Constituency wise ------>
				<h4 class="alert alert-info text-center border-radius-0 m-0">TS DISTRICT WISE DETAILS</h4>
				<div style="overflow: auto;height: 300px ! important;" id="TGdistrictableDiv" >
				<img style="width:20px;" src="./images/icons/search.gif" id="tgDistImg" class="offset3"/>
				</div>
				<!-----/TS Constituency wise ------>
				
				<!------TS District wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">TS CONSTITUENCY WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="TGconstituencyTableDiv">
				<img style="width:20px;" src="./images/icons/search.gif"  id="tgConstImg" class="offset3"/>
				</div>
				<!------/TS District wise -------->
				<!-----TS Constituency wise ------>
				<h4 class="alert alert-info text-center border-radius-0 m-0">TS MANDAL WISE DETAILS</h4>
				<div style="overflow: auto;height: 300px ! important;" id="TGmandalTableDiv1">
					<div class="rangeSliderDiv" style=" margin-left: 35px;width:400px;">       
			          <table><tr><td><div id="slider3" style="width:300px;" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div></td><td><input type="button"  class="btn btn-success" onclick="getCandidateDataCollectionInfo('amount3','mandal','TG');" value="Get"/></td></tr></table>
			          <span id="amount3" key="0" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
		             </div>
					 <div id="TGmandalTableDiv"></div>
				</div>
				<!-----/TS Constituency wise ------>
				
				<!------TS District wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">TS PANCHAYAT WISE DETAILS</h4>
				<div style="overflow: auto; height: 300px ! important;" id="TGpanchayattableDiv1">
					<div class="rangeSliderDiv" style=" margin-left: 35px;width:400px;">       
			          <table><tr><td><div id="slider4" style="width:300px;" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a></div></td><td><input type="button"  class="btn btn-success" onclick="getCandidateDataCollectionInfo('amount4','panchayat','TG');" value="Get"/></td></tr></table>
			          <span id="amount4" key="0" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
		             </div>
					 <div id="TGpanchayattableDiv"></div>
				</div>
				<!------/TS District wise -------->
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
                   console.log(start.toISOString(), end.toISOString(), label);
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
					
					  console.log("show event fired"); });
					   

                  $('#daterange').on('hide.daterangepicker', function() { 
					   
					  console.log("hide event fired"); });
				
                  $('#daterange').on('apply.daterangepicker', function(ev, picker) { 
					   
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('MMMM D, YYYY') 
                      + " to " 
                      + picker.endDate.format('MMMM D, YYYY')
                    ); 
					  getDateWiseIVRCount();
                  });
				 
                  $('#daterange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
			$("#selectedDate").html(''); 
				
	}

		$(".radioCls").click(function() {
		
			 dateRange();
			getIvrBasicCount();
			getDateWiseIVRCount();
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
		
		str+='<canvas id="printPercChart" style=" float:left;margin-top: 20px;" width="120px"; height=" 120px"; ></canvas>';
		str+='<div  style="float:right; margin-top: 45px;">';
		str+='<h4 class="m-0">'+result.count+'</h4>';
		if(state == "All")
		str+='<p class="m-0" >Members Registered in <br> Andhra Pradesh And <br>Telangana</p>';
		else if(state == "AP")
		str+='<p class="m-0" >Members Registered in <br> Andhra Pradesh </p>';
		else if(state == "TS")
		str+='<p class="m-0" >Members Registered in <br> Telangana</p>';
		str+='</div>';
		$("#registrationTD").html(str);
		var str1='';
		str1+='<canvas id="piep" style="float:left;margin-top: 20px;" height="120px" width="120px"></canvas>';
		str1+='<div  style="float:right; margin-top: 33px;">';
		str1+='<h4 class="m-0">'+result.printingCompleted+'</h4>';
		if(state == "All")
		str1+='<p class="m-0" >Cards Printed in <br> Andhra Pradesh And <br>Telangana</p>';
		else if(state == "AP")
		str1+='<p class="m-0" >Cards Printed in <br> Andhra Pradesh </p>';
		else if(state == "TS")
		str1+='<p class="m-0" >Cards Printed in <br> Telangana</p>';
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
		str+='<canvas id="answerErrorChart" style="width: 180px; height: 180px; margin-left:0px; padding-left: 29px; margin-right: -42px; margin-top: 35px;" width="180px" height="180px"></canvas>';
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
	str+='<h6>Selected Any Option : '+selectedOptPerc+' %</h6>';
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
	
	str+='<li>';
	str+='<b>Not selected any option<span>&nbsp;&nbsp;'+result[0].noOptionPerc+'%</span></b>';
	str+='<div style="height:5px;" class="progress progress-muted mb-10">';
	str+='<div class="bar" style="width: '+result[0].noOptionPerc+'%"></div>';
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
	function getConstituencyWiseIVRCount()
	{
		$("#tgConstImg").show();
		$("#apConstImg").show();
		$("#tgDistImg").show();
		$("#apDistImg").show();
		var jsObj = {	
		task:""             
		}
			   
		$.ajax({
			type : "POST",
			url : "getConstituencyWiseIVRAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildConstCount(result);
			buildDistrictCount(result);
		});
	}
	
	function buildConstCount(result)
	{
		$("#tgConstImg").hide();
		$("#apConstImg").hide();
		var ApArr= new Array();
		var TGArr = new Array();
		for(var i in result)
		{
			for(var j in result[i].subList)
					{
						if(result[i].id > 10)
						ApArr.push(result[i].subList[j]);
						else
						TGArr.push(result[i].subList[j]);	
				    }
		}
		ApArr.sort(dynamicSort("notReceived"));
		TGArr.sort(dynamicSort("notReceived"));
		
		var str ='';
		str+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="apConstTable">';
		str+='<thead class="alert-info">';
		str+='<tr>';
		str+='<th rowspan="2">Constituency Name</th>';
		str+='<th rowspan="2">No of members registered</th>';
		str+='<th rowspan="2">IVR Calls </th>';
		str+='<th colspan="2">Cards Received </th>';
		str+='<th colspan="2">Cards Not Received </th>';
		str+='<th colspan="2">Not Registered Members </th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
	
			for(var j in ApArr)
			{
				if(ApArr[j].total == null)
					ApArr[j].total =0;
				if(ApArr[j].receivedPerc == null)
				ApArr[j].receivedPerc = 0.0;
				if(ApArr[j].notReceived == null)
				ApArr[j].notReceived = 0.0;
				if(ApArr[j].notMemberPerc == null)
				ApArr[j].notMemberPerc = 0.0;
			str+='<tr><td>'+ApArr[j].name+'</td><td>'+ApArr[j].total+'</td><td>'+ApArr[j].answeredCnt+'</td><td>'+ApArr[j].received+'</td><td>'+ApArr[j].receivedPerc+'</td><td>'+ApArr[j].notReceived+'</td><td>'+ApArr[j].notReceivedPerc+'</td><td>'+ApArr[j].notRegistered+'</td><td>'+ApArr[j].notMemberPerc+'</td></tr>';
			}
	
 str+='</tbody>';
 str+='</table>';
 $("#APconstituencyTableDiv").html(str);
$("#apConstTable").dataTable();
 	var str1 ='';
		str1+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="tgConstTable">';
		str1+='<thead class="alert-info">';
		str1+='<tr>';
		str1+='<th rowspan="2">Constituency Name</th>';
		str1+='<th rowspan="2">No of members registered</th>';
		str1+='<th rowspan="2">IVR Calls </th>';
		str1+='<th colspan="2">Cards Received </th>';
		str1+='<th colspan="2">Cards Not Received </th>';
		str1+='<th colspan="2">Not Registered Members </th>';
		str1+='</tr>';
		str1+='<tr>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='</tr>';
		str1+='</thead>';
		str1+='<tbody>';
		
			for(var j in TGArr)
			{
				if(TGArr[j].total == null)
					TGArr[j].total =0;
				if(TGArr[j].receivedPerc == null)
				TGArr[j].receivedPerc = 0.0;
				if(TGArr[j].notReceived == null)
				TGArr[j].notReceived = 0.0;
				if(TGArr[j].notMemberPerc == null)
				TGArr[j].notMemberPerc = 0.0;
			str1+='<tr><td>'+TGArr[j].name+'</td><td>'+TGArr[j].total+'</td><td>'+TGArr[j].answeredCnt+'</td><td>'+TGArr[j].received+'</td><td>'+TGArr[j].receivedPerc+'</td><td>'+TGArr[j].notReceived+'</td><td>'+TGArr[j].notReceivedPerc+'</td><td>'+TGArr[j].notRegistered+'</td><td>'+TGArr[j].notMemberPerc+'</td></tr>';
			}

		str1+='</tbody>';
		str1+='</table>';
		 $("#TGconstituencyTableDiv").html(str1);
	 $("#tgConstTable").dataTable();
	}

	function buildDistrictCount(result)
	{
		$("#tgDistImg").hide();
		$("#apDistImg").hide();
		var ApArr= new Array();
		var TGArr = new Array();
		for(var i in result)
		{
			if(result[i].id > 10)
				{
				ApArr.push(result[i]);
				}
				else
				{
				TGArr.push(result[i]);
				}
		}
		
		ApArr.sort(dynamicSort("notReceived"));
		TGArr.sort(dynamicSort("notReceived"));
		var str ='';
		str+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="apDistTable">';
		str+='<thead class="alert-info">';
		str+='<tr>';
		str+='<th rowspan="2">District Name</th>';
		str+='<th rowspan="2">No of members registered</th>';
		str+='<th rowspan="2">IVR Calls </th>';
		str+='<th colspan="2">Cards Received </th>';
		str+='<th colspan="2">Cards Not Received </th>';
		str+='<th colspan="2">Not Registered Members </th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var j in ApArr)
		{
			if(ApArr[j].total == null)
					ApArr[j].total =0;
				if(ApArr[j].receivedPerc == null)
				ApArr[j].receivedPerc = 0.0;
				if(ApArr[j].notReceived == null)
				ApArr[j].notReceived = 0.0;
				if(ApArr[j].notMemberPerc == null)
				ApArr[j].notMemberPerc = 0.0;
			str+='<tr><td>'+ApArr[j].name+'</td><td>'+ApArr[j].total+'</td><td>'+ApArr[j].answeredCnt+'</td><td>'+ApArr[j].received+'</td><td>'+ApArr[j].receivedPerc+'</td><td>'+ApArr[j].notReceived+'</td><td>'+ApArr[j].notReceivedPerc+'</td><td>'+ApArr[j].notRegistered+'</td><td>'+ApArr[j].notMemberPerc+'</td></tr>';
		
		}
 str+='</tbody>';
 str+='</table>';
 $("#APdistrictableDiv").html(str);
 $("#apDistTable").dataTable();
		
 	var str1 ='';
		str1+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="tgDistTable">';
		str1+='<thead class="alert-info">';
		str1+='<tr>';
		str1+='<th rowspan="2">District Name</th>';
		str1+='<th rowspan="2">No of members registered</th>';
		str1+='<th rowspan="2">IVR Calls </th>';
		str1+='<th colspan="2">Cards Received </th>';
		str1+='<th colspan="2">Cards Not Received </th>';
		str1+='<th colspan="2">Not Registered Members </th>';
		str1+='</tr>';
		str1+='<tr>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='</tr>';
		str1+='</thead>';
		str1+='<tbody>';
		for(var j in TGArr)
		{
			if(TGArr[j].total == null)
					TGArr[j].total =0;
				if(TGArr[j].receivedPerc == null)
				TGArr[j].receivedPerc = 0.0;
				if(TGArr[j].notReceived == null)
				TGArr[j].notReceived = 0.0;
				if(TGArr[j].notMemberPerc == null)
				TGArr[j].notMemberPerc = 0.0;
			str1+='<tr><td>'+TGArr[j].name+'</td><td>'+TGArr[j].total+'</td><td>'+TGArr[j].answeredCnt+'</td><td>'+TGArr[j].received+'</td><td>'+TGArr[j].receivedPerc+'</td><td>'+TGArr[j].notReceived+'</td><td>'+TGArr[j].notReceivedPerc+'</td><td>'+TGArr[j].notRegistered+'</td><td>'+TGArr[j].notMemberPerc+'</td></tr>';
		}
		str1+='</tbody>';
		str1+='</table>';
		 $("#TGdistrictableDiv").html(str1);
	$("#tgDistTable").dataTable();
		
	
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
	function getPanchayatWiseIVRCount(range,state)
	{
	   if(state == "AP"){
	     $("#APpanchayatDiv").html('<img style="width:20px;" src="./images/icons/search.gif" class="offset3"/>');
	   }else{
	     $("#TGpanchayattableDiv").html('<img style="width:20px;" src="./images/icons/search.gif" class="offset3"/>');
	   }
		var jsObj = {	
		state:state,
        range:range		
		}
			   
		$.ajax({
			type : "POST",
			url : "getPanchayatWiseIVRAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildPanchayatWiseIVRCount(result,state);
		});
	}
	
	function getTehsilWiseIVRCount(range,reqtype)
	{
	  if(reqtype == "AP" || reqtype == "both"){
	     $("#APmandalDiv").html('<img style="width:20px;" src="./images/icons/search.gif" class="offset3"/>');
	  }
	  if(reqtype == "TG" || reqtype == "both"){
	    $("#TGmandalTableDiv").html('<img style="width:20px;" src="./images/icons/search.gif" class="offset3"/>');
	  }
		var jsObj = {	
		range:range             
		}
			   
		$.ajax({
			type : "POST",
			url : "getTehsilWiseIVRAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildTehsilWiseIVRCount(result,reqtype);
		});
	}
	
	function buildTehsilWiseIVRCount(result,reqtype)
	{
		var ApArr = result.apList;
		var TGArr = result.tgList;
		var str ='';
		str+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="APmandalTable">';
		str+='<thead class="alert-info">';
		str+='<tr>';
		str+='<th rowspan="2">District</th>';
		str+='<th rowspan="2">Constituency</th>';
		str+='<th rowspan="2">Mandal</th>';
		str+='<th rowspan="2">IVR Calls </th>';
		str+='<th colspan="2">Cards Received </th>';
		str+='<th colspan="2">Cards Not Received </th>';
		str+='<th colspan="2">Not Registered Members </th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
	
			for(var j in ApArr)
			{
			  str+='<tr><td>'+ApArr[j].areaName+'</td><td>'+ApArr[j].locationName+'</td><td>'+ApArr[j].name+'</td><td>'+ApArr[j].totalCalls+'</td><td>'+ApArr[j].received+'</td><td>'+ApArr[j].receivedPerc+'</td><td>'+ApArr[j].notReceived+'</td><td>'+ApArr[j].notReceivedPerc+'</td><td>'+ApArr[j].notMember+'</td><td>'+ApArr[j].notMemberPerc+'</td></tr>';
			}
	
		 str+='</tbody>';
		 str+='</table>';
		 if(reqtype == "AP" || reqtype == "both"){
		   $("#APmandalDiv").html(str);
		   $("#APmandalTable").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
		}
 	var str1 ='';
		str1+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="TGmandalTable">';
		str1+='<thead class="alert-info">';
		str1+='<tr>';
		str1+='<th rowspan="2">District</th>';
		str1+='<th rowspan="2">Constituency</th>';
		str1+='<th rowspan="2">Mandal</th>';
		str1+='<th rowspan="2">IVR Calls </th>';
		str1+='<th colspan="2">Cards Received </th>';
		str1+='<th colspan="2">Cards Not Received </th>';
		str1+='<th colspan="2">Not Registered Members </th>';
		str1+='</tr>';
		str1+='<tr>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='<th>Count</th>';
		str1+='<th>%</th>';
		str1+='</tr>';
		str1+='</thead>';
		str1+='<tbody>';
		
		  for(var j in TGArr)
			{
			  str1+='<tr><td>'+TGArr[j].areaName+'</td><td>'+TGArr[j].locationName+'</td><td>'+TGArr[j].name+'</td><td>'+TGArr[j].totalCalls+'</td><td>'+TGArr[j].received+'</td><td>'+TGArr[j].receivedPerc+'</td><td>'+TGArr[j].notReceived+'</td><td>'+TGArr[j].notReceivedPerc+'</td><td>'+TGArr[j].notMember+'</td><td>'+TGArr[j].notMemberPerc+'</td></tr>';
			}

		str1+='</tbody>';
		str1+='</table>';
		if(reqtype == "TG" || reqtype == "both"){
		 $("#TGmandalTableDiv").html(str1);
	     $("#TGmandalTable").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
		}
	}
	function buildPanchayatWiseIVRCount(result,state)
	{
		var ApArr = result.apList;
		var str ='';
		if(state =="AP"){
		   str+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="APpanchayatTable">';
		}else{
		   str+='<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 " id="TGpanchayattable">';
		}
		str+='<thead class="alert-info">';
		str+='<tr>';
		str+='<th rowspan="2">District</th>';
		str+='<th rowspan="2">Constituency</th>';
		str+='<th rowspan="2">Panchayat</th>';
		str+='<th rowspan="2">IVR Calls </th>';
		str+='<th colspan="2">Cards Received </th>';
		str+='<th colspan="2">Cards Not Received </th>';
		str+='<th colspan="2">Not Registered Members </th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='<th>Count</th>';
		str+='<th>%</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
	
			for(var j in ApArr)
			{
			  str+='<tr><td>'+ApArr[j].areaName+'</td><td>'+ApArr[j].locationName+'</td><td>'+ApArr[j].name+'</td><td>'+ApArr[j].totalCalls+'</td><td>'+ApArr[j].received+'</td><td>'+ApArr[j].receivedPerc+'</td><td>'+ApArr[j].notReceived+'</td><td>'+ApArr[j].notReceivedPerc+'</td><td>'+ApArr[j].notMember+'</td><td>'+ApArr[j].notMemberPerc+'</td></tr>';
			}
	
		 str+='</tbody>';
		 str+='</table>';
		 if(state =="AP"){
		     $("#APpanchayatDiv").html(str);
		     $("#APpanchayatTable").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
		 }else{
		     $("#TGpanchayattableDiv").html(str);
		     $("#TGpanchayattable").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
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


	
</script>
<script>
getIvrBasicCount();
getDateWiseIVRCount();
getConstituencyWiseIVRCount();
getPanchayatWiseIVRCount(0,"AP");
getPanchayatWiseIVRCount(0,"TG");
getTehsilWiseIVRCount(0,"both");
</script>
<script>
var range;

$( "#slider1" ).slider({
value:1,
min: 0,
max: 100,
step: 1,
slide: function( event, ui ) {
$( "#amount1" ).html( "Percentage : " + ui.value +" %");
$( "#amount1").attr("key",ui.value);
},
change: function( event, ui ) {

}
});
$( "#slider2" ).slider({
value:1,
min: 0,
max: 100,
step: 1,
slide: function( event, ui ) {
$( "#amount2" ).html( "Percentage : " + ui.value +" %");
$( "#amount2").attr("key",ui.value);
},
change: function( event, ui ) {

}
});
$( "#slider3" ).slider({
value:1,
min: 0,
max: 100,
step: 1,
slide: function( event, ui ) {
$( "#amount3" ).html( "Percentage : " + ui.value +" %");
$( "#amount3").attr("key",ui.value);
},
change: function( event, ui ) {

}
});
$( "#slider4" ).slider({
value:1,
min: 0,
max: 100,
step: 1,
slide: function( event, ui ) {
$( "#amount4").html( "Percentage : " + ui.value +" %");
$( "#amount4").attr("key",ui.value);
},
change: function( event, ui ) {

}
});
$( "#amount1" ).html( "Percentage : 0 %");
$( "#amount2" ).html( "Percentage : 0 %");
$( "#amount3" ).html( "Percentage : 0 %");
$( "#amount4" ).html( "Percentage : 0 %");
</script>


</body>
</html>