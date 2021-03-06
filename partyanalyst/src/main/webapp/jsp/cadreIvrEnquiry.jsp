<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IVR Report</title>
<link href="css/Bootstrap2.2.1.css" rel="stylesheet" type="text/css" />
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
 <!-- Load jQuery from Google's CDN -->
    <!-- Load jQuery UI CSS  -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    
    <!-- Load jQuery JS -->
    <script src="https://code.jquery.com/jquery-1.9.1.js"></script>
    <!-- Load jQuery UI Main JS  -->
    <script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


<style>
body{
	font-family: 'Roboto', sans-serif;
}
a
{
	color:#000
}

a:hover
{
	color:#000
}
.text-center
{
	text-align:center;
	alignment-adjust:central;
	alignment-baseline:middle;
}

.table-bordered
{
	border-radius:0px;
	border-collapse:collapse;
}

.table-condensed
{
	border-radius:0px;
}

.well
{
	border-radius:0px;
}

input[type="text"]
{
	border-radius:0px;
}

.btn-success
{
	border-radius:0px;
}
.border-radius-0{border-radius:0px;}
.selected{background:#DFF0D8 !important;}
.selectedchild{background:#DFF0D8 !important;}
</style>
</head>

<body>
<div class="container">
<div class="row"><div class="span12 text-center" >

<table width="100%" class="table table-condensed table-bordered" style="margin-top:30px;">
    <tr class="well">
        <td colspan="6">
        <div class="span11"><h4 class="text-success" align="center">CARDS DISTRIBUTION CONFIRMATION STATUS BY INFO MANAGER</h4>
        	 <span class="btn btn-success btn-xs form-inline pull-right" style="margin-right: -50px;">
				<label class="radio"><input type="radio" id="allId" style="vertical-align: text-bottom;" class="stateRd" value="0" checked="true" name="selectstate">&nbsp;ALL &nbsp;&nbsp;&nbsp;</label>
				<label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="stateRd" value="1" name="selectstate" >&nbsp;AP &nbsp;&nbsp;&nbsp;</label>
				<label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="stateRd" value="2" name="selectstate">&nbsp;TS &nbsp;&nbsp;&nbsp;</label>
			</span>
            <!-- <a class="btn btn-small btn-default pull-right border-radius-0">
				 <label class="radio">
				  <input type="radio">AP
				</label>
			</a>
            <a class="btn btn-small btn-default pull-right border-radius-0">
				<label class="radio ">
				  <input type="radio">TS
				</label>
            </a>
             <a class="btn btn-small btn-success pull-right border-radius-0">            
				<label class="radio">
				  <input type="radio"> ALL
				</label>
            </a>-->
    	</div>
        </td>
    </tr>
    <tr>
	
        <td width="15%"><div class="text-center"><h2  class="text-center"><span id="printsId1" ></span><img class="imgStyle printData" src="images/icons/search.gif"></img></h2>Total Printed Cards</div></td>
        <td width="15%"><div class="text-center"><h2 id=""><img class="imgStyle  printData" src="images/icons/search.gif"/><span id="printId2" ></span></h2>Total Cards Received By Constituency Incharge</div></td>
        <td width="15%"><div class="text-center"><h2 id=""><img class="imgStyle  printData" src="images/icons/search.gif"/><span id="printId3" ></span></h2>Total Cards Received By Urban Area Incharge</div></td>
        <td width="15%"><div class="text-center"><h2 id=""><img class="imgStyle  printData" src="images/icons/search.gif"/><span id="printId4" ></span></h2>Total Cards Dispatched To Mandal Incharge</div></td>
        <td width="15%"><div class="text-center"><h2 id=""><img class="imgStyle  printData" src="images/icons/search.gif"/><span id="printId5" ></span></h2>Total Cards Received By Mandal Incharge</div></td>
        <td width="15%"><div class="text-center"><h2 id=""><img class="imgStyle  printData" src="images/icons/search.gif"/><span id="printId6" ></span></h2>Total Cards Delivered To Cadre By Mandal Incharge</div></td>
    </tr>
</table>

<table class="table table-bordered table-condensed">
    <tr>
		<td ><span class="add-on" style="background-color:#CCC;padding:6px;margin-left:-5px;"><i class="icon-calendar"></i></span><h6 style="display:inline"> &nbsp; Select Date to view the infomanagers conformed distributed cards status Constituency and mandal wise by area incharges</h6></td>
    </tr>
    <tr>
    	<td>
        	<div class="form-inline" style="margin-top:15px;">
            	From Date<input type="text" id="fromDate" readonly class="dateClass"/>&nbsp; To Date <input type="text" id="toDate" class="dateClass" readonly /> &nbsp;&nbsp;
                <button class="btn btn-success" value="Submit"  onclick="getCardsDistributedInfo();">Get Details</button>
				<span id="errorDiv"></span>
            </div>
        </td>
    </tr>
</table>
<!--<table width="100%" class="table table-condensed table-bordered">
    <tr class="well">
        <td colspan="4"><h5 style="display:inline;padding:5px;">DISTRICT LEVEL INFO</h5>(selected date range)</td>
    </tr>
    <tr>
        <td width="25%"><h4 >5</h4>Districts</td>
        <td width="25%"><h4>50000</h4>Total cards printed</td>
        <td width="25%"><h4>30000</h4>Total Cards dispatched to mandal incharge</td>
        <td width="25%"><h4>30000</h4>Total Cards received constituency incharge</td>
    </tr>
</table>-->
</div></div>
<center><img class="offset6" id="basicajaxImg" src="./images/icons/search.gif" style="display:none;height: 18px; width: 16px; margin-top: 0px; margin-left: 0px;"></center>
<div class="row">
	<div class="span6" id="constiTD">
    	
    </div>
    <div class="span6" id="mandalTD">
    	
    </div>
</div>
<p style="display:none;" id="noteId">NOTE: Click on mandal or contituency <u>COUNT</u> to get details</p>

<img style="height:18px;width:16px;display:none;" src="./images/icons/search.gif" id="ajaxImg" class="offset5"/>
	<div class="row">
    	<div class="span12" id="resultDivByLocation">
        	
        </div>
    </div>
	
</div>

<script src="js/bootstrap3/bootstrap.min.js"></script>
<script>

	$( document ).ready(function() {
		  $(".dateClass").datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$(".dateClass").datepicker("setDate", new Date());
		//$('.dateClass').datepicker({dateFormat: 'yy-mm-dd'}); 
		//$(".toDatepicker").datepicker("setDate", new Date());
	});
	$(".stateRd").click(function(){
		$("#mandalTD").html('');
		$("#resultDivByLocation").html('');
		$("#constiTD").html('');
		$("#noteId").hide();
		getTotalCardsDispatchedPerc();
		
	});
	function getCardsDistributedInfo()
	{	
		$("#errorDiv").html("");
		
		$("#mandalTD").html('');
		$("#resultDivByLocation").html('');
		$("#constiTD").html('');		
		$("#noteId").hide();
		
		var fromDate = $("#fromDate").val();
		var toDate =  $("#toDate").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var yr1  = parseInt(fromDate.substring(0,4),10);
		 
		  var mon1 = parseInt(fromDate.substring(5,7),10);
		  
		  var dt1  = parseInt(fromDate.substring(8,10),10);
		 
		  var yr2  = parseInt(toDate.substring(0,4),10);
		  var mon2 = parseInt(toDate.substring(5,7),10);
		  var dt2  = parseInt(toDate.substring(8,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);
			
			if(date2 < date1)
			{ 
				$("#errorDiv").html("From Date Should Be Less Than To Date").css("color","red");
				return false;
			}
		}
	
		$("#basicajaxImg").show();
		var selectedState = $("input[type='radio'][name='selectstate']:checked").val();		
		var jsObj = {	
			fromDate:fromDate,
			toDate:toDate,
			stateTypeId:selectedState,
			//fromDate:"2015-01-05",
			//toDate:"2015-01-26",
			task:"previousCallsCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getIvrPreviousCallBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#basicajaxImg").hide();	
			buildCardsDistributedDetails(result,fromDate,toDate);
		});
	}
	
	
	function buildCardsDistributedDetails(result,fromDate,toDate)
	{
		var str='';
		
		str+='<table width="100%" class="table table-condensed table-bordered">';
        str+='<tr><td colspan="4" class="well"><h5 style="display:inline;padding:5px;">CONSTITUENCY LEVEL INFO</h5>(selected date range)</td></tr>';
        str+='<tr>';
		
		if(result.totalCalls!= null && result.totalCalls > 0)	
		  str+='<td width="20%"><h4><a style="cursor:pointer;" onclick="getCardsDispatchedDataByType(\'Constituency\',\''+fromDate+'\',\''+toDate+'\')">'+result.totalCalls+'</a></h4><span style="font-size:11px; line-height:14px">Constituencies</span></td>';
        else
		  str+='<td width="20%"><h4>'+result.totalCalls+'</h4><span style="font-size:11px; line-height:14px">Constituencies</span></td>';
		
		str+='<td width="20%"><h4>'+result.printedCount+'</h4><span style="font-size:11px; line-height:14px">Total Cards Printed</span></td>';
        str+='<td width="30%"><h4>'+result.received+'</h4><span style="font-size:11px; line-height:14px">Total Cards Received By Constituency Incharge</span></td>';
        str+='<td width="30%"><h4>'+result.notReceived+'</h4><span style="font-size:11px; line-height:14px">Total Cards Dispatched To Mandal Incharge</span></td>';
        str+='</tr>';
		str+='</table>';
	
		$("#constiTD").html(str);
		
		var str1='';
		str1+='<table width="100%" class="table table-condensed table-bordered">';
        str1+='<tr><td colspan="4" class="well"><h5 style="display:inline;padding:5px;">MANDAL LEVEL INFO</h5>(selected date range)</td></tr>';
        str1+='<tr>';
		if(result.totalIvrCalls != null && result.totalIvrCalls > 0)	
			str1+='<td width="15%"><h4><a style="cursor:pointer;" onclick="getCardsDispatchedDataByType(\'Mandal\',\''+fromDate+'\',\''+toDate+'\')">'+result.totalIvrCalls+'</a></h4><span style="font-size:11px; line-height:14px">Mandals</span></td>';
		else
			str1+='<td width="15%"><h4>'+result.totalIvrCalls+'</h4><span style="font-size:11px; line-height:14px">Mandals</span></td>';
        if(result.mandalPrintedCnt != null)
		str1+='<td width="20%"><h4>'+result.mandalPrintedCnt+'</h4><span style="font-size:11px; line-height:14px">Total Cards Printed</span></td>';
		else
		str1+='<td width="20%"><h4>0</h4><span style="font-size:11px; line-height:14px">Total Cards Printed</span></td>';
        if(result.totalAnswerdCalls != null)
		str1+='<td width="30%"><h4>'+result.totalAnswerdCalls+'</h4><span style="font-size:11px; line-height:14px">Total Cards Received By Mandal Incharge</span></td>';
		else
		str1+='<td width="30%"><h4>0</h4><span style="font-size:11px; line-height:14px">Total Cards Received By Mandal Incharge</span></td>';
		
        str1+='<td width="35%"><h4>'+result.notMember+'</h4><span style="font-size:11px; line-height:14px">Total Cards Delivered To Cadre By Mandal Incharge</span></td>';
        str1+='</tr>';
		str1+='</table>';
		$("#mandalTD").html(str1);
		
		$("#noteId").show();
	}
	
	function getCardsDispatchedDataByType(type,fromDate,toDate)
	{
		$("#ajaxImg").show();
		$("#resultDivByLocation").html("");
		var selectedState = $("input[type='radio'][name='selectstate']:checked").val();	
		var jsObj = {	
			fromDate:fromDate,
			toDate:toDate,
			locationType:type,
			stateTypeId:selectedState,
			task:"previousCallsData"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getIvrPreviousCallBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				$("#ajaxImg").hide();
				buildCardsDispatchedDataByType(result,type);
		});
	}
	
	
	function buildCardsDispatchedDataByType(resultList,type)
	{
		var result = resultList.apList;
		var str ='';
		if(result == null || result.length == 0)
		{
			str+='<span style="color:red">No Data Available...</span>';
			$("#resultDivByLocation").html(str);
			return;
		}		
		str+='<table class="table table-bordered table-condensed"><tr>';
		if(type =="Constituency")
		{		
			str+='<td colspan="7" class="well"><h5 style="display:inline;">CONSTITUENCY LEVEL  DETAILS</h5></td>';
		}
		else if(type =="Mandal")
		{
			str+='<td colspan="6" class="well"><h5 style="display:inline;">MANDAL LEVEL DETAILS</h5> </td>';
		}
		str+='</tr>';
		str+='<tr>';
		if(type =="Constituency")
		{
			 str+='<td><h6>DISTRICT NAME</h6></td>';
			 str+='<td><h6>CONSTITUENCY NAME</h6></td>';
		}
		else if(type =="Mandal")
		{
			str+=' <td><h6>CONSTITUENCY NAME</h6></td>';
			str+='<td><h6>MANDAL NAME</h6></td>';
		}
		
		str+=' <td><h6>TOTAL CARDS PRINTED</h6></td>';
		if(type =="Constituency")
		{
			str+='<td><h6>TOTAL CARDS RECEIVED BY CONSTITUENCY INCHARGE</h6></td>';
		}
		else if(type =="Mandal")
		{
			str+='<td><h6>TOTAL CARDS RECEIVED BY MANDAL INCHARGE</h6></td>';
		}
		
        str+='<td><h6>DISTRIBUTED CARDS<br/>(CONFIRMED BY INFOMANAGERS)</h6></td>';
		if(type =="Constituency")
		{
			str+='<td><h6>TOTAL CARDS RECEIVED BY MANDAL INCHARGE <br/>(CONFIRMED BY INFOMANAGERS)</h6></td>';
		}
        str+='<td><h6>CADRE MEMBERS RECEIVED CARDS<br/>(CONFIRMED BY IVR)</h6></td>';
        str+='</tr>';
		for(var i in result)
		{
			 str+='<tr class="removeCls clearCls'+result[i].id+'">';
			 if(result[i].locationName != null)
			 
				str+='<td>'+result[i].locationName+'</td>';
			
			 else
				str+='<td>-</td>';
				
			 if(result[i].name != null){
				if(result[i].total != null && result[i].total > 0){
					str+='<td class="removeCls clearClsTD'+result[i].id+'"><a style="cursor:pointer;" onclick="getMandalDetailsByConstituencyId('+result[i].id+')">'+result[i].name+'</a></td>';
				}
				else {
					str+='<td>'+result[i].name+'</td>';
				}
			}
			 else
				str+='<td>-</td>';
				
			 if(result[i].printedCount != null)	
				str+='<td>'+result[i].printedCount+'</td>';
			 else
				str+='<td>-</td>';
			
			if(result[i].ivrEnqReceived != null)		
				str+='<td>'+result[i].ivrEnqReceived+'</td>';
			else
				str+='<td>-</td>';
			
			 if(result[i].ivrEnqDelivered != null)	
				str+='<td>'+result[i].ivrEnqDelivered+'</td>';
			 else
				str+='<td>-</td>';
			if(type =="Constituency"){
				if(result[i].total != null)		
					str+='<td>'+result[i].total+'</td>';
				else
				str+='<td>-</td>';
					
			}
			
			if(result[i].totalAnswerdCalls != null)		
			 //str+='<td>'+result[i].received+'</td>';
			 str+='<td>'+result[i].received+'<br/> yes '+result[i].receivedPerc+'% | no '+result[i].notReceivedPerc+'%<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
			
			/*str+='<td><span class="tooltipIVR" rel="tooltip" title="IVR Received '+result[i].received+' | NOtReceived '+result[i].notReceived+'">'+result[i].totalAnswerdCalls+'</span><br/> yes '+result[i].receivedPerc+'% | no '+result[i].notReceivedPerc+'%</td>';*/
			 else
				str+='<td>-<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
						 
				str+='</tr>';
		 }		 
		 str+='</tbody>';
		 str+='</table>';
		 $("#resultDivByLocation").html(str);
         
	}
	
	function getTotalCardsDispatchedPerc()
	{	
		$('#printId2,#printId3,#printId4,#printId5,#printId6,#printsId1').html("");
		$('.printData').show();
		var selectedState = $("input[type='radio'][name='selectstate']:checked").val();		
		var jsObj = {
			stateTypeId:selectedState,
			task:"totalData"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getTotalIvrPreviousCallBasicInfo.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null)
			{
				$('.printData').hide();
				$('#printsId1').html(result.printedCount);
				$('#printId2').html(result.constiReceivedPerc+'%');
				$('#printId3').html(result.urbanPerc+'%');
				$('#printId4').html(result.constiDeliveredPerc+'%');
				$('#printId5').html(result.mandalPerc+'%');
				$('#printId6').html(result.deliveredPerc+'%');
			}
		});
	}
	
	  function closeDiv(trID)
   {
	  
	$(".selectedchild").remove();
   }
  
	function getMandalDetailsByConstituencyId(constituencyId)
	{	
		$('.added').remove('');
		$(".removeicon").hide();
		$(".removeCls").removeClass("selected");
		$('.clearCls'+constituencyId).after('<tr class="selectedchild"><td id="subLevelMandal'+constituencyId+'" colspan="7" class="added"><div align="center"><img id="ajaxImgStyle" style="display:block;margin-left: 10px;width:20px;" src="images/icons/search.gif"/></div></td></tr>');;
		$('.clearCls'+constituencyId).addClass("selected");
		$('.clearClsTD'+constituencyId).addClass("selected");
		$("#ajaxImgStyle").show();
		$("#iconDiv"+constituencyId).show();
 
		var jsObj = {
			constituencyId:constituencyId,
			task:"mandalDetails"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getMandalRecievedCountInConstituencyAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			buildMandalData(result,"subLevelMandal"+constituencyId);
		});
	}
	function buildMandalData(resultList,divId)
	{
		var result = resultList.apList;
		var str ='';
		if(result == null || result.length == 0)
		{
			str+='<span style="color:red">No Data Available...</span>';
			$("#"+divId).html(str);
			return;
		}		
		str+='<table class="table table-bordered table-condensed" style=" background: none repeat scroll 0 0 #F5F5F5; margin-bottom: 2px;">';
	
		str+='<tr>';

		str+='<td><h6>MANDAL NAME</h6></td>';
		str+=' <td><h6>TOTAL CARDS PRINTED</h6></td>';
		str+='<td><h6>TOTAL CARDS RECEIVED BY MANDAL INCHARGE</h6></td>';
        str+='<td><h6>DISTRIBUTED CARDS<br/>(CONFIRMED BY INFOMANAGERS)</h6></td>';
        str+='<td><h6>CADRE MEMBERS RECEIVED CARDS<br/>(CONFIRMED BY IVR)</h6></td>';
        str+='</tr>';
		for(var i in result)
		{
			 if(result[i].name != null)
					str+='<td>'+result[i].name+'</td>';
			
			 else
				str+='<td>-</td>';
				
			 if(result[i].printedCount != null)	
				str+='<td>'+result[i].printedCount+'</td>';
			 else
				str+='<td>-</td>';
			
			if(result[i].ivrEnqReceived != null)		
				str+='<td>'+result[i].ivrEnqReceived+'</td>';
			else
				str+='<td>-</td>';
		
			 if(result[i].ivrEnqDelivered != null)	
				str+='<td>'+result[i].ivrEnqDelivered+'</td>';
			 else
				str+='<td>-</td>';
						 
			
			if(result[i].totalAnswerdCalls != null)		
			
			 str+='<td>'+result[i].received+'<br/> yes '+result[i].receivedPerc+'% | no '+result[i].notReceivedPerc+'%</td>';
			
			
			 else
				str+='<td>-</td>';
				str+='</tr>';
		 }		 
		 str+='</tbody>';
		 str+='</table>';
		 $("#"+divId).html(str);
         
	}
	
	
	function getDates()
	{
		var jObj ={
		task:"getDates"             
		}	
		$.ajax({
			type : "POST",
			url : "getAvailableDatesAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$("#fromDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			minDate:result[0],
			maxDate: result.length - 1
		});
			var minDate =result[0];
		$("#fromDate").datepicker("setDate", minDate);
		$("#toDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			minDate: result[0],
			maxDate:result.length - 1
		});
			var maxDate = result.length - 1;
		$("#toDate").datepicker("setDate", maxDate);
		getIvrPreviousCallsBasicInfo();
		});		
	}
	
	getTotalCardsDispatchedPerc();
//$('.tooltipIVR').tooltip('toggle');
//getDates();
</script>

</body>
</html>
