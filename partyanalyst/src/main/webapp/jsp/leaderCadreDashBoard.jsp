<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leader Cadre DashBoard</title>

 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/sample.js"></script>
<style>
.selected,.selectedchild{background:#EA4F5A !important;}
.selected1,.selected1child{background: #0BBA7C !important;}
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
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
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
						<td><b>Select State</b></td>
						<td>
						  <select id="statesDivId">
							
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
				     </tr>
				
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getLocationswiseleaderCadreInfo();" value="Submit"/> 
						<div><img id="ajaxImgStyle" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
				  </tr>
			</table>
					  <div id="leaderDataDiv"></div>
					  <div id="constituencyDynamicDiv"></div>
					   <div id="MandalDynamicDiv"></div>
			  </div>
			
			</div>
			
		</div>
   </div>
   <script>
   function getLocationswiseleaderCadreInfo()
   {
		
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
		str+='<th>District</th>';
		else if(type == "Constituency")
	   {
		str+='<th>District</th>';
		str+='<th>Parliament</th>';
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
		str+='<td class="removeCls clearClsTD'+result[i].id+'"><a onclick="displaySublevelDetails('+result[i].id+',\'District\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		if(type == "Constituency"){
		str+='<td>'+result[i].districtName+'</td>';	
		str+='<td>'+result[i].parliament+'</td>';
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
		str+='<td id="appendID">'+result[i].difference+'<span class="pull-right" onclick="closeDiv('+result[i].id+');"><i class="icon-remove"></i></span></td>';
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
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
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevel'+id+'" colspan="8" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
   if(type == "District")
	   {
	scope = "Constituency";
 	   }
   if(type == "Constituency")
	   {
	scope = "Mandal";
	   }
   var jObj = {
		type : type,
		id:id,
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildData1(result,scope,"subLevel"+id);
		});
   }


function displaySublevelDetails1(id,type)
   {
   var scope = "";
    $('.added1').remove('');
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selected1child"><td id="subLevel'+id+'" colspan="8" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;" src="images/icons/barloader.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#ajaxImgStyle2").show();
   if(type == "District")
	   {
	scope = "Constituency";
 	   }
   if(type == "Constituency")
	   {
	scope = "Mandal";
	   }
   var jObj = {
		type : type,
		id:id,
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseleaderCadreInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildData2(result,scope,"subLevel"+id);
		});
   }
   
function buildData1(result,type,divId)
   {
	 
	   var str='';
	  $("#ajaxImgStyle1").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th>Constituency</th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th>Mandal</th>';
	   }
		str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		if(type == "Constituency")
	   {
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
	   }
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
		if(type == "Constituency"){
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'"><a  onclick="displaySublevelDetails1('+result[i].id+',\'Constituency\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		}
		  else if(type == "Mandal")
		   {
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
		if(type == "Constituency")
		   {
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td>'+result[i].difference+'<span class="pull-right" onclick="closeDiv1('+result[i].id+');"><i class="icon-remove"></i></span></td>';
		   }
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


   function buildData2(result,type,divId)
   {
	 
	   var str='';
	  $("#ajaxImgStyle2").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th>Constituency</th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th>Mandal</th>';
	   }
		str+='<th>Total Voters</th>';
		str+='<th>Target Cadres</th>';
		str+='<th>Registered Cadres</th>';
		str+='<th>% of Register cadres</th>';
		str+='<th>Total Amount</th>';
		if(type == "Constituency")
	   {
		str+='<th>Received Amount</th>';
		str+='<th>Pending Amount</th>';
	   }
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
		if(type == "Constituency")
		   {
		str+='<td>'+result[i].paidAmount+'</td>';
		str+='<td>'+result[i].difference+'</td>';
		   }
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
   </script>
</body>
</html>