<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leader Cadre DashBoard</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"> 
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/sample.js"></script>
<style>
 #leaderDataDiv .selected, #leaderDataDiv .selectedchild{background:#EA4F5A !important;}
 #leaderDataDiv .selected1, #leaderDataDiv .selected1child{background: #0BBA7C !important;}

 #leaderDataDivStatusBars .selected{background:lightblue !important;}
 #leaderDataDivStatusBars .selectedchild{background:lightblue !important;}
 #leaderDataDivStatusBars .selected1{background: #cad5df !important;}
 #leaderDataDivStatusBars .selected1child{background: #cad5df !important;}
 .customMg{margin-left:-150px;}
</style>
</head>
<body>
   <div class="container m_top10">
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> Leader Cadre DashBoard</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 215px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   
			  <!-- <div style="margin-left:250px;">
					<h4 style="color:black;"> <input type ="radio" style="margin-top:-2px;" class="rprtType" name="reportType" value="normal" checked="checked">&nbsp;&nbsp;TABULAR REPORT</input>
					 <input style="margin-top:-2px;margin-left:12px;" type ="radio" class="rprtType" name="reportType" value="bar">&nbsp; STATUS BAR REPORT </h4></input>
			   </div>-->
			   
			   
			   <table  style="margin-left: 270px;" id="tabularReport">
					 <tr>
					   <td><b>Select Scope : </b></td>
					   <td>
						  <select id="selLctnType">
						  <option value="0">Select Scope</option>
							<option value="2">District</option>
							<option value="3">Constituency</option>
						</select>
						</td>
					 </tr>
				     <tr id="statedisplaydivid">
						<td><b>Select State :</b></td>
						<td>
						  <select id="statesDivId">
							
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
				     </tr>
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDate" /></td></tr>
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getLocationswiseleaderCadreInfo();" value="Submit"/> 
						<div><img id="ajaxImgStyle" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
					  
					  
				  </tr>
				  
			</table>
			<table id="barReport" style="display:none;margin-left: 270px;">
				<tr id="toggleDiv">
					<td></td>
					<td>
						<div>
							<a class="btn btn-success" id="ap" onclick="getLocationswiseleaderCadreInfoForBars('ap')">&nbsp;Andhrapradesh&nbsp;</a>
							<a class="btn" id="tg" onclick="getLocationswiseleaderCadreInfoForBars('tg')">&nbsp;Telangana&nbsp;</a>
						</div>
					</td>
					<td><div><img id="ajaxImg" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div></td>
				  </tr>
			</table>	  
					 
			  </div>
			
			
		</div>
   </div>
	
    <div id="leaderDataDiv" ></div>
					  <div id="leaderDataDivStatusBars"></div>
					  <div id="constituencyDynamicDiv"></div>
					   <div id="MandalDynamicDiv"></div>
			</div>
   <script>
   $("#fromDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#fromDate").datepicker("setDate", new Date());
		$("#toDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#toDate").datepicker("setDate", new Date());
   function getLocationswiseleaderCadreInfo()
   {
		$("#errStatusDiv").html("");
		var scopeId = $("#selLctnType").val();
		var scope = $("#selLctnType option:selected").text();
		var stateId = $("#statesDivId").val();
		$("#leaderDataDiv").html('');
		
		if(scopeId == 0)
		{
			$("#errStatusDiv").html("Select Scope").css("color","red");
			return;
		}
		$("#ajaxImgStyle").show();
		var jObj = {
		type : scope,
		stateId:stateId,
		fromDate:$("#fromDate").val(),
		toDate:$("#toDate").val(),
		task:"mainLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgStyle").hide();
				buildData(result,scope);
		});
   }
   function buildData(result,type)
   {
	   var str='';
		str+='<table class="table table-bordered" id="tabledataTab">';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "District")
	   {
		str+='<th>Dist#</th>';
		str+='<th>District</th>';
	   }
		else if(type == "Constituency")
	   {
		str+='<th>Dist#</th>';
		str+='<th>District</th>';
		str+='<th>Parl#</th>';
		str+='<th>Parliament</th>';
		str+='<th>Const#</th>';
		str+='<th>Constituency</th>';
	   }
		str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		if(type == "District")
		   {
		str+='<td class="removeCls clearClsTD'+result[i].id+'">'+result[i].id+'</td>';
		str+='<td ><a onclick="displaySublevelDetails('+result[i].id+',\'District\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		   }
		if(type == "Constituency"){
		str+='<td>'+result[i].districtId+'</td>'; 
		str+='<td>'+result[i].districtName+'</td>';	
		str+='<td>'+result[i].pcConstiNo+'</td>'; 
		str+='<td>'+result[i].parliament+'</td>';
		str+='<td>'+result[i].constiNo+'</td>';
		str+='<td>'+result[i].name+'</td>';
		}
		str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td id="appendID">'+result[i].difference+'<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		if(type == "District")
		$("#leaderDataDiv").removeClass("customMg");
		if(type == "Constituency")
		$("#leaderDataDiv").addClass("customMg");
		$("#leaderDataDiv").html(str);
		$("#tabledataTab").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
		var firstRowId = $('#tabledataTab tr').eq(1).attr('id');
		if(type == "District")
		{		
		displaySublevelDetails(firstRowId.replace('"',''),'District');
		}
   }
   
   function closeDiv(trID)
   {
	  
	$(".selectedchild").remove();
   }
   function closeDiv1(trID)
   {
	  
	$(".selected1child").remove();
   }
   function displaySublevelDetails(id,type)
   {
   var scope = "";
    $('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevelConst'+id+'" colspan="9" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+id).show();
 
   var jObj = {
		type : type,
		id:id,
		fromDate:$("#fromDate").val(),
		toDate:$("#toDate").val(),
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildData1(result,"subLevelConst"+id);
		});
   }


function displaySublevelDetails1(id,type)
   {
   var scope = "";
    $('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selected1child"><td id="subLevel'+id+'" colspan="9" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#ajaxImgStyle2").show();
	$("#iconDiv1"+id).show();
   
   var jObj = {
		type : type,
		id:id,
		fromDate:$("#fromDate").val(),
		toDate:$("#toDate").val(),
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildData2(result,"subLevel"+id);
		});
   }
   
function buildData1(result,divId)
   {
	 
	   var str='';
	  $("#ajaxImgStyle1").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		
		str+='<th>Const#</th>';
		str+='<th>Constituency</th>';
	  
		str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
	
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
	  
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
			str+='<td>'+result[i].constiNo+'</td>';
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'"><a  onclick="displaySublevelDetails1('+result[i].id+',\'Constituency\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		
		str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td>'+result[i].difference+'<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
	
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
		/*if(type == "Constituency")
		$("#constituencyDynamicDiv").html(str);
		if(type == "Mandal")
		$("#MandalDynamicDiv").html(str);*/
		
   }


   function buildData2(result,divId)
   {
	 
	   var str='';
	  $("#ajaxImgStyle2").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		
		   str+='<th>Mandal</th>';
	  
		str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
			  str+='<td>'+result[i].name+'</td>';
		
		str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
		/*if(type == "Constituency")
		$("#constituencyDynamicDiv").html(str);
		if(type == "Mandal")
		$("#MandalDynamicDiv").html(str);*/
		
   }
   
   function getLocationswiseleaderCadreInfoForBars(id)
   {
	
		$("#errStatusDiv").html("");
		var scope="District";
		if(id=="ap"){
			$("#ap").addClass("btn-success");
			$("#tg").removeClass("btn-success");
			var stateId=1;
		}else if(id=="tg"){
			$("#ap").removeClass("btn-success");
			$("#tg").addClass("btn-success");
			var stateId=2;
		}
		var fromDate="03-11-2014";
		var toDate;
		var toDate = new Date();
				var dd = toDate.getDate();
				var mm = toDate.getMonth()+1; //January is 0!
				var yyyy = toDate.getFullYear();
				if(dd<10) {
					dd='0'+dd
				} 
				if(mm<10) {
					mm='0'+mm
				} 
				toDate = dd+'-'+mm+'-'+yyyy;
		$("#leaderDataDivStatusBars").html('');
		
		/*if(scopeId == 0)
		{
			$("#errStatusDiv").html("Select Scope").css("color","red");
			return;
		}*/
		$("#ajaxImg").show();
		var jObj = {
		type : scope,
		stateId:stateId,
		fromDate:fromDate,
		toDate:toDate,
		task:"mainLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImg").hide();
				buildDataForBars(result,scope);
		});
   }
   function buildDataForBars(result,type)
   {
	   var str='';
		str+='<table class="table table-bordered" id="tabledataTab">';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "District")
		str+='<th><h4 style="color:red">DISTRICT</h4></th>';
		else if(type == "Constituency"){
		str+='<th><h4 style="color:red">DISTRICT</h4></th>';
		//str+='<th>Parliament</th>';
		//str+='<th>Constituency</th>';
	   }
		/* str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>'; */
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
	   
			var totalVoters = " - ";
			var targetCadres = " - ";
			var totalRecords = " - ";
			var percentage = 0;
			
				if(result[i].totalVoters !=null){
					totalVoters =  result[i].totalVoters;
				} 
				if(result[i].targetCadres !=null){
					targetCadres =  result[i].targetCadres;
				} 
				if(result[i].totalRecords !=null){
					totalRecords =  result[i].totalRecords;
				} 
				if(result[i].percentage !=null){
					percentage =  result[i].percentage;
				} 
	   
		str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		if(type == "District")
		//str+='<td style="width:320px;" class="removeCls clearClsTD'+result[i].id+'"><a onclick="displaySublevelDetailsForBars('+result[i].id+',\'District\');" style="cursor:pointer;">'+result[i].name+'('+ totalVoters+ ' , '+targetCadres+' , '+totalRecords+' , '+percentage+'%)</a>';
		str+='<td style="width:320px;" class="removeCls clearClsTD'+result[i].id+'" title= "Total Voters - '+ totalVoters+ ' ,&nbsp;&nbsp;Target Voters - '+targetCadres+' ,Total Registered Cadre -  '+totalRecords+' ,Percentage - '+percentage+'"><a onclick="displaySublevelDetailsForBars('+result[i].id+',\'District\');" style="cursor:pointer;"><h4>'+result[i].name+' &nbsp;&nbsp; ('+percentage+'%)</h4></a><span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span>';
		if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:15px;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:15px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:15px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:15px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:15px;">';
				} 
				
				str+='<div style="width:'+result[i].percentage+'%" class="bar" style="height:15px;"></div>';
		
		
		str+='</td>';
		str+='</tr>';
		if(type == "Constituency"){
		str+='<td>'+result[i].districtName+'('+ result[i].totalVoters+ ' , '+result[i].targetCadres+' , '+result[i].totalRecords+' , '+result[i].percentage+'%)</td>';	
		/* str+='<td>'+result[i].parliament+'</td>';
		str+='<td>'+result[i].name+'</td>'; */
		}
		/* str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td id="appendID">'+result[i].difference+'<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>'; */
		
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#leaderDataDivStatusBars").html(str);
		/* $("#tabledataTab").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     }); */
				 
		/* var firstRowId = $('#tabledataTab tr').eq(1).attr('id');
		if(type == "District")
		{		
		displaySublevelDetails(firstRowId.replace('"',''),'District');
		} */
		
		
   }
   function displaySublevelDetailsForBars(id,type)
   {
   var scope = "";
    $('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevel'+id+'" colspan="8" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+id).show();
   if(type == "District")
	   {
	scope = "Constituency";
 	   }
   if(type == "Constituency")
	   {
	scope = "Mandal";
	   }
	   fromDate="03-11-2014";
	   var toDate = new Date();
				var dd = toDate.getDate();
				var mm = toDate.getMonth()+1; //January is 0!
				var yyyy = toDate.getFullYear();
				if(dd<10) {
					dd='0'+dd
				} 
				if(mm<10) {
					mm='0'+mm
				} 
				toDate = dd+'-'+mm+'-'+yyyy;
   var jObj = {
		type : type,
		id:id,
		fromDate:fromDate,
		toDate:toDate,
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildDataForBars1(result,scope,"subLevel"+id);
		});
   }
   
   function buildDataForBars1(result,type,divId)
   {
	   var str='';
	  $("#ajaxImgStyle1").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th> <h4 style="color:red"> CONSTITUENCY </h4></th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th> <h4 style="color:red"> MANDAL </h4></th>';
	   }
		/* str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		if(type == "Constituency")
	   {
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
	   } */
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
		if(type == "Constituency"){
		
			var totalVoters = " - ";
			var targetCadres = " - ";
			var totalRecords = " - ";
			var percentage = 0;
			
				if(result[i].totalVoters !=null){
					totalVoters =  result[i].totalVoters;
				} 
				if(result[i].targetCadres !=null){
					targetCadres =  result[i].targetCadres;
				} 
				if(result[i].totalRecords !=null){
					totalRecords =  result[i].totalRecords;
				} 
				if(result[i].percentage !=null){
					percentage =  result[i].percentage;
				} 
			
		
			//str+='<td class="removeCls1 clearClsTD1'+result[i].id+'"><a  onclick="displaySublevelDetailsForBars1('+result[i].id+',\'Constituency\');" style="cursor:pointer;">'+result[i].name+'('+ totalVoters+ ' , '+targetCadres+' , '+totalRecords+' , '+percentage+'%)</a>';
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'" title= "Total Voters - '+ totalVoters+ ' ,&nbsp;&nbsp;Target Voters - '+targetCadres+' ,Total Records -  '+totalRecords+'"><a  onclick="displaySublevelDetailsForBars1('+result[i].id+',\'Constituency\');" style="cursor:pointer;"><h4>'+result[i].name+' &nbsp;&nbsp; ('+percentage+'%)</h4></a>';
			if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:15px;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:15px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:15px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:15px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:15px;">';
				} 
				str+='<div style="width:'+percentage+'%" class="bar" style="height:15px;"></div>';
		str+='</td></tr>';
		}
		  else if(type == "Mandal")
		   {
			  str+='<td>'+result[i].name+'</td>';
		   }
		/* str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		if(type == "Constituency")
		   {
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td>'+result[i].difference+'<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		   } */
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
		/*if(type == "Constituency")
		$("#constituencyDynamicDiv").html(str);
		if(type == "Mandal")
		$("#MandalDynamicDiv").html(str);*/
		
   }
   function displaySublevelDetailsForBars1(id,type)
   {
   var scope = "";
    $('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selectedchild"><td id="subLevel'+id+'" colspan="8" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;width:70px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#ajaxImgStyle2").show();
	$("#iconDiv1"+id).show();
	if(type == "District"){
			scope = "Constituency";
 	}if(type == "Constituency"){
		scope = "Mandal";
	  }
	   fromDate="03-11-2014";
	   var toDate = new Date();
				var dd = toDate.getDate();
				var mm = toDate.getMonth()+1; //January is 0!
				var yyyy = toDate.getFullYear();
				if(dd<10) {
					dd='0'+dd
				} 
				if(mm<10) {
					mm='0'+mm
				} 
				toDate = dd+'-'+mm+'-'+yyyy;
	   
   var jObj = {
		type : type,
		id:id,
		fromDate:fromDate,
		toDate:toDate,
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildDataForBars2(result,scope,"subLevel"+id);
		});
   }
   function buildDataForBars2(result,type,divId)
   {
	   var str='';
	  $("#ajaxImgStyle2").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th><h4 style="color:red"> CONSTITUENCY </h4></th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th><h4 style="color:red" > MANADAL </h4></th>';
	   }
		/* str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		if(type == "Constituency")
	   {
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
	   } */
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
		if(type == "Constituency"){
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'">'+result[i].name+'</td>';
		}
		  else if(type == "Mandal")
		   {
				var totalVoters = " - ";
				var targetCadres = " - ";
				var totalRecords = " - ";
				var percentage = 0;
				
				if(result[i].totalVoters !=null){
					totalVoters =  result[i].totalVoters;
				} 
				if(result[i].targetCadres !=null){
					targetCadres =  result[i].targetCadres;
				} 
				if(result[i].totalRecords !=null){
					totalRecords =  result[i].totalRecords;
				} 
				if(result[i].percentage !=null){
					percentage =  result[i].percentage;
				} 
				
			  
			  str+='<td title= "Total Voters - '+ totalVoters+ ' ,&nbsp;&nbsp;Target Voters - '+targetCadres+' ,Total Records -  '+totalRecords+'"><h4>'+result[i].name+'  &nbsp;&nbsp;('+percentage+'%)</h4>';
			  if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:15px;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:15px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:15px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:15px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:15px;">';
				} 
				str+='<div style="width:'+percentage+'%" class="bar" style="height:15px;"></div>';
		str+='</td></tr>';
			  
			  
		   }
		/* str+='<td>'+result[i].totalVoters+'</td>';
		str+='<td>'+result[i].targetCadres+'</td>';
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		if(result[i].percentage==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].percentage+'</td>';
		}
		str+='<td>'+result[i].totalAmount+'</td>';
		if(type == "Constituency")
		   {
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td>'+result[i].difference+'</td>';
		   } */
		
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
		/*if(type == "Constituency")
		$("#constituencyDynamicDiv").html(str);
		if(type == "Mandal")
		$("#MandalDynamicDiv").html(str);*/
		
		
		
   }
   $(".viewChart").click(function(){
	$("#toggleDiv").toggle();
   });
   
   $(".rprtType").click(function(){
	
		$("#leaderDataDiv").html("");
		$("#leaderDataDivStatusBars").html("");
		
		$("#ap").removeClass("btn-success");
		$("#tg").removeClass("btn-success");
   
		var val = $('input:radio[name=reportType]:checked').val();
		if(val == "bar"){
			$("#barReport").show();
			$("#tabularReport").hide();
		}else{
			$("#barReport").hide();
			$("#tabularReport").show();
		}
   });
   </script>
</body>
</html>