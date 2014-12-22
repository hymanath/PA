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
	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
 <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
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
	</style>
</head>
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
				<img style="width:70px;" src="./images/Loading-data.gif" id="ajaxImg"/>
			</div>	
			<!----- /Total In AP & TS ----->
			
			<!-----Today In AP ----->
			<div class="span6 m_top20" id="todayApDiv">
			<img style="width:70px;" src="./images/Loading-data.gif" id="todayAPajaxImg"/>
						
			</div>
			<!----- /Today In AP ----->
						
			<!-----Today In TS ----->
			<div class="span6 m_top20" id="todayTGDiv">
				<img style="width:70px;" src="./images/Loading-data.gif" id="todayTGajaxImg"/>			
			</div>
			<!----- /Today In TS ----->
			
			<!------ Total in AP ------->
			<div class="span6 m_top20" style="outline:6px solid rgb(223, 240, 216);" id="ApDataDiv">
			<div id="ApTotalDiv">
				<img style="width:70px;" src="./images/Loading-data.gif" id="apTotalajaxImg"/>
				</div>
				<!-----TS Constituency wise ------>
				<h4 class="alert alert-info text-center border-radius-0 m-0">CONSTITUENCY WISE DETAILS</h4>
				<div style="overflow: auto;">
					<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 ">
						<thead class="alert-info">
							<tr>
								<th>Constituency Name</th>
								<th>No of members registered</th>
								<th>IVR Calls </th>
								<th>Cards Received </th>
								<th>Cards Not Received </th>
								<th>Not Registered Members </th>
							</tr>
						</thead>
						<tbody>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						</tbody>
					</table>
				</div>
				<!----- /AP Constituency wise ------>
				
				<!------AP District wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">DISTRICT WISE DETAILS</h4>
				<div style="overflow: auto;">
					<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 ">
						<thead class="alert-info">
							<tr>
								<th>District Name</th>
								<th>No of members registered</th>
								<th>IVR Calls </th>
								<th>Cards Received </th>
								<th>Cards Not Received </th>
								<th>Not Registered Members </th>
							</tr>
						</thead>
						<tbody>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						</tbody>
					</table>
				</div>
				<!------/AP District wise -------->
			</div>
			
			<!----- /Total in AP ----->
			
			<!-------Total in TS------>
			<div class="span6 m_top20" style="outline:6px solid rgb(223, 240, 216);" id="TGDataDiv">
				<div id="TGTotalDiv">
				<img style="width:70px;" src="./images/Loading-data.gif" id="TGTotalajaxImg"/>
				</div>
				<!-----TS Constituency wise ------>
				<h4 class="alert alert-info text-center border-radius-0 m-0">CONSTITUENCY WISE DETAILS</h4>
				<div style="overflow: auto;">
					<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 ">
						<thead class="alert-info">
							<tr>
								<th>Constituency Name</th>
								<th>No of members registered</th>
								<th>IVR Calls </th>
								<th>Cards Received </th>
								<th>Cards Not Received </th>
								<th>Not Registered Members </th>
							</tr>
						</thead>
						<tbody>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>Constituency</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						</tbody>
					</table>
				</div>
				<!-----/TS Constituency wise ------>
				
				<!------TS District wise -------->
				<h4 class="alert alert-info text-center border-radius-0 m-0">DISTRICT WISE DETAILS</h4>
				<div style="overflow: auto;">
					<table class="table table-bordered border-radius-0 table-condensed table-hover mb-0 ">
						<thead class="alert-info">
							<tr>
								<th>District Name</th>
								<th>No of members registered</th>
								<th>IVR Calls </th>
								<th>Cards Received </th>
								<th>Cards Not Received </th>
								<th>Not Registered Members </th>
							</tr>
						</thead>
						<tbody>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						<tr><td>District Name</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td><td>4545555</td></tr>
						</tbody>
					</table>
				</div>
				<!------/TS District wise -------->
			</div>
		</div>
		
		
		
	</div>
	
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	
<script>
function showHide()
{
    var value =$('input:radio[name=searchType]:checked').val();;
	$("#countDiv").hide();
	if(value == 1)
	{
		$("#dateId").css("display","block");
		$("#constituencyId").val(0);
		$("#constituencyId").css("display","none");
	}
	else
	{
$("#dateId").css("display","none");
$("#dateId").val(0);
$("#constituencyId").css("display","block");
	}
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
		 $("#todayAPajaxImg").show();
		 $("#todayTGajaxImg").show();
		  $("#apTotalajaxImg").show();
		  $("#TGTotalajaxImg").show();
			var jsObj = {	
			task:""             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			 $("#ajaxImg").hide();
			 $("#todayAPajaxImg").hide();
			 $("#todayTGajaxImg").hide();
			 $("#apTotalajaxImg").hide();
			 $("#TGTotalajaxImg").hide();
			buildIvrCount(result);
		});
	}
	function buildIvrCount(result)
	{
		$("#APandTsDiv").html('');
		$("#todayApDiv").html('');
		$("#todayTGDiv").html('');
		$("#ApTotalDiv").html('');
		$("#TGTotalDiv").html('');
		var str ='';
		var basicInfo = result[0] ;
		var todayInfo = result[1];
		var totalInfo = result[2];
		var totalRegistered = basicInfo.apCount + basicInfo.tgCount;
		var pending  = totalRegistered - basicInfo.printingCompleted;
		str+='<table class="table table-bordered border-radius-0 mb-0 table-hover">';
		str+='<tr>';
		str+='<td>';
		str+='<h2 >AP &nbsp;& TS</h2>';
		str+='</td>';
		str+='<td><h2 id="totalRegCntId"> '+totalRegistered +'</h2><p>Total Members Registered </p></td>';
		str+='<td><h2 id="printedId">'+basicInfo.printingCompleted+'</h2><p>Cards <b>Printed</b> <br><span class="text-red" id="pendingId">'+pending+'</span> Pending</p>';
		str+='<hr style="margin-top: 0px; margin-bottom: 0px;" id="readyIvrId"> Ready For IVR calls '+basicInfo.ivrReady+'</td>';
		str+='<td><h5 class="mb-0" id="totalIvrId"> '+(totalInfo.apCount + totalInfo.tgCount)+'<br><small>Dialed IVR Calls</small></h5>';
		str+='<hr style="margin-top: 0px; margin-bottom: 0px;"><h2 class="m-0" id="answerIvrId">'+(totalInfo.responseCnt + totalInfo.tgResponseCnt)+'</h2><p class="mb-0"><b>IVR</b> Calls<span class="text-success"> Answered </span></p><hr style="margin-top: 0px; margin-bottom: 0px;"> <span class="text-error">IVR Errors- </span><p></p></td>';
		str+='<td><h2 id="receivedId">'+(totalInfo.received + totalInfo.tgReceived)+'</h2><p>Cards  <span class="text-orange">Received</span></p></td>	';
		str+='<td><h2 id="notreceivedId">'+(totalInfo.notReceived + totalInfo.tgnotReceived)+'</h2><p>Cards  <span class="text-orange">Not Received</span></p></td>';	
		str+='<td><h2 id="notRegisteredId">'+(totalInfo.notRegistered + totalInfo.tgnotRegistered)+'</h2><p>Not Registered Member Phone Numbers</p></td>	';
		str+='</tr>';
		str+='</table>';
		
		var str1=''
		str1+='<table class="table table-bordered border-radius-0 mb-0 table-striped">';
		str1+='<tr class="alert alert-success">';
		str1+='<td colspan="4">';
		str1+='<h4>Today AP IVR Calls Answered <span class="pull-right">'+todayInfo.responseCnt +'</span></h4>';	
		str1+='</td>';
		str1+='</tr>';
		str1+='<tr>';
		str1+='<td><h2>'+todayInfo.received+'</h2><p>Cards  <span class="text-orange">Received</span></p></td>';	
		str1+='<td><h2>'+todayInfo.notReceived+'</h2><p>Cards  <span class="text-orange">Not Received</span></p></td>';	
		str1+='<td><h2>'+todayInfo.notRegistered+'</h2><p>Not Registered Member Phone Numbers</p></td>	';
		str1+='</tr>';
		str1+='</table>';

		var str2=''
		str2+='<table class="table table-bordered border-radius-0 mb-0 table-striped">';
		str2+='<tr class="alert alert-success">';
		str2+='<td colspan="4">';
		str2+='<h4 >Today AP IVR Calls Answered <span class="pull-right">'+todayInfo.tgResponseCnt +'</span></h4>';	
		str2+='</td>';
		str2+='</tr>';
		str2+='<tr>';
		str2+='<td><h2>'+todayInfo.tgReceived+'</h2><p>Cards  <span class="text-orange">Received</span></p></td>';	
		str2+='<td><h2>'+todayInfo.tgnotReceived+'</h2><p>Cards  <span class="text-orange">Not Received</span></p></td>';	
		str2+='<td><h2>'+todayInfo.tgnotRegistered+'</h2><p>Not Registered Member Phone Numbers</p></td>	';
		str2+='</tr>';
		str2+='</table>';

		var str3 = '';
		str3+='<table class="table table-bordered border-radius-0 mb-0 table-condensed">';
		str3+='<tr class="alert alert-success">';
		str3+='<td rowspan="4" style="text-align: center;">';
		str3+='<img style="width:70px" src="images/AP.png">';
		str3+='<h4 >Total In AP <br>'+ basicInfo.apCount+'  <br><small>Members Registered </small> <hr style="margin-top: 5px; margin-bottom: 5px;">IVR Calls<br>'+totalInfo.responseCnt +' <br><small>Answered</small>	</h4>';
		str3+='</td>';
		str3+='</tr>';
		str3+='<tr>';
		str3+='<td><h5>'+totalInfo.received+'</h5><p>Cards  <span class="text-orange">Received</span></p></td>';
		str3+='</tr>';
		str3+='<tr>';
		str3+='<td><h5>'+totalInfo.notReceived+'</h5><p>Cards  <span class="text-orange">Not Received</span></p></td>	';
		str3+='</tr>';
		str3+='<tr>';
		str3+='<td><h5>'+totalInfo.notRegistered+'</h5><p>Not Registered Member Phone Numbers</p></td>';	
		str3+='</tr>';
		str3+='</table>';
		var str4 = '';
		str4+='<table class="table table-bordered border-radius-0 mb-0 table-condensed">';
		str4+='<tr class="alert alert-success">';
		str4+='<td rowspan="4" style="text-align: center;">';
		str4+='<img style="width:70px" src="images/TS.png">';
		str4+='<h4 >Total In TG <br>'+basicInfo.tgCount+' <br><small>Members Registered </small> <hr style="margin-top: 5px; margin-bottom: 5px;">IVR Calls<br>'+totalInfo.tgResponseCnt+'  <br><small>Answered</small>	</h4>';
		str4+='</td>';
		str4+='</tr>';
		str4+='<tr>';
		str4+='<td><h5>'+totalInfo.tgReceived+'</h5><p>Cards  <span class="text-orange">Received</span></p></td>';
		str4+='</tr>';
		str4+='<tr>';
		str4+='<td><h5>'+totalInfo.tgnotReceived+'</h5><p>Cards  <span class="text-orange">Not Received</span></p></td>	';
		str4+='</tr>';
		str4+='<tr>';
		str4+='<td><h5>'+totalInfo.tgnotRegistered+'</h5><p>Not Registered Member Phone Numbers</p></td>';	
		str4+='</tr>';
		str4+='</table>';
		$("#APandTsDiv").html(str);
		$("#todayApDiv").html(str1);
		$("#todayTGDiv").html(str2);
		$("#ApTotalDiv").html(str3);
		$("#TGTotalDiv").html(str4);
	}
</script>
<script>
showHide();
getIvrBasicCount();
</script>
</body>
</html>