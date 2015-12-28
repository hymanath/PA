<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tirupati Bye Election Poll Monitor</title>
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
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
	.height-500{height: 500px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   
  .dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	
table.dataTable tr.odd td.sorting_1 {
    background-color: #d3d3d3;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #fafafa;
}
table.dataTable tr.odd {
    background-color: #f3f3f3;
}

	.summary td {
		//color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-bottom:1px solid lightblue;
		border-left:1px solid lightblue;
		border-right:1px solid lightblue;
	}
	.summary th {
		border:1px solid lightblue;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.summary{
		margin-left:25px;margin-bottom:10px;
	}
	
	.info td {
		color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-bottom: 1px solid #6699CC;
	}
	.info th {
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-top: 1px solid #6699CC;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.typeRd{margin:5px;}
	.cCodeDiv{height:8px;width:8px;margin:6px;float:left;}
	
	
.progress {
    height: 3px !important;
}
#subCount{
 display:none;
}
.error{color:red;}
#summaryInfo h6{color:#023C59;margin-top:8px;}
</style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
 <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  
</head>
<body>
<div class="container m_top10" style=" background: none repeat scroll 0 0 #fff;
    padding: 10px;">

		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">Tirupati Bye Election Poll Monitor</h3>
			</div>
		</div><!-- Title Row End-->
		<div id="latestUpdatesDiv" class="row-fluid"></div>
			<div class="well well-small border-radius-0 mb-10" id="summaryInfoTable" style="margin-top:10px;padding: 0px;">
			</div>	

			<div class="row-fluid" style="margin-top: 20px;"><div class="form-inline"><label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" value="1" onclick="buildOptions()" >
			Cluster
			</label>
			<label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" onclick="buildOptions()" value="2">
			Divison
			</label>
			<label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" value="0" checked>
			All
			</label>
			<lable id="selectDiv"  style="margin-left: 53px;">Select Cluster</lable><select id="selectType" onchange="getByeEleBoothsCurrentStatus();"></select>

			<input class="btn btn-danger " type="button" onclick="getByeEleBoothsErrorReport();" value="Error Info" style="float:right;">
			</div>
			<div class="row-fluid " id="summaryInfo" style="margin-top:10px;">

			<div  class="span12 show-grid well well-small border-radius-0 mb-10">
			</div>
			</div>
			</div>
			
		<div class="row-fluid " id="mainCount" style="margin-top:20px;">
			<span id="exportToExcelBtn" class="btn btn-info pull-right"> Export Report To Excel </span>
			<div id="mainInfo" class="span12 show-grid well well-small border-radius-0 mb-10" style="margin-top: 20px; overflow: auto;margin-left: 0px;">
				    
			</div>
			<div style="font-size: 14px;margin-bottom: 5px;text-align: right;"><span type="button" onclick="getCurrentBoothsMessages();" class="btn btn-success pull-right">Refresh Messages</span></div>
            <div id="countInfo" class="span12 show-grid well well-small border-radius-0 mb-10" style="margin-top: 20px; overflow: auto;margin-left: 0px;">
				    
			</div>
		</div>
		<div class="row-fluid " id="subCount">
			<div id="subInfo" class="span12 show-grid well well-small border-radius-0 mb-10">
				    
			</div>
		</div>
		
<div id="errorDetailsDiv" style="display:none;margin-top:20px;"><div id="errorDetailsInnerDiv"></div></div>
</div>

<script>
$("#exportToExcelBtn").click(function(){
	 tableToExcel('knownTabDiv', 'TIRUPATHI BYE ELECTION POLL MONITOR');
});
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()


</script>


<script type="text/javascript">

function getSummary()
{
var jobj = {
			task : "ByeElesummary",

		  }
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getByeEleBoothsCurrentStatusAction.action"
				}).done(function(result){
					buildSummary(result);
				});

}

function getSummaryByStatus(status,typeId,type)
{
	
	      $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		  $("#mainCount").show(); 
var jobj = {
			task : "ByeElesummaryStatusInfo",
			status : status,
			typeId:typeId,
			type:type
		  }
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getByeEleBoothsCurrentStatusAction.action"
				}).done(function(result){
					
					buildReport(result,jobj);
				});

}
function buildByeElectionSummary(result,jobj)
{
	var str ='';
	str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover">';
	str+='<tr>';
	/*var total = 0;
	if(result.recognizeList !== null && result.recognizeList.length >0){
	for(var i in result.recognizeList){	
		total +=result.recognizeList[i].totalVoters;
	}
	}*/
	str+='<td style="width:25px;"><h2>'+result.totalVoters+'</h2><h4>Total Booths</h4><h6>TOTAL VOTERS - 292526</h6></td>';

	if(result.preTotalVoters > 0)
	{
	str+='<td style="width:25px;"><h2><a style="cursor:pointer" onclick="getSummaryByStatus(\'all\',\''+jobj.typeId+'\',\''+jobj.type+'\')">256</a></h2> <h4>All</h4> <h6> POLLED - '+ result.abPolledCount+' ('+result.abPercentage+' %)</h6></td>';
	}
	else
	{
		str+='<td style="width:25px;"><h2>'+result.preTotalVoters+'</h2> <h4>All  </h4> </td>';
	}
	if(result.polledVotes > 0)
	{
	str+='<td style="width:25px;"><h2><a style="cursor:pointer" onclick="getSummaryByStatus(\'recognize\',\''+jobj.typeId+'\',\''+jobj.type+'\')">'+result.polledVotes+'</a></h2> <h4>From Mapped Mobile No.s </h4> <h6> POLLED - '+ result.kbPolledCount+' ('+result.kbPercentage+' %)</h6> </td>';
	}
	else
	{
		str+='<td style="width:25px;"><h2>'+result.polledVotes+'</h2> <h4>From Mapped Mobile No.s  </h4> </td>';
	}
	if(result.id > 0)
	{
	str+='<td style="width:25px;"><h2><a style="cursor:pointer" onclick="getSummaryByStatus(\'unrecognize\',\''+jobj.typeId+'\',\''+jobj.type+'\')">'+result.id+'</a></h2> <h4>From UnMapped Mobile No.s </h4> <h6> POLLED - '+ result.ukbPolledCount+' ('+result.ukbPercentage+' %)</h6> </td>';
	}
	else
	{
		str+='<td style="width:35px;"><h2>'+result.id+'</h2> <h4> From UnMapped Mobile No.s </h4> </td>';
	}
	str+='</tr>';
	str+='</table>';
	$("#summaryInfo").html(str);

}
  function getBoothsCurrentStatus(){
	      $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		  $("#mainCount").show(); 
		  var jobj = {
			task : "",

		  }
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getBoothsCurrentStatus.action"
				}).done(function(result){
	                  if((result.knownList != null && result.knownList.length > 0) || (result.unKnownList != null && result.unKnownList.length > 0) ){
						  if(result.knownList != null && result.knownList.length > 0){
							  $("#mainCount").show(); 
							  var boothData = result.knownList;
							  var str ='';
							  str+='<table id="knownTab"class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
						      str+='  <tr><th>Booth No</th><th>Booth Type</th><th>Total Voters</th><th>Votes Polled</th><th>% Of Votes Polled</th><th>Time</th><th>Mobile</th></thead></tr>';
							  str+='<tbody>';

							 for(var i in boothData){
								 var percentage = "";
								if(boothData[i].total > 0)
								var percentage = (boothData[i].status / boothData[i].total * 100).toFixed(2);
								else
								percentage = 0.0;
								str+='  <tr>'; 
								str+='    <td>'+boothData[i].name+'</td>'; 
								str+='    <td>'+boothData[i].role+'</td>'; 
								str+='    <td>'+boothData[i].total+'</td>';
								
								str+='    <td>'+boothData[i].status+'</td>'; 
								str+='    <td>'+percentage+'</td>';
								str+='    <td>'+boothData[i].locationName+'</td>'; 
								str+='    <td>'+boothData[i].membershipNo+'</td>'; 
								str+='  </tr>'; 
							 }
							
						      str+=' </tbody>';
					          str+='</table>';
							  
							  $("#mainInfo").html(str);
							  $("#knownTab").dataTable({
				"aaSorting": [[ 1, "asc" ]],
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
				});;
						  }else{
							$("#mainInfo").html("");
							$("#mainCount").hide();  
						  }
						  if(result.unKnownList != null && result.unKnownList.length > 0){
							$("#subCount").show(); 
							var boothData = result.unKnownList;
							  var str ='<div style="font-size: 18px;margin-bottom: 5px;text-align: center;"><b>Messages From Un Known Mobile</b></div>';
							  str+='<table id="unknownTab" class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
						      str+='  <tr><th>Votes Polled</th><th>Time</th><th>Mobile</th></thead></tr>';
							  str+=' <tbody>';
							 for(var i in boothData){
								str+='  <tr>'; 
								str+='    <td>'+boothData[i].status+'</td>'; 
								str+='    <td>'+boothData[i].locationName+'</td>'; 
								str+='    <td>'+boothData[i].membershipNo+'</td>'; 
								str+='  </tr>'; 
							 }
						      str+=' </tbody>';
					          str+='</table>';
							  $("#subInfo").html(str);
							  $("#unknownTab").dataTable({
				"aaSorting": [[ 1, "asc" ]],
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
				});;
						  }else{
							$("#subInfo").html("");
							$("#subCount").hide();  
						  }
					  }else{
						   $("#mainCount").show();
							$("#subCount").hide();  						   
						  $("#mainInfo").html("No Data Available!");
					  }
				});
  }

function getByeEleBoothsCurrentStatus(){
$("#summaryInfo").html('');
	var type ="";
	    $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		$("#mainCount").show(); 
		var checkdVal= $("input[name=optionsRadios]:checked").val();
		if(checkdVal > 0){
		   type = $("#selectType").val();
		}
		  var jobj = {
			task : "ByeEleInfo",
			typeId:checkdVal,
			type:type

		  }
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getByeEleBoothsCurrentStatusAction.action"
				}).done(function(result){
					buildByeElectionSummary(result,jobj);
					buildReport(result,jobj);
	                
				});
  }
function sortByPartNO(a, b){
  var aName = parseInt(a.partNo);
  var bName = parseInt(b.partNo); 
  return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
}
  function buildReport(result,jobj)
  {
	   
		if(result.recognizeList != null && result.recognizeList.length > 0){
							  $("#mainCount").show(); 
							  var boothData = result.recognizeList;
							  try{
							  boothData.sort(sortByPartNO); 
							  }catch(e){
							  }
							  //console.log(boothData);
							  var str ='';
							  var title ="";
							 var typeId = jobj.typeId;	
							  if(jobj.status != null)
								{
								  if(jobj.status == "recognize")
								  title = "Mapped Mobile No.s"
								   if(jobj.status == "unrecognize")
								  title = "UnMapped Mobile No.s"
							  str+='<h4 style=" text-transform: uppercase;color:#0062AE; margin-left: 400px;"> '+title+' Report</h4>';
								}
								else if (jobj.typeId == 1)
								{
									 str+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Cluster Report</h4>';
								}
								else if (jobj.typeId == 2)
								{
									 str+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Divison Report</h4>';
								}
							  str+='<table id="knownTab" class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
						      str+='<tr>';
							  str+='<th>PART NO</th>';
							  //if(typeId == 0){
								str+='<th>CLUSTER</th>';
								str+='<th>WARD</th>';
							  //}
							  str+='<th>BOOTH LOCATION</th>';
							  str+='<th>2015 TOTAL VOTERS</th>';
							  str+='<th>2015 POLLED VOTES</th>';
							  str+='<th>2015 POLLING PERCENTAGE</th>';
							   str+='<th>Time</th>';
							 // str+='<th>2014_PartNO</th>';
							 // str+='<th>2014 VOTERS IN 2015</th>';
							 // str+='<th>2014 TOTAL VOTERS</th>';
							  str+='<th>2014 POLLING PERCENTAGE';
							  str+='<th>2014 POLLED VOTES</th>';
							  str+=' </thead>';
							  str+=' </tr>';
							  str+='<tbody>';

							 for(var i in boothData){
								 str+='<tr>';
							  str+='<td>'+boothData[i].partNo+'</td>';
							 // if(typeId == 0){
								str+='<td>'+boothData[i].cluster+'</td>';
								str+='<td>'+boothData[i].ward+'</td>';
							 // }
							  str+='<td>'+boothData[i].boothLocation+'</td>';
							  str+='<td>'+boothData[i].totalVoters+'</td>';
							  if(boothData[i].polledVotes == null)
								 str+='<td> - </td>';
							  else
							  str+='<td>'+boothData[i].polledVotes+'</td>';
							   if(boothData[i].percentage == null)
								 str+='<td> - </td>';
							  else
							  str+='<td>'+boothData[i].percentage+'</td>';
							   if(boothData[i].time == null)
								 str+='<td> - </td>';
							  else
							 str+='<td>'+boothData[i].time+'</td>';
							  //str+='<td>'+boothData[i].prevPartNo+'</td>';
							 // str+='<td>'+boothData[i].preVotersInPresent+'</td>';
							 // str+='<td>'+boothData[i].preTotalVoters+'</td>';
							  str+='<td>'+boothData[i].prevPercentage+'</td>';
							  str+='<td>'+boothData[i].prevPolledVotes+'</td>';
								str+='  </tr>'; 
							 }
							
						      str+=' </tbody>';
					          str+='</table>';
							  
							  var str1 = '<div id="knownTabDiv" style="display:none;">';
							  if(jobj.status != null)
								{
								  if(jobj.status == "recognize")
								  title = "Mapped Mobile No.s"
								   if(jobj.status == "unrecognize")
								  title = "UnMapped Mobile No.s"
							  str1+='<h4 style=" text-transform: uppercase;color:#0062AE; margin-left: 400px;"> '+title+' Report</h4>';
								}
								else if (jobj.typeId == 1){
									 str1+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Cluster Report</h4>';
								}
								else if (jobj.typeId == 2){
									 str1+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Divison Report</h4>';
								}else if(jobj.typeId==0){
									 str1+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Total Report</h4>';
								}
							  str1+='<table id="knownTab1"  class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
						      str1+='<tr>';
							  str1+='<th>PART NO</th>';
							 // if(typeId == 0){
								str1+='<th>CLUSTER</th>';
								str1+='<th>WARD</th>';
							 // }
							  str1+='<th>BOOTH LOCATION</th>';
							  str1+='<th>2015 TOTAL VOTERS</th>';
							  str1+='<th>2015 POLLED VOTES</th>';
							  str1+='<th>2015 POLLING PERCENTAGE</th>';
							   str1+='<th>Time</th>';
							 // str+='<th>2014_PartNO</th>';
							 // str+='<th>2014 VOTERS IN 2015</th>';
							 // str+='<th>2014 TOTAL VOTERS</th>';
							  str1+='<th>2014 POLLING PERCENTAGE';
							  str1+='<th>2014 POLLED VOTES</th>';
							  str1+=' </thead>';
							  str1+=' </tr>';
							  str1+='<tbody>';

							 for(var i in boothData){
								 str1+='<tr>';
							  str1+='<td>'+boothData[i].partNo+'</td>';
							  //if(typeId == 0){
								str1+='<td>'+boothData[i].cluster+'</td>';
								str1+='<td>'+boothData[i].ward+'</td>';
							  //}
							  str1+='<td>'+boothData[i].boothLocation+'</td>';
							  str1+='<td>'+boothData[i].totalVoters+'</td>';
							  if(boothData[i].polledVotes == null)
								 str1+='<td> - </td>';
							  else
							  str1+='<td>'+boothData[i].polledVotes+'</td>';
							   if(boothData[i].percentage == null)
								 str1+='<td> - </td>';
							  else
							  str1+='<td>'+boothData[i].percentage+'</td>';
							   if(boothData[i].time == null)
								 str1+='<td> - </td>';
							  else
							 str1+='<td>'+boothData[i].time+'</td>';
							  //str+='<td>'+boothData[i].prevPartNo+'</td>';
							 // str+='<td>'+boothData[i].preVotersInPresent+'</td>';
							 // str+='<td>'+boothData[i].preTotalVoters+'</td>';
							  str1+='<td>'+boothData[i].prevPercentage+'</td>';
							  str1+='<td>'+boothData[i].prevPolledVotes+'</td>';
								str1+='  </tr>'; 
							 }
							
						      str1+=' </tbody>';
					          str1+='</table></div>';
							  
							  $("#mainInfo").html(str);
							  $("#mainInfo").append(str1);
							  
							  $("#knownTab").dataTable({
								"iDisplayLength": 50,
								"aLengthMenu": [[50,100,150, -1], [50, 100, 150, "All"]]
								});
						  }else{
							$("#mainInfo").html("");
							$("#mainCount").hide();  
						  }
						  
  }

$(".radioCls").click(function(){
 var checkdVal= $("input[name=optionsRadios]:checked").val();
	  if(checkdVal == 2)
	  {
		  $("#selectDiv").html("Select Divison");
		  $("#divisonSelect").show();
		  $("#clusterSelect").hide();
		  $("#selectType").show();
	  }
	  else if(checkdVal == 1)
	  {
		  $("#selectDiv").html("Select Cluster");
		   $("#divisonSelect").hide();
		   $("#clusterSelect").show();
		    $("#selectType").show();
	  }
	  else if(checkdVal == 0)
	  {
		  $("#selectDiv").html("");
		  $("#selectType").hide();
		   $("#divisonSelect").hide();
		   $("#clusterSelect").hide();
		   getByeEleBoothsCurrentStatus();
	  }

});
function buildOptions()
  {
	  $('#selectType').html('');
	  $("#selectDiv").html("");
	  $("#selectType").hide();
	  $("#divisonSelect").hide();
	  $("#clusterSelect").hide();
     var checkdVal= $("input[name=optionsRadios]:checked").val();
	  var jobj ={
		typeId:checkdVal,
		task:""
	  }
		 $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getClustesAndDivisionNamesAction.action"
				}).done(function(result){
					
					if(result != null && result.length > 0)
					{
						
						
						for(var i in result)
						{
							
							$('#selectType').append('<option value="'+result[i].name+'">'+result[i].name+'</option>');
					
						}
					}
					getByeEleBoothsCurrentStatus();
				});
				
		
  }

function getByeEleBoothsErrorReport(){
$('#errorDetailsInnerDiv').html('');
	var type ="";
	     
		 
		  var jobj = {
			task : "byeEleerrorInfo"
			
		  }
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getByeEleBoothsCurrentStatusAction.action"
				}).done(function(result){
					
					buildErrorReport(result);
	                
				});


  }

function getLatestUpdates(){
			 var jobj = {
			  startIndex : 0,
			  maxIndex:5,
			  task : "byeElelatestUpdates"
				}
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {task:JSON.stringify(jobj)},
					url : "getByeEleBoothsCurrentStatusAction.action"
				}).done(function(result){
				
					if(result.recognizeList.length > 0)
					buildLatestUpdatesData(result.recognizeList);
	                
				});
				setTimeout(getLatestUpdates, 150000);

			 }

function buildLatestUpdatesData(result)
{
var str='';
str+='<div id="updatesDiv" class="span12 show-grid well well-small border-radius-0 mb-5  animated fadeInLeft" id="fadeInLeft">';
str+='	<h4 class="m-0 text-center"><span style="background:#ffffff; padding:0px 3px;font-size: 16px;text-transform:uppercase;">Latest Updates</span></h4>';
str+='<marquee direction="left" scrolldelay="200" scrollamount="5" style="width:100%" ">';
for(var i in result)
	{
str+='<span class="label label-info"><b>'+result[i].partNo+'</b></span> &nbsp;&nbsp;';
	}
str+='</marquee>';
str+='</div>';

$("#latestUpdatesDiv").html(str);

}

 
function buildErrorReport(resultList)
{
	var result = resultList.recognizeList;
	var str ='';
	
		


	  str+='<table id="errorInfoTable" class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead class="table-alert-info">';
	  str+='<tr>';
	  str+='<th>Mobile No</th>';
	  str+='<th>Message </th>';
	  str+='<th>Reason</th>';
	  str+='<th>Time</th>';
	  str+='</tr>';
	  str+='</thead>';
	  str+='<tbody>';
	  if(result != null && result.length > 0)
	{
		  for(var i in result)
		{
		  str+='<tr>';
		  str+='<td>'+result[i].partNo+'</td>';
		  str+='<td>'+result[i].name+' </td>';
		  str+='<td>'+result[i].type+'</td>';
		  str+='<td>'+result[i].time+'</td>';
		  str+='</tr>';
		}
	}
	   str+='</tbody>';
	   str+='</table>';
	
	    $('#errorDetailsInnerDiv').html(str).removeClass("error");
		$('#errorDetailsDiv').dialog({                   
		    modal: true,
            title: "<b>Error Details</b>",
			width: 970,
            height: 600
     });
			
				$("#errorInfoTable").dataTable({
				"aaSorting": [[ 1, "asc" ]],
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
				});
			


}
buildOptions();
//getSummary();

function buildLastThreeYearsVotersDetails()
{
	var str ='';
	str+='<table class="table table-bordered border-radius-0 mb-0 table-hover">';
	str += '<thead style="">';
	str += '<tr>';
	str += '<th style="width:25%">YEAR</th>';
	str += '<th style="width:25%">TOTAL VOTERS </th>';
	str += '<th style="width:25%">POLLED VOTES </th>';
	str += '<th style="width:25%">% OF POLLED VOTES </th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tr>';
	str+='<td style=""><h5>2014</h5></td>';	
	str+='<td style=""><h5>289895</h5></td>';	
	str+='<td style=""><h5>172525</h5></td>';
	str+='<td style=""><h5>59.51</h5></td>';
	str+='</tr>';
	str += '<tr>';
	str+='<td style=""><h5>2012</h5></td>';	
	str+='<td style=""><h5>250258</h5></td>';	
	str+='<td style=""><h5>136740</h5></td>';
	str+='<td style=""><h5>54.64</h5></td>';
	str+='</tr>';
	str += '<tr>';
	str+='<td style=""><h5>2009</h5></td>';	
	str+='<td style=""><h5>247160</h5></td>';	
	str+='<td style=""><h5>127627</h5></td>';
	str+='<td style=""><h5>51.64</h5></td>';
	str+='</tr>';
	str+='</table>';
	$("#summaryInfoTable").html(str);
	 
}
function getCurrentBoothsMessages(){
	  $("#countInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
	           $.ajax({
				    dataType: 'json',
					type : "POST",
					data: {startIndex:"",maxIndex:""},
					url : "getByeEleMessagesInfoAction.action"
				}).done(function(result){
					
					buildMessages(result);
	                
				});
  }
  
 function buildMessages(result){
					  var boothData = result.recognizeList;
					  var str ='';
					  str+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;"> Messages </h4>';
					  str+='<table id="countInfoTab"class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
					  str+='  <tr><th>Booth No</th><th>mobileNo</th><th>Time</th><th>Message</th></thead></tr>';
					  str+='<tbody>';

					 for(var i in boothData){
						 
						str+='  <tr>'; 
						str+='    <td>'+boothData[i].partNo+'</td>'; 
						str+='    <td>'+boothData[i].type+'</td>'; 
						str+='    <td>'+boothData[i].percentage+'</td>'; 
						str+='    <td>'+boothData[i].name+'</td>'; 
						str+='  </tr>'; 
					 }
					
					  str+=' </tbody>';
					  str+='</table>';
					  
					  $("#countInfo").html(str);
					  $("#countInfoTab").dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 50,
		"aLengthMenu": [[50,100,250, -1], [50,100,250, "All"]]
		});
 }					 
buildLastThreeYearsVotersDetails();
getCurrentBoothsMessages();
getLatestUpdates();
</script>
</body>
</html>