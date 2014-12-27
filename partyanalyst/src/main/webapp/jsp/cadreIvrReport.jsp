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


<title>IVR Report</title>
<style>
	.border-radius-0{border-radius:0px;}
	.pad-0{padding:0px;}
	.m-0{margin:0px;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.m_top10{margin-top:10px;}
	.m_top20{margin-top:20px;}
	.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
	.widgetservey h4{font-size:26px; font-weight: normal; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
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
	</style>
</head>

<body>

<div class="container ">	
   
		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 ">
				<h3 class="text-center text-uppercase">IVR  STATUS REPORTS DASHBOARD</h3>
			</div>
		</div><!-- Title Row End-->
		
		<div class="row">
			<!-----Total In AP & TS ----->
			<div class="span12" id="APandTsDiv">
				<img style="height:77px;width:90px;" src="./images/Loading-data.gif" id="ajaxImg" class="offset5"/>
			</div>	
			<!----- /Total In AP & TS ----->
			
			<!-----Today In AP
			<div class="span6 m_top20" id="todayApDiv">
			
						
			</div>
		/Today In AP
						
			Today In TS
			<div class="span6 m_top20" id="todayTGDiv">
						
			</div>
			<!----- /Today In TS ----->
			
			<!------ Total in AP ------->
			<!-- date range
		 <div style="overflow: auto" class="well">

				 <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
				    <i class="icon-calendar pull-right"></i>
                <span id="selectedDate"></span> 
               </div>


            </div>	
           
		date range end-->
			<div class="span6 m_top20" style="outline:6px solid rgb(223, 240, 216);clear:both;" id="ApDataDiv">
			 
				 <div id="APreportrange" class="pull-left" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                <!--  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>-->
				   <i class="icon-calendar pull-left"></i>
                <span id="APselectedDate"></span> 
				
		 </div>	
           
			<div id="ApTotalDiv" style="clear:both;">
				<img style="width:20px;" src="./images/icons/search.gif" id="apcntImg" class="offset3"/>
				</div>
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
			 <div id="TSreportrange" class="pull-left" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                <!--  <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>-->
				   <i class="icon-calendar pull-left"></i>
                <span id="TSselectedDate"></span> 
				
		 </div>	
           
				<div id="TGTotalDiv" style="clear:both;">
					<img style="width:20px;" src="./images/icons/search.gif" id="tgcntImg" class="offset3"/>
				</div>
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
			 	 
				   var cb = function(start, end, label) {
                   console.log(start.toISOString(), end.toISOString(), label);
                    $('#APreportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
					 $('#TSreportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
				  }

                  var optionSet1 = {
					
                    showDropdowns: true,
                    showWeekNumbers: true,
					ranges: {
                       'Today': [moment(), moment()],
                       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')]
                       
                    },
                    opens: 'right',
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
				  $('#APreportrange').daterangepicker(optionSet1, cb);
				  $('#TSreportrange').daterangepicker(optionSet1, cb);
					 $('#APreportrange').on('show.daterangepicker', function() { 
					   $('#APreportrange span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
					 
					  console.log("show event fired"); });
					   $('#TSreportrange').on('show.daterangepicker', function() { 
					   $('#TSreportrange span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
					 
					  console.log("show event fired"); });

                  $('#APreportrange').on('hide.daterangepicker', function() { 
					    getDateWiseIVRCount('AP');
					  console.log("hide event fired"); });
				   $('#TSreportrange').on('hide.daterangepicker', function() { 
					    getDateWiseIVRCount('TS');
					   console.log("hide event fired"); });
                  $('#APreportrange').on('apply.daterangepicker', function(ev, picker) { 
					   
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('MMMM D, YYYY') 
                      + " to " 
                      + picker.endDate.format('MMMM D, YYYY')
                    ); 
                  });
				   $('#TSreportrange').on('apply.daterangepicker', function(ev, picker) { 
                    console.log("apply event fired, start/end dates are " 
                      + picker.startDate.format('MMMM D, YYYY') 
                      + " to " 
                      + picker.endDate.format('MMMM D, YYYY')
                    ); 
                  });
                  $('#APreportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
				   $('#TSreportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
				$("#APselectedDate").html(''); 
				$("#TSselectedDate").html(''); 
			 });
	function getDateWiseIVRCount(state)
	{

		var selectedDate ;
		if(state == 'AP')
		{
		
		selectedDate = $("#APselectedDate").text().split('-'); 
		$("#apcntImg").show();
		}
		else
		{
	
		selectedDate = $("#TSselectedDate").text().split('-'); 
		$("#tgcntImg").show();
		}
		var fromDate = selectedDate[0];
		var toDate = selectedDate[1];
		var jsObj = {
			state:state,
			fromdate:fromDate,
			todate:toDate,
			task:"datewiseBasicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildIvrCountByDate(result,state);
		});
	}
	function getDateWiseIVRCountLoad(state)
	{
		if(state=="AP")
		$("#apcntImg").show();
		if(state=="TS")
		$("#tgcntImg").show();
		var fromDate ="";
		var toDate = "";
		var jsObj = {
			state:state,
			fromdate:fromDate,
			todate:toDate,
			task:"datewiseBasicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			
			buildIvrCountByDate(result,state);
		});
	}

function buildIvrCountByDate(result,state)
	{
		if(state=="AP")
		{
		$("#apcntImg").hide();
		$("#ApTotalDiv").html('');
		}
		if(state=="TS")
		{
		$("#tgcntImg").hide();
		$("#TGTotalDiv").html('');
		}
		
		var str ='';
		var totalInfo = result[0];
		var str = '';
		str+='<table class="table table-bordered border-radius-0 mb-0 table-condensed">';
		str+='<tr class="alert alert-success">';
		str+='<td rowspan="4" style="text-align: center;">';
		if(state == 'AP' )
		str+='<img style="width:70px" src="./images/AP.png">';
		else
		str+='<img style="width:70px" src="./images/TS.png">';
		if(state == 'AP' )
		str+='<h4 >Total In AP <br>'+ totalInfo.count+'  <br><small>Members Registered </small> <hr style="margin-top: 5px; margin-bottom: 5px;">IVR Calls<br>'+totalInfo.answeredCnt +' <br><small>Answered</small>	</h4>';
		else
		str+='<h4 >Total In TS <br>'+ totalInfo.tgCount+'  <br><small>Members Registered </small> <hr style="margin-top: 5px; margin-bottom: 5px;">IVR Calls<br>'+totalInfo.answeredCnt +' <br><small>Answered</small>	</h4>';
		str+='</td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td><h5>'+totalInfo.received+'</h5><p>Cards  <span class="text-orange">Received</span></p></td>';
		str+='</tr>';
		str+='<tr>';
		str+='<td><h5>'+totalInfo.notReceived+'</h5><p>Cards  <span class="text-orange">Not Received</span></p></td>	';
		str+='</tr>';
		str+='<tr>';
		str+='<td><h5>'+totalInfo.notRegistered+'</h5><p>Not Registered Member Phone Numbers</p></td>';	
		str+='</tr>';
		str+='</table>';
		if(state == 'AP' )
		$("#ApTotalDiv").html(str);
		if(state == 'TS')
		$("#TGTotalDiv").html(str);
	}
	
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
		
			var jsObj = {	
			task:"basicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			 $("#ajaxImg").hide();
			
			buildIvrCount(result);
		});
	}
	function buildIvrCount(result)
	{
		$("#APandTsDiv").html('');
		var str ='';
		var basicInfo = result[0] ;
		var totalInfo = result[1];
		var totalRegistered = basicInfo.count + basicInfo.tgCount;
		var pending  = totalRegistered - basicInfo.printingCompleted;
		var ivrError = totalInfo.total - totalInfo.answeredCnt;
		str+='<table class="table table-bordered border-radius-0 mb-0 table-hover">';
		str+='<tr>';
		str+='<td>';
		str+='<h2 >AP &nbsp;& TS</h2>';
		str+='</td>';
		str+='<td><h2 id="totalRegCntId"> '+totalRegistered +'</h2><p>Total Members Registered </p></td>';
		str+='<td><h2 id="printedId">'+basicInfo.printingCompleted+'</h2><p>Cards <b>Printed</b> <br><span class="text-red" id="pendingId">'+pending+'</span> Pending</p>';
		str+='<hr style="margin-top: 0px; margin-bottom: 0px;" id="readyIvrId"> Ready For IVR calls '+basicInfo.ivrReady+'</td>';
		str+='<td><h5 class="mb-0" id="totalIvrId"> '+totalInfo.total+'<br><small>Dialed IVR Calls</small></h5>';
		str+='<hr style="margin-top: 0px; margin-bottom: 0px;"><h2 class="m-0" id="answerIvrId">'+totalInfo.answeredCnt+'</h2><p class="mb-0"><b>IVR</b> Calls<span class="text-success"> Answered </span></p><hr style="margin-top: 0px; margin-bottom: 0px;"> <span class="text-error">IVR Errors- </span><p>'+ivrError+'</p></td>';
		str+='<td><h2 id="receivedId">'+totalInfo.received+'</h2><p>Cards  <span class="text-orange">Received</span></p></td>	';
		str+='<td><h2 id="notreceivedId">'+totalInfo.notReceived+'</h2><p>Cards  <span class="text-orange">Not Received</span></p></td>';	
		str+='<td><h2 id="notRegisteredId">'+totalInfo.notRegistered +'</h2><p>Not Registered Member Phone Numbers</p></td>	';
		str+='</tr>';
		str+='</table>';

		$("#APandTsDiv").html(str);
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
getDateWiseIVRCountLoad("AP");
getDateWiseIVRCountLoad("TS");
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
<script type="text/javascript">
$(document).ready(function() {
  $('input[name="daterange"]').daterangepicker();
});
</script>
</body>
</html>