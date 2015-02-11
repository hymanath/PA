<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Booths Status</title>
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


	</style>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
<div class="container m_top10" style=" background: none repeat scroll 0 0 #fff;
    padding: 10px;">

		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">Booth Wise Polling Info</h3>
			</div>
		</div><!-- Title Row End-->
				<div class="row-fluid " id="summaryInfo">

			<div  class="span12 show-grid well well-small border-radius-0 mb-10">
			</div>
			</div>

			<div class="row-fluid" style="margin-top: 20px;"><div class="form-inline"><label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" value="1" onclick="buildOptions()" checked>
			Cluster
			</label>
			<label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" onclick="buildOptions()" value="2">
			Divison
			</label>
			<label class="radio">
			<input type="radio" name="optionsRadios" class="radioCls" value="0">
			All
			</label>
			<lable id="selectDiv"  style="margin-left: 53px;">Select Cluster</lable><select id="selectType" onchange="getByeEleBoothsCurrentStatus();"></select>
			</div>

			</div>
			
		<div class="row-fluid " id="mainCount">
			<div id="mainInfo" class="span12 show-grid well well-small border-radius-0 mb-10" style="margin-top: 20px; overflow: auto;">
				    
			</div>
		</div>
		<div class="row-fluid " id="subCount">
			<div id="subInfo" class="span12 show-grid well well-small border-radius-0 mb-10">
				    
			</div>
		</div>
</div>
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

function getSummaryByStatus(status)
{
	var type ="";
	      $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		  $("#mainCount").show(); 
var jobj = {
			task : "ByeElesummaryStatusInfo",
			status : status
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
function buildSummary(result)
{
	var str ='';
	str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover">';
	str+='<tr>';
	str+='<td style="width:35px;"><h2>'+result.totalVoters+'</h2> <p> TotalBooths</p> </td>';
	if(result.polledVotes > 0)
	{
	str+='<td style="width:35px;"><h2><a style="cursor:pointer" onclick="getSummaryByStatus(\'recognize\')">'+result.polledVotes+'</a></h2> <p>Recognize Booths </p> </td>';
	}
	else
	{
		str+='<td style="width:35px;"><h2>'+result.polledVotes+'</h2> <p>Recognize Booths </p> </td>';
	}
	if(result.id > 0)
	{
	str+='<td style="width:35px;"><h2><a style="cursor:pointer" onclick="getSummaryByStatus(\'unrecognize\')">'+result.id+'</a></h2> <p>Un Recognize Booths</p> </td>';
	}
	else
	{
		str+='<td style="width:35px;"><h2>'+result.id+'</h2> <p> Un Recognize Booths </p> </td>';
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
	var type ="";
	      $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		  $("#mainCount").show(); 
		   var checkdVal= $("input[name=optionsRadios]:checked").val();
		   if(checkdVal > 0)
		    type = $("#selectType").val();
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

					buildReport(result,jobj);
	                
				});
  }

  function buildReport(result,jobj)
  {
	   
		if(result.recognizeList != null && result.recognizeList.length > 0){
							  $("#mainCount").show(); 
							  var boothData = result.recognizeList;
							  var str ='';
							  if(jobj.status != null)
							  str+='<h4 style=" text-transform: uppercase;color:#0062AE; margin-left: 400px;"> '+jobj.status+' Booth Report</h4>';
								else if (jobj.typeId == 1)
								{
									 str+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Cluster Report</h4>';
								}
								else if (jobj.typeId == 2)
								{
									 str+='<h4 style=" text-transform: uppercase;;color:#0062AE; margin-left: 400px;">  Divison Report</h4>';
								}
							  str+='<table id="knownTab"class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><thead>'
						      str+='<tr>';
							  str+='<th>2015_PartNO</th>';
							  str+='<th>BOOTH LOCATION</th>';
							  str+='<th>2015 TOTAL VOTERS</th>';
							  str+='<th>2015 POLLED VOTES</th>';
							  str+='<th>2015 POLLING PERCENTAGE</th>';
							   str+='<th>Time</th>';
							  str+='<th>2014_PartNO</th>';
							  str+='<th>2014 VOTERS IN 2015</th>';
							  str+='<th>2014 TOTAL VOTERS</th>';
							  str+='<th>2014 POLLING PERCENTAGE';
							  str+='<th>2014 POLLED VOTES</th>';
							  str+=' </thead>';
							  str+=' </tr>';
							  str+='<tbody>';

							 for(var i in boothData){
								 str+='<tr>';
							  str+='<td>'+boothData[i].partNo+'</td>';
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
							  str+='<td>'+boothData[i].prevPartNo+'</td>';
							  str+='<td>'+boothData[i].preVotersInPresent+'</td>';
							  str+='<td>'+boothData[i].preTotalVoters+'</td>';
							  str+='<td>'+boothData[i].prevPercentage+'</td>';
							  str+='<td>'+boothData[i].prevPolledVotes+'</td>';
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
buildOptions();
getSummary();

</script>
</body>
</html>