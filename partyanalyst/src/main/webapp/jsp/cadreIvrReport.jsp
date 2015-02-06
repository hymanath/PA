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
	.width25p{width:150px !important;}
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
.mandatory {
    color: #ff0000;
}
textarea {
    border: 1px solid #ddd !important;
}
.ui-dialog .ui-dialog-titlebar-close span {left: 0px; bottom: 0px; right: 0px; top: 0px;}
	</style>
</head>

<body>
	<div class="container ">
    <div id="prevCallDetailsShowOuter" style="display:none;"><div id="prevCallDetailsShowInner"></div></div>	
	<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-0 ">
				<h3 class="text-center text-uppercase">IVR  STATUS REPORTS DASHBOARD</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Filters Row -->
		<div>
		<c:if test="${sessionScope.USER.accessType == 'STATE'}">
		<button type="button" value="" style="margin-top: -29px; margin-left: 0px; display: block; border-left-width: 4px; padding-left: 0px; padding-right: 0px; border-top-width: 0px;" class="btn btn-medium btn-success border-radius-0 pull-right" onclick="openPopupWindow();">Click to View Info Managers Calls Details</button></div>
		</c:if>
		<div id="fadeInDown" class="row-fluid">
		
			<div class="span12 well well-small  border-radius-0 mb-10 ">
		<c:if test="${sessionScope.USER.accessType == 'STATE'}">		
		<ul class="inline" style="margin-bottom: 0px;"><li>
		<input type="radio" class="radioCls" style="margin-top: -2px;" name="stateradio" value="All" checked> </li>All
			<li><input type="radio" class="radioCls"  value="AP" name="stateradio" style="margin-top: -2px;"> Andhra Pradesh  </li>
			
			<li><input type="radio" class="radioCls"  value="TS" name="stateradio" style="margin-top: -2px;"> Telangana  </li>
			
			</ul>
			</c:if>
			<c:if test="${sessionScope.USER.accessType != 'STATE'}">
			<input type="radio" class="radioCls" style="display:none;" name="stateradio" value="All" checked="checked">
			</c:if>
				<!-----date picker----->
				
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
				<div style="background: none repeat scroll 0% 0% rgb(255, 255, 255); cursor: pointer; padding: 5px 10px; border: 1px solid rgb(204, 204, 204); margin-top: -25px;" class="pull-right" id="daterange">
                 </c:if> 
				 <c:if test="${sessionScope.USER.accessType != 'STATE'}">
				   <div style="background: none repeat scroll 0% 0% rgb(255, 255, 255); cursor: pointer; padding: 5px 10px; border: 1px solid rgb(204, 204, 204);" class="pull-right" id="daterange">
                 </c:if>
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
						<!--<td style="width:173px;text-align:center;">
						<img style="height:18px;width:16px;margin-top:80px;margin-left:1px;" src="./images/icons/search.gif" id="ajaxImg" class="offset1"/>
						<div id="registrationTD">
						</div>
						</td>-->
						<td style="text-align:center; width:20%;" rowspan="2" id="totalIvrTD">
							
						</td>
						<td style="width:40%;" rowspan="2"  id="successIvrTD">
							
						</td>
						<td style="width:40%;" rowspan="2" style="230px !important;"  id="errorIvrTD">
							
						</td>	
							
					</tr>
					<!--<tr>
						<td class="width25p" id="printingTD" style="text-align:center;">
							
						</td>
						
					</tr>-->
					
				</table>
			</div>	
			<!----- /Total In AP & TS ----->
			
		</div>	
		
		<div class="row-fluid m_top10">
			<div class="span12 well well-small border-radius-0 mb-0 ">
				<h4 class="m-0" style=" display: inline;"><span id="districtConstituencyHeading">District Wise Card Not Received Response</span></h4> 			
				
				<span id="constiDistMainOutDiv"><input type="button" id="constiDistMainDiv" onclick="getOtherLocationsInfo();" class="btn btn-medium btn-success border-radius-0 pull-right " style="margin-right: 10px; margin-top: -5px; margin-bottom: -5px;" value="Click to view Constituency Wise" /></span>&nbsp; &nbsp; 
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
var isDistrictRequired = false;
var selectedLocation ="district";
var tgIds ="8,4,295,296,2,5,1,3,7,6,10,13,15,12,18,16,342,343,11,321,23,322,318,26,30,24,20,323,31,319,21,320,40,35,36,32,37,41,39,337,336,34,57,367,345,346,347,56,348,349,351,350,55,58,60,59,50,49,315,47,314,51,316,46,317,44,43,54,313,52,53,66,335,68,64,369,69,73,63,62,70,61,65,71,67,77,338,79,339,78,84,82,75,81,85,74,89,87,362,86,91,93,94,363,364,97,365,92,324,107,101,104,103,325,105,102,326,100,80";
var apIds ="111,352,117,114,116,108,109,112,353,113,360,124,125,122,120,121,361,129,127,368,354,355,356,357,358,134,136,359,138,133,141,135,140,137,163,157,156,307,155,147,308,159,153,146,160,310,152,309,303,304,305,149,306,172,366,181,174,173,167,179,178,177,180,169,170,171,176,168,194,193,184,185,187,327,328,182,329,330,196,331,195,191,192,186,210,215,206,211,217,213,216,209,212,312,311,199,208,214,207,203,205,344,221,228,218,219,229,227,223,225,226,222,224,232,241,233,340,341,236,231,237,239,238,242,252,243,246,248,251,245,244,250,249,254,332,261,260,263,262,333,257,264,258,265,334,253,255,276,279,297,278,277,298,272,299,273,270,275,300,267,271,290,285,294,286,280,291,289,288,283,301,281,302,284,282";
<c:if test="${sessionScope.USER.accessType == 'STATE' || sessionScope.USER.accessType == 'DISTRICT'}">
 isDistrictRequired = true;
</c:if>
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
					
                    });
					   

                  $('#daterange').on('hide.daterangepicker', function() { 
					   
						
					   });
				
                  $('#daterange').on('apply.daterangepicker', function(ev, picker) { 
					   
                  
					  getDateWiseIVRCount();
					  selectedLocation ="district";
						$("#constiDistMainDiv").attr("value","Click to view Constituency Wise");
						$("#districtConstituencyHeading").html("District Wise Card Not Received Response");
						if(isDistrictRequired){
						  getDistrictConstiWisePerformance("district","");
						}else{
							
						  $("#constiDistMainOutDiv").hide();
						  getDistrictConstiWisePerformance("constituency",apIds+","+tgIds);
						}
						$("#allErrorsInfoLocationWiseOuter").html("");
						getLocationWisePerformance(232,$('input[name=stateradio]:checked').val());
						
                  });
				 
                  $('#daterange').on('cancel.daterangepicker', function(ev, picker) {  });
			$("#selectedDate").html(''); 
				
	}

		$(".radioCls").click(function() {
		
			 dateRange();
			//getIvrBasicCount();
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
		
		str+='<canvas id="printPercChart" style="margin-top: 5px; width: 300px; height: 150px; margin-left: -97px;" height="150" width="300"></canvas>';
		str+='<div  style="margin-top: -103px; margin-left: 82px; margin-right: -32px">';
		str+='<ul class="doughnut-legend">';
		str+='<li><span style="background-color: #5CB65C; width: 10px; height: 10px; display: block; margin-left: 73px; margin-bottom: -12px;"></span><p style="margin: 0px 0px 0px 61px; padding-bottom: 14px;">Cards Printed</p></li>';
		str+='<li><span style="background-color: #005580; width: 10px; height: 10px; display: block; margin-left: 72px; margin-top: 0px;"></span><p style="margin-top: -14px; margin-right: 0px; margin-left: 34px;">Pending</p></li>';
		str+='</ul>';
		str+='<li><span style="background-color: rgb(209, 71, 65); width: 10px; height: 10px; display: block; margin-left: 97px; margin-top: -12px;"></span><p style="margin-left: 39px; margin-top: -13px; margin-bottom: 0px;">Error</p></li>';
		str+='</ul>';
		str+='</div>';
		str+='<div style="margin-top: 26px;">';
		str+='<h4 class="m-0">'+result.count+'</h4>';
		if(state == "All"){
		<c:if test="${sessionScope.USER.accessType == 'STATE'}">
		   str+='<p class="m-0" >Members Registered in Andhra Pradesh And Telangana</p>';
		</c:if>
		<c:if test="${sessionScope.USER.accessType != 'STATE'}">
		   str+='<p class="m-0" >Members Registered</p>';
		</c:if>
		}else if(state == "AP")
		str+='<p class="m-0" >Members Registered in Andhra Pradesh </p>';
		else if(state == "TS")
		str+='<p class="m-0" >Members Registered in Telangana</p>';
		str+='</div>';
		$("#registrationTD").html(str);
		var str1='';
		str1+='<canvas id="piep"  style="margin-top: 5px; width: 300px; height: 150px; margin-left: -99px;" height="150" width="300"></canvas>';
		str1+='<div  style="margin-top: -103px; margin-left: 82px; margin-right: -32px">';
		str1+='<ul class="doughnut-legend">';
		str1+='<li><span style="background-color: #5CB65C; width: 10px; height: 10px; display: block; margin-left: 73px; margin-bottom: -12px;"></span><p style="margin: 0px 0px 0px 61px; padding-bottom: 14px;">Avail for IVR</p></li>';
		str1+='<li><span style="background-color: #D14741; width: 10px; height: 10px; display: block; margin-left: 72px; margin-top: 0px;"></span><p style="margin-top: -14px; margin-right: 0px; margin-left:54px;">Send to IVR</p></li>';
		str1+='</ul>';
		str1+='</div>';
		str1+='<div  style="margin-top: 48px;">';
		str1+='<h4 class="m-0">'+result.printingCompleted+'</h4>';
		if(state == "All"){
		<c:if test="${sessionScope.USER.accessType == 'STATE'}">
		   str1+='<p class="m-0" >Cards Printed in Andhra Pradesh And Telangana</p>';
		</c:if>
		<c:if test="${sessionScope.USER.accessType != 'STATE'}">
		   str1+='<p class="m-0" >Cards Printed</p>';
		</c:if>
		
		}else if(state == "AP")
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
        animateScale : false
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
		var unAnswered= (result[0].totalUnAnswered / result[0].total * 100);
		
		var str ='';
		str+='<canvas id="answerErrorChart"  height="180" width="220" style=" margin-top: 35px; width: 220px; height: 180px;"></canvas>';
		str+='<div id="legend" style="margin-top:10px;margin-left:30px;">';
		str+='<ul class="doughnut-legend text-left">';
		str+='<li><span style="background-color: rgb(92, 182, 92); width: 10px; height: 10px; display:inline-block; "></span> IVR Answered</li>';
		str+='<li><span style="background-color: rgb(209, 71, 65); width: 10px; height: 10px; display: inline-block; "></span> IVR Error</li>';
		str+='<li><span style="background-color: rgb(0, 85, 128); width: 10px; height: 10px; display: inline-block;"></span> IVR UnAnswered</li>';
		str+='</ul>';
		str+='</div>';
		str+='<div class="text-center" style="margin-top:0px;">';	
		str+='<h2 class="m-0">'+result[0].total+'</h2>';
		str+='<p>Calls IVR Total <br>Dailled</p>';
		str+='</div>';
		$("#totalIvrTD").html(str);
		options = {		
        animateRotate : false,
        animateScale : false		
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
			{
		value: unAnswered,
        color:"#005580",
        //highlight: "#D7C482",
        label: "IVR UnAnswered",
		labelFontSize: '8'
		},
		
		
		]
		
		 ctx = $("#answerErrorChart").get(0).getContext("2d");
	     myNewChart = new Chart(ctx).Doughnut(data,options);
		 // var legend = myNewChart.generateLegend();
//$("#legend").html(legend);
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
	<!--selectedOptPerc = 100 - result[0].noOptionPerc;  -->
	var str='';
	str+='<div class="row-fluid">';
	str+='<h4 class="m-0" style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;color:#5cb55c;">ANSWERED CALLS</h4>';
	str+='<h6 class="label" style="width: 180px; margin-left: 2px; margin-right: -55px;">Selected Any Option : '+result[0].selectedOptionCntPerc+' %</h6>';
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
	
	//str1+='<h6 class="label" style="width: 180px; margin-left: 2px; margin-right: -55px;">Error Calls : '+result[0].totalErrorPerc+' %<span>&nbsp;&nbsp;</h6>';
	str1+='<li>';	
	str1+='<b>Network Error <span>&nbsp;&nbsp;'+result[0].newtworkErrorPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-success mb-10">';
	str1+='<div class="bar" style="width: '+result[0].newtworkErrorPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';	
	str1+='<b>Congestion Error<span>&nbsp;&nbsp;'+result[0].switchCongestionPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].switchCongestionPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li>';	
	str1+='<b>Unallocated Numbers<span>&nbsp;&nbsp;'+result[0].unallocatedNumbersPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].unallocatedNumbersPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li>';	
	str1+='<b>Interworking Error<span>&nbsp;&nbsp;'+result[0].interworkingCountPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].interworkingCountPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='<li style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;">';	
	str1+='<b>Other <span>&nbsp;&nbsp;'+result[0].otherErrorPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-danger mb-10">';
	str1+='<div class="bar" style="width: '+result[0].otherErrorPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	str1+='</ul>';
	
	str1+='</div>'
	
	str1+='<div class="row-fluid">';
	str1+='<h4 class="m-0" style="border-bottom: 1px solid rgb(204, 204, 204); padding-bottom: 10px;color:#005580;">IVR UNANSWERED CALLS</h4>';
	//str1+='<h6 class="label" style="width: 180px; margin-left: 2px; margin-right: -55px;">Unanswered Calls : '+result[0].totalUnAnsweredPerc+' %<span>&nbsp;&nbsp;</h6>';
	str1+='<ul class="unstyled">';
	str1+='<li>';
	str1+='<b>Rejected Calls  <span>&nbsp;&nbsp;'+result[0].callRejectedPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-success mb-10">';
	str1+='<div class="bar" style="width: '+result[0].callRejectedPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	
	str1+='<li>';
	str1+='<b>User Busy  <span>&nbsp;&nbsp;'+result[0].userBusyPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-success mb-10">';
	str1+='<div class="bar" style="width: '+result[0].userBusyPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	//str1+='<li style="border-bottom: 2px solid rgb(204, 204, 204); padding-bottom: 20px;">';
	str1+='<li>';
	str1+='<b>No Answer<span>&nbsp;&nbsp;'+result[0].noAnswerPerc+'%</span></b>';
	str1+='<div style="height:5px;" class="progress progress-warning mb-10">';
	str1+='<div class="bar" style="width: '+result[0].noAnswerPerc+'%"></div>';
	str1+='</div>';
	str1+='</li>';
	
	str+='</ul>';
	
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
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
				   str+="				<th rowspan='2'>Job ID's</th>";
				</c:if>
				str+='				<th rowspan="2">IVR Calls Dailed</th>';
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
					str+='				<th colspan="2">Answered Calls</th>';
					str+='				<th colspan="2">Not Selected Any option</th>';
					str+='				<th colspan="2">Error Calls</th>';
				</c:if>
				str+='				<th colspan="2">Card Received</th>';
				str+='				<th colspan="2">Card Not Received</th>';
				str+='				<th colspan="2">Member NOT Registered</th>';
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
					str+='				<th colspan="2">Wrong option Selected</th>';
				</c:if>
				str+='		</tr>';
				str+='	    <tr class="well">';
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
					str+='				<th>Count</th>';
					str+='				<th>%</th>';
					str+='				<th>Count</th>';
					str+='				<th>%</th>';
					str+='				<th>Count</th>';
					str+='				<th>%</th>';
				</c:if>
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				str+='				<th>Count</th>';
				str+='				<th>%</th>';
				<c:if test="${sessionScope.USER.accessType == 'STATE'}">
					str+='				<th>Count</th>';
					str+='				<th>%</th>';
				</c:if>
				str+='		</tr>';
				str+='	</thead>';
				str+='	<tbody>';
				for(var i in result.apList){
				  str+='<tr>';
				  if(locationType =="All" || locationType =="AP" || locationType =="TS"){
				    str+='  <td><a href="javascript:{}" title="Click Here To View Calls Info" onclick="getEnquiryInfo(\'constituency\',\''+result.apList[i].id+'\',\''+result.apList[i].name+'\')">'+result.apList[i].name+'</a><span style="cursor:pointer;" title="Click Here To Add Call Details" onclick="openSaveEnquiryInfo(\'constituency\','+result.apList[i].id+','+result.apList[i].id+',\''+result.apList[i].name+'\')"><i class="icon-list-alt"></i></span></td>';
				  }else if(locationType == "mandal"){
				    str+='  <td><a href="javascript:{}" title="Click Here To View Calls Info" onclick="getEnquiryInfo(\'tehsil\',\''+result.apList[i].id+'\',\''+result.apList[i].name+'\')">'+result.apList[i].name+'</a><span style="cursor:pointer;" title="Click Here To Add Call Details" onclick="openSaveEnquiryInfo(\'mandal\','+result.apList[i].id+','+constituencyId+',\''+result.apList[i].name+'\')"><i class="icon-list-alt"></i></span></td>';
				  }else{
				    str+='  <td>'+result.apList[i].name+'</td>';
				  }
				  str+='  <td>'+result.apList[i].registeredCount+'</td>';
				  str+='  <td>'+result.apList[i].printedCount+'</td>';
				  <c:if test="${sessionScope.USER.accessType == 'STATE'}">
				     str+='  <td>'+result.apList[i].jobCode+'</td>';
				  </c:if>
				  str+='  <td>'+result.apList[i].totalIvrCalls+'</td>';
				  <c:if test="${sessionScope.USER.accessType == 'STATE'}">
					  str+='  <td>'+result.apList[i].totalAnswerdCalls+'</td>';
					  str+='  <td>'+result.apList[i].totalAnswerdPerc+'</td>';
					  str+='  <td>'+result.apList[i].noOptionSel+'</td>';
					  str+='  <td>'+result.apList[i].noOptionSelPerc+'</td>';
					  str+='  <td>'+result.apList[i].errorCalls+'</td>';
					  str+='  <td>'+result.apList[i].errorCallsPerc+'</td>';
				  </c:if>
				  str+='  <td>'+result.apList[i].received+'</td>';
				  str+='  <td>'+result.apList[i].receivedPerc+'</td>';
				  str+='  <td>'+result.apList[i].notReceived+'</td>';
				  str+='  <td>'+result.apList[i].notReceivedPerc+'</td>';
				  str+='  <td>'+result.apList[i].notMember+'</td>';
				  str+='  <td>'+result.apList[i].notMemberPerc+'</td>';
				  <c:if test="${sessionScope.USER.accessType == 'STATE'}">
					str+='  <td>'+result.apList[i].wrongOptionSel+'</td>';
					str+='  <td>'+result.apList[i].wrongOptionSelPerc+'</td>';
				  </c:if>
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
function getOtherLocationsInfo(){
  $("#allErrorsInfoLocationWiseOuter").html('');
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
    //getLocationWisePerformance(232,radioVal);
	getLocationWisePerformance(232,$('input[name=stateradio]:checked').val());
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
 function openPopupWindow()
 {
	 var urlstr = "cadreIvrEnquiryAction.action";
	var browser1 = window.open(urlstr,"Ivr Details","scrollbars=yes,height=600,width=1050,left=200,top=200");	
	browser1.focus();
 } 
 function saveEnquiryInfo(locationName,locationId,details,mobile,received,delivered,callStatus,constituencyId,designation){
      $("#successMesDiv").html("");
	  $("#errorMesDiv").html("");
	  
      var jsObj = {	
			locationName:locationName,
			locationId:locationId,
			details : details,
			mobile : mobile,
            received:received,
            delivered:delivered,
            callStatus:callStatus,
            constituencyId:constituencyId,
			designation :designation
		}
     $.ajax({
			type : "POST",
			url : "saveInchargeEnquiryInfo.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		   $("#ajaxImgStyle").hide();
		    $("#submitDataForEnquiryInfoId").removeAttr("disabled");
			if(result == "success"){
				$("#detailsRId").val("");
				$("#designationId").val("");
				$("#mobileRId").val("");
				$("#receivedRId").val("");
				$("#deliveredRId").val("");
				$("#callStatusRId").val("");
				$("#successMesDiv").html("Call Details Saved SuccessFully");
			}else{
			  if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
			    $("#errorMesDiv").html("Your Session Expired.Please login.");
			  }else{
			    $("#errorMesDiv").html("Error Occured.Please try again later.");
			  }
			}
        });
 }
 function getEnquiryInfo(locationLvl,locationValue,locationName){
 
 $('#prevCallDetailsShowInner').html('<img src="images/Loading-data.gif" style="margin-left: 380px;margin-top: 78px;width:70px;height:60px;">');
 var name="";
 if(locationLvl == "constituency"){
	name = ""+locationName+" Constituency";
 }else
 {
	name = locationName;
 }
  $('#prevCallDetailsShowOuter').dialog(
	{
		width : 850,
		height:550,
		title : ""+name+" Calls Info"
	});
 if(locationLvl == "tehsil"){
    if(locationValue.charAt(0) =="1"){
	  locationLvl = "localElecBody";
	}
	locationValue = locationValue.slice(1, locationValue.length);
 }
      var jsObj = {	
			locationLvl:locationLvl,
			locationValue:locationValue
		}
     $.ajax({
			type : "POST",
			url : "getInchargeEnquiryInfo.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		   buildDetailsForLocation(result)
        });
 }
 
 function buildDetailsForLocation(result) {
       if(result.apList != null && result.apList.length >0){
			var str = '';
			str += '<table class="table table-bordered m_top20 "  style="width:850px;">';
			str += '<thead>';
			str += '<tr>';
			str += '<th> Location  </th>';
			str += '<th> Date  </th>';
			str += '<th> Name  </th>';
			str += '<th> Mobile  </th>';
			str += '<th> Received  </th>';
			str += '<th> Distributed  </th>';
			str += '<th> Comment  </th>';
			str += '</tr>';
			str += '</thead>';

			str += '<tbody>';
             var reqRes =result.apList;
					for ( var i in reqRes) {
							str += '<tr>';
							str += '  <td>' +reqRes[i].locationName+ '</td>';
							str += '  <td>' +reqRes[i].date+ '</td>';
							str += '  <td>'+ reqRes[i].name+ '</td>';
							str += '  <td>'+ reqRes[i].jobCode+ '</td>';
							if(reqRes[i].received != null){
							   str += '  <td>' +reqRes[i].received+ '</td>';
							}else{
							  str += '  <td></td>';
							}
							if(reqRes[i].notReceived != null){
							  str += '  <td>' +reqRes[i].notReceived+ '</td>';
							 }else{
							  str += '  <td></td>';
							}
							str += '  <td>' +reqRes[i].areaName+ '</td>';
							str += '</tr>';
					}
			str += '</tbody>';
			str += '</table>';

			   $('#prevCallDetailsShowInner').html(str);
		}else{
              $('#prevCallDetailsShowInner').html('<div style="font-weight:bold;padding:20px;margin-left:375px;">No Data Available</div>');
        }		
}
 function openSaveEnquiryInfo(locationType,locationId,constituencyId,locationNme){
 
 var name="";
 if(locationType =="constituency"){
 name = " "+locationNme+" Constituency";
 }else{
 name=locationNme;
 }
         $('#prevCallDetailsShowOuter').dialog(
			{
				width : 850,
				height:550,
				title : " "+name+" Calls Info"
			});
			var str="<div style='color:red;' id='errorMesDiv'/>";
			str +="<div style='color:green;' id='successMesDiv'/>";
			str += '<table class="table table-bordered m_top20 "  style="width:850px;">';
			str += '<tr><th> Name<span class="mandatory">*</span> </th><td><input type="text" id="detailsRId"></td></tr>';
			str += '<tr><th> Designation<span class="mandatory">*</span> </th><td><input type="text" id="designationId"></td></tr>';
			str += '<tr><th> Mobile<span class="mandatory">*</span>  </th><td><input type="text" id="mobileRId"></td></tr>';
			str += '<tr><th> Cards Received Count<span class="mandatory">*</span> </th><td><input type="text" id="receivedRId"></td></tr>';
			str += '<tr><th> Cards Delivered Count<span class="mandatory">*</span>  </th><td><input type="text" id="deliveredRId"></td></tr>';
			str += '<tr><th> Comment  </th><td><textarea id="callStatusRId"/></td></tr>';
			str += '<tr><th>   </th><td><input type="button" id="submitDataForEnquiryInfoId" class="btn btn-success" onclick="submitDataForEnquiryInfo(\''+locationType+'\','+locationId+','+constituencyId+');" value="Submit"/><img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/></td></tr>';
			str += '</table>';
			
    $('#prevCallDetailsShowInner').html(str);
 }
 function submitDataForEnquiryInfo(locationType,locationId,constituencyId){
  $("#errorMesDiv").html("");
  $("#successMesDiv").html("");
  var isError = false;
  var details = $.trim($("#detailsRId").val());
  var mobile = $.trim($("#mobileRId").val());
  var received = $.trim($("#receivedRId").val());
  var delivered = $.trim($("#deliveredRId").val());
  var designation=$.trim($("#designationId").val());
  var rStr="";
  if(details.length == 0){
    isError = true;
	rStr+="<div>Name is required.</div>";
  }
  if(designation.length ==0){
	isError = true;
	rStr+="<div>Designation is required.</div>";
  }
  if(mobile.length == 0){
    isError = true;
	rStr+="<div>Mobile is required.</div>";
  }else if(isNaN(mobile)){
    isError = true;
	rStr+="<div>Mobile must be number.</div>";
  }else if(mobile.length != 10){
    isError = true;
	rStr+="<div>Mobile must be 10 digits.</div>";
  }
  if(received.length == 0){
    isError = true;
	rStr+="<div>Cards Received Count is required.</div>";
  }else if(isNaN(received)){
    isError = true;
	rStr+="<div>Cards Received Count must be number.</div>";
  }
  if(delivered.length == 0){
    isError = true;
	rStr+="<div>Cards Delivered Count is required.</div>";
  }else if(isNaN(delivered)){
    isError = true;
	rStr+="<div>Cards Delivered Count must be number.</div>";
  }
 if(isError){
    $("#errorMesDiv").html(rStr);
   return;
 }
  
  $("#submitDataForEnquiryInfoId").attr("disabled","disabled");
  $("#ajaxImgStyle").show();
 saveEnquiryInfo(locationType,locationId,details,mobile,received,delivered,$("#callStatusRId").val(),constituencyId,designation);
 
 }
</script>
<script>
//getIvrBasicCount();
getDateWiseIVRCount();
if(isDistrictRequired){
  getDistrictConstiWisePerformance("district","");
}else{
  $("#constiDistMainOutDiv").hide();
  $("#districtConstituencyHeading").html("Constituency Wise Card Not Received Response");
  getDistrictConstiWisePerformance("constituency",apIds+","+tgIds);
}
getLocationWisePerformance(232,"All");
</script>



</body>
</html>