<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> 2014 CADRE REPORTS </title>

 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="daterangepicker/daterangepicker-bs3.css"/>

	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

<style>
 #mahilaAndYouth .selected, #mahilaAndYouth .selectedchild{background:lightblue !important;}
 #mahilaAndYouth .selected1, #mahilaAndYouth .selected1child{background: #cad5df !important;}
 
 #leaderDataDiv .selected, #leaderDataDiv .selectedchild{background:lightblue !important;}
 #leaderDataDiv .selected1, #leaderDataDiv .selected1child{background: #cad5df !important;}

 #leaderDataDivStatusBars .selected{background:lightblue !important;}
 #leaderDataDivStatusBars .selectedchild{background:lightblue !important;}
 #leaderDataDivStatusBars .selected1{background: #cad5df !important;}
 #leaderDataDivStatusBars .selected1child{background: #cad5df !important;}

  #CasteDataDiv .selected, #CasteDataDiv .selectedchild{background:lightblue !important;}
 #CasteDataDiv .selected1, #CasteDataDiv .selected1child{background: #cad5df !important;}
 
 table.target {
	font: 11px/24px Verdana, Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 750px;
	}
	
	#leaderDataDivStatusBars {margin-top:45px;}
	#leaderDataDivStatusBars h4{margin:0px;}
	#leaderDataDivStatusBars .progress{margin:0px;height:4px !important;}
	.percLbl{margin:4px;}
</style>
</head>
<body>

	
   <div class="container m_top10">
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">  2014 CADRE REPORTS </h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   
			  <!-- <div style="margin-left:250px;">
					<h4 style="color:black;"> <input type ="radio" style="margin-top:-2px;" class="rprtType" name="reportType" value="normal" checked="checked">&nbsp;&nbsp;TABULAR REPORT</input>
					 <input style="margin-top:-2px;margin-left:12px;" type ="radio" class="rprtType" name="reportType" value="bar">&nbsp; STATUS BAR REPORT </h4></input>
			   </div>-->
			   
			   <div style="margin-left:250px;">
					<h4 style="color:black;"> <input type ="radio" style="margin-top:-2px;" class="stType" name="stateType" value="ap" checked="checked">&nbsp;&nbsp;ANDHRA PRADESH</input>
					 <input style="margin-top:-2px;margin-left:12px;" type ="radio" class="stType" name="stateType" value="tg">&nbsp; TELANGANA </h4></input>
			   </div>
			   
			   
			 
			<table id="barReport" style="margin-left: 130px;">
				<tr>
					<td>
						<div id="reportTypeId">
							<a class="btn rprtCls" id="targetId" >&nbsp;TARGET VS ACHIEVED&nbsp;</a>
							<a class="btn rprtCls" id="womenId" >&nbsp;MAHILA & YOUTH&nbsp;</a>
							<a class="btn rprtCls" id="communityId" >&nbsp;COMMUNITY WISE REPORT&nbsp;</a>
						</div>
					</td>
					<td></td>
					<td></td>
				  </tr>
				  
			</table>	  
					 <div>
						<img id="ajaxImg" style="width:80px;display:none;margin-left:350px;margin-top:20px;" src="images/Loading-data.gif"/>
					 </div>
					  <div id="mahilaAndYouth"></div>
					  <div id="leaderDataDiv"></div>
					  <div id="leaderDataDivStatusBars"></div>
					  <div id="constituencyDynamicDiv"></div>
					  <div id="MandalDynamicDiv"></div>
					  <div id="CasteDataDiv"></div>
			  </div>
			
			</div>
			
		</div>
		
   </div>
   
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	
	<script type="text/javascript" src="js/sample.js"></script>
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
   
   function closeDiv1(trID){
		$(".selected1child").remove();
   }
   function closeDiv(trID){
		$(".selectedchild").remove();
   }
   
   function buildSublocationInfoOfConstiYouthAndMahila(result,type,divId)
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
		var fromDate="15-10-2014";
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
		str+='<table class="table-bordered" style="width:900px;margin-left:10px;" id="tabledataTab">';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "District")
		str+='<th><h4 style="color:red;float:left;margin-left:10px;">DISTRICTS</h4></th>';
		else if(type == "Constituency"){
		str+='<th><h4 style="color:red;float:left;margin-left:10px;margin-top:10px;">DISTRICTS</h4></th>';
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
		str+='<td style="width:800px;padding:12px;border-bottom:1px solid #cdcdcd;" class="removeCls clearClsTD'+result[i].id+'" ><a onclick="displaySublevelDetailsForBars('+result[i].id+',\'District\');" style="cursor:pointer;"><h4>'+result[i].name+'';
		
		if(percentage <= 20){
						str+='<span class=" label label-important percLbl">&nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 20 && percentage <= 40){
						str+='<span class=" label label-warning percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 40 && percentage <= 60){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 60 && percentage <= 80){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else{
						str+='<span class=" label label-success percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
		//str+='&nbsp;&nbsp; ('+percentage+'%)';
		str+='<span class="pull-right" style="font-size:14px;font-weight:normal;">  Voters - '+ totalVoters+ ', Target - '+targetCadres+', Acheived -  '+totalRecords+'  </span></h4></a>';
		if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:10x;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:10px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:10px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:10px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:10px;">';
				} 
				
				str+='<div style="width:'+result[i].percentage+'%" class="bar" style="height:10px;"></div>';
		
		
		str+='</td>';
		
		
		str+='<td id="appendID" style="border-left:0px;"><span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		str+='</tr>';
	   }
	    str+='</tbody>';
		str+='</table>';
    	$("#leaderDataDivStatusBars").html(str);
   }
   function displaySublevelDetailsForBars(id,type)
   {
   var scope = "";
    $('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevelConsti'+id+'" colspan="8" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
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
	   fromDate="15-10-2014";
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
		buildDataForBars1(result,scope,"subLevelConsti"+id);
		});
   }
   
   function buildDataForBars1(result,type,divId)
   {
	   var str='';
	  $("#ajaxImgStyle1").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1" style="width:800px;margin-left:50px;background:#f3f3f3;">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th> <h4 style="color:red"> CONSTITUENCIES </h4></th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th> <h4 style="color:red"> MANDALS </h4></th>';
	   }
	   str +="<th style='border-left:0px;'></th>";
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
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'" style="padding:12px;" ><a  onclick="displaySublevelDetailsForBars1('+result[i].id+',\'Constituency\');" style="cursor:pointer;"><h4>'+result[i].name+'';
			
			if(percentage <= 20){
						str+='<span class=" label label-important percLbl">&nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 20 && percentage <= 40){
						str+='<span class=" label label-warning percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 40 && percentage <= 60){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 60 && percentage <= 80){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else{
						str+='<span class=" label label-success percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					
					
			//str+='&nbsp;&nbsp; ('+percentage+'%)';
			str+='<span class="pull-right" style="font-size:14px;font-weight:normal;">  Voters - '+ totalVoters+ ', Target - '+targetCadres+', Acheived -  '+totalRecords+'  </span></h4></a>';
			if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:10px;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:10px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:10px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:10px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:10px;">';
				} 
				str+='<div style="width:'+percentage+'%" class="bar" style="height:10px;"></div>';
				str+='</div></td>';
				str +='<td style="border-left:0px;"><span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span>';
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
		str+='<td>'+result[i].totalAmount+'</td>';*/
		if(type == "Constituency")
		   {
		//str+='<td>'+result[i].paidAmount+'</td>';
		//str+='<td><span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
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
   function displaySublevelDetailsForBars1(id,type)
   {
   var scope = "";
    $('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selected1child"><td id="subLevel'+id+'" colspan="8" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;width:70px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#ajaxImgStyle2").show();
	$("#iconDiv1"+id).show();
	if(type == "District"){
			scope = "Constituency";
 	}if(type == "Constituency"){
		scope = "Mandal";
	  }
	   fromDate="15-10-2014";
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
	   
		str+='<table class="table table-bordered" id="tabledata1" style="width: 800px; margin-left: 50px;">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	   {
		
		str+='<th><h4 style="color:red"> CONSTITUENCIES </h4></th>';
	   }
	   else if(type == "Mandal")
	   {
		   str+='<th><h4 style="color:red" > MANADALS </h4></th>';
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
				
			  
			  str+='<td style="padding:12px;"><h4>'+result[i].name+'';
			  
					if(percentage <= 20){
						str+='<span class=" label label-important percLbl">&nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 20 && percentage <= 40){
						str+='<span class=" label label-warning percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 40 && percentage <= 60){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else if(percentage > 60 && percentage <= 80){
						str+='<span class=" label label-info percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					else{
						str+='<span class=" label label-success percLbl"> &nbsp;&nbsp;('+percentage+'%) </span>';
					}
					
					
			  //str+='&nbsp;&nbsp;('+percentage+'%)';
			  str+='<span class="pull-right" style="font-size:14px;font-weight:normal;">  Voters - '+ totalVoters+ ' ,Target - '+targetCadres+' ,Acheived -  '+totalRecords+'  </span></h4>';
			  if(percentage <= 20){
				   str+='<div class="progress progress-danger" style="height:10px;">';
				}else if(percentage > 20 && percentage <= 40){
				   str+='<div class="progress progress-warning" style="height:10px;">';
				}else if(percentage > 40 && percentage <= 60){
				   str+='<div class="progress progress-info" style="height:10px;">';
				}else if(percentage > 60 && percentage <= 80){
				   str+='<div class="progress" style="height:10px;">';
				}else{
				   str+='<div class="progress progress-success" style="height:10px;">';
				} 
				str+='<div style="width:'+percentage+'%" class="bar" style="height:10px;"></div>';
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
   
   /* $(".stType").click(function(){
	
		$("#leaderDataDiv").html("");
		$("#leaderDataDivStatusBars").html("");
		
		$("#ap").removeClass("btn-success");
		$("#tg").removeClass("btn-success");
   
		//var val = $('input:radio[name=reportType]:checked').val();
		var val = $('input:radio[name=stateType]:checked').val();
		if(val == "bar"){
			$("#barReport").show();
			$("#tabularReport").hide();
		}else{
			$("#barReport").hide();
			$("#tabularReport").show();
		}
   }); */
    $(".stType").change(function(){
		
		$("#targetId").removeClass("btn-success");
		$("#womenId").removeClass("btn-success");
		$("#communityId").removeClass("btn-success");
		clearDataDivs();
		
   });
   $(".rprtCls").click(function(){
	var divId = $(this).attr("id");
	$(this).parent().find("a.btn-success").removeClass("btn-success");
	$(this).addClass("btn-success");

	var state = $('input:radio[name=stateType]:checked').val();
	var stateId = 1;
	if(state == "tg"){
		stateId = 2;
	}
	
	clearDataDivs();
	
	
		if(divId == "targetId"){
			getLocationswiseleaderCadreInfoForBars(state);
		}
		if(divId == "womenId"){
			getYouthAndMahilaInfo(stateId,"district");
		}
		if(divId == "communityId"){
			getCasteWiseInfo(stateId,"DISTRICT");
		}
	
   });
   
   function clearDataDivs(){
		$("#leaderDataDivStatusBars").html("");
		$("#mahilaAndYouth").html("");
		$("#CasteDataDiv").html("");
   }
   
   
   //getYouthAndMahilaInfo(1,"district");
   function getYouthAndMahilaInfo(id,type){
		$("#ajaxImg").show();
	   var fromDate="15-10-2014";
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
			locationType : type,
			locationId:id,
			fromDate:fromDate,
			toDate:toDate,
			task:"mainLevel"
		}
		
		$.ajax({
			type:'GET',
			url: 'getLocationswiseYouthAndMahilaInfoAction.action',
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$("#ajaxImg").hide();
			buildYouthAndMahilaMain(result,type);
		});
   }

   /* add */
   function getCasteWiseInfo(stId,type){
		$("#ajaxImg").show();
		var jObj = {
			stateId:stId,
			locationtype:type,
			task:"mainLevel"
		}
		$.ajax({
          type:'GET',
          url: 'getLocationswiseCasteInfoAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$("#ajaxImg").hide();
			buildDataForCaste(result.infoList);
		});
   }

   function buildDataForCaste(result){
   
	   var str='';
		str+='<table class="table table-bordered" id="casteTable">';
		str+='<thead>';
		str+='<tr>';
		str+='<th rowspan="2">District</th>';
		
		str+='<th rowspan="2">Total Voters</th>';
		str+='<th rowspan="2">Registred Cadre</th>';
		for(var i in result[0].infoList){
			str+='<th colspan="2">'+result[0].infoList[i].casteCategory+'</th>';
		}
		
		str+='</tr>';
		str+='<tr>';
		for(var i in result[0].infoList){
		str+='<th>Total Cadre</th>';
		str+='<th>%</th>';
	   }
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
		str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		
		str+='<td class="removeCls clearClsTD'+result[i].id+'"><a onclick="displaySublevelCasteDetails('+result[i].id+',\'DISTRICT\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		
		str+='<td>'+result[i].totalVoters+'</td>';
		
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		for(var j in result[i].infoList) {
			str+='<td>'+result[i].infoList[j].totalCount+'</td>';
			if(result[i].infoList[j].percentage != null){
				str+='<td>'+result[i].infoList[j].percentage+'% ';
			}
			else{
				str+='<td> - ';
			}
		}
		
		str+='<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#CasteDataDiv").html(str);
		$("#casteTable").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
		var firstRowId = $('#casteTable tr').eq(2).attr('id');
		
	   displaySublevelCasteDetails(firstRowId.replace('"',''),'DISTRICT');
	
   }

   function displaySublevelCasteDetails(id,type){
	$('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevelConstis'+id+'" colspan="14" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+id).show();
	    var jObj = {
		stateId:1,
		locationtype:type,
		Id:id,
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getSubLocationswiseCasteInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildDataForSublevelCaste1(result.infoList,type,"subLevelConstis"+id);
		});
   }
function displaySublevelCasteDetails1(id,type){
	$('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selected1child"><td id="subLevel'+id+'" colspan="14" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#iconDiv1"+id).show();
	$("#ajaxImgStyle2").show();
	    var jObj = {
			stateId:1,
			locationtype:type,
			Id:id,
			task:"subLevel"
		}
		$.ajax({
			type:'GET',
			url: 'getSubLocationswiseCasteInfoAction.action',
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			buildDataForSublevelCaste2(result.infoList,type,"subLevel"+id);
		});
   }
    function buildDataForSublevelCaste2(result,type,divId){
	  var str='';
	  $("#ajaxImgStyle2").css("display","none");
	   str+='<table class="table table-bordered" style="width:990px;">';
		str+='<thead>';
		str+='<tr>';
		str+='<th rowspan="2">Mandal</th>';
		
		str+='<th rowspan="2">Total Voters</th>';
		str+='<th rowspan="2">Registred Cadre</th>';
		for(var i in result[0].infoList){
			str+='<th colspan="2">'+result[0].infoList[i].casteCategory+'</th>';
		}
		str+='</tr>';
		str+='<tr>';	
			for(var i in result[0].infoList){
				str+='<th> Cadre </th>';
				str+='<th> % </th>';
			}
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
		str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		
		str+='<td class="removeCls clearClsTD'+result[i].id+'">'+result[i].name+'</td>';
		
		str+='<td>'+result[i].totalVoters+'</td>';
		
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		for(var j in result[i].infoList){
			str+='<td>'+result[i].infoList[j].totalCount+'</td>';
			if(result[i].infoList[j].percentage != null){
				str+='<td>'+result[i].infoList[j].percentage+'%';
				str+='<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
			}else{
				str+='<td> - ';
				str+='<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
			}
	   }
		str+='</tr>';
	   }
	   str+='</tbody>';
	   str+='</table>';
	   $("#"+divId).html(str);
			
   }
    function buildDataForSublevelCaste1(result,type,divId){
		var str='';
		str+='<table class="table table-bordered" id="tabledataTab1">';
		str+='<thead>';
		str+='<tr>';
		str+='<th rowspan="2">Constituency</th>';
		
		str+='<th rowspan="2">Total Voters</th>';
		str+='<th rowspan="2">Registred Cadre</th>';
		for(var i in result[0].infoList){
			str+='<th colspan="2">'+result[0].infoList[i].casteCategory+'</th>';
		}
		str+='<tr>';	
			for(var i in result[0].infoList){
				str+='<th> Cadre </th>';
				str+='<th> % </th>';
			}
		str+='</tr>';
		str+='</tr>';
		str+='<tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
		//str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
		
		str+='<td class="removeCls1 clearClsTD1'+result[i].id+'"><a  onclick="displaySublevelCasteDetails1('+result[i].id+',\'Constituency\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		
		//str+='<td class="removeCls clearClsTD'+result[i].id+'"><a onclick="displaySublevelCasteDetails1('+result[i].id+',\'CONSTITUENCY\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		
		str+='<td>'+result[i].totalVoters+'</td>';
		
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
			str+='<td>'+result[i].totalRecords+'</td>';
		}
		for(var j in result[i].infoList){
			str+='<td>'+result[i].infoList[j].totalCount+'</td>';
			if(result[i].infoList[j].percentage != null){
				str+='<td>'+result[i].infoList[j].percentage+'%';
			}else{
				str+='<td> - ';
			}
	   }
	   str+='<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		str+='</tr>';
	   }
	   str+='</tbody>';
	   str+='</table>';
	   $("#"+divId).html(str);
   }
   /* end */
   
   function buildYouthAndMahilaMain(result,type){
	   var str='';
		str+='<table class="table table-bordered" id="tabledataTab">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>District</th>';
		str+='<th>Total Voters</th>';
		str+='<th>Total Registered Cadres(%)</th>';
		str+='<th> Registered Mahila Cadres(%)</th>';
		str+='<th> Registered Youth Cadres(%)</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
		str+='<tr id='+result[i].id+' class="removeCls clearCls'+result[i].id+'">';
		str+='<td class="removeCls clearClsTD'+result[i].id+'"><a onclick="getSublocationOfDistYouthAndMahila('+result[i].id+',\'District\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		if(result[i].totalVoters != null){
		  str+='<td>'+result[i].totalVoters+'</td>';
		}else{
		 str+='<td>-</td>';
		}
		if(result[i].totalCount == null){
		 str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalCount;
		  if(result[i].cadrePerc != null){
		    str+='('+result[i].cadrePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].registeredCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].registeredCadres;
		  if(result[i].percentage != null){
		    str+='('+result[i].percentage+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalAmount==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalAmount;
		  if(result[i].femalePerc != null){
		    str+='('+result[i].femalePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].targetCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].targetCadres;
		  if(result[i].totalYouthPerc != null){
		    str+='('+result[i].totalYouthPerc+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalRecords;
		  if(result[i].malePerc != null){
		    str+='('+result[i].malePerc+'%)';
		  }
		 str+='<span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		}
		
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#mahilaAndYouth").html(str);
		$("#tabledataTab").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
		var firstRowId = $('#tabledataTab tr').eq(2).attr('id');		
		getSublocationOfDistYouthAndMahila(firstRowId.replace('"',''),'District');
   }
   function getSublocationOfDistYouthAndMahila(id,type) {
		
    var scope = "";
    $('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevelConst'+id+'" colspan="8" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+id).show();
	if(type == "District"){
		scope = "Constituency";
 	}
	if(type == "Constituency"){
		scope = "Mandal";
	}
   var jObj = {
		locationType : type,
		locationId :id,
		fromDate:"",
		toDate:"",
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getLocationswiseYouthAndMahilaInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
		buildDistSublocationYouthAndMahila(result,scope,"subLevelConst"+id);
		});
   }


  function getSublocationOfConstiYouthAndMahila(id,type){
	
   var scope = "";
    $('.added1').remove('');
	$(".removeicon1").hide();
	$(".removeCls1").removeClass("selected1");
	$('.clearCls1'+id).after('<tr class="selected1child"><td id="subLevel'+id+'" colspan="8" class="added1"><div><img id="ajaxImgStyle2" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls1'+id).addClass("selected1");
	$('.clearClsTD1'+id).addClass("selected1");
	$("#ajaxImgStyle2").show();
	$("#iconDiv1"+id).show();
   if(type == "District"){
		scope = "Constituency";
 	}
   if(type == "Constituency"){
		scope = "Mandal";
	}
   var jObj = {
		locationType : type,
		locationId:id,
		fromDate:"",
		toDate:"",
		task:"subLevel"
		}
		 $.ajax({
          type:'GET',
          url: 'getLocationswiseYouthAndMahilaInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
			buildSublocationInfoOfConstiYouthAndMahila(result,scope,"subLevel"+id);
		});
   }  
   
	function buildDistSublocationYouthAndMahila(result,type,divId){
	  var str='';
	  $("#ajaxImgStyle1").css("display","none");
	   
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		if(type == "Constituency")
	    {
		  str+='<th> Constituency</th>';
	    }
	    else if(type == "Mandal")
	    {
		   str+='<th> Mandal</th>';
	    }
		str+='<th> Total Voters</th>';
		str+='<th> Total Registered Cadres(%)</th>';
		str+='<th> Registered Mahila Cadres(%)</th>';
		str+='<th> Registered Youth Cadres(%)</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls1 clearCls1'+result[i].id+'">';
		
		if(type == "Constituency"){
			str+='<td class="removeCls1 clearClsTD1'+result[i].id+'"><a  onclick="getSublocationOfConstiYouthAndMahila('+result[i].id+',\'Constituency\');" style="cursor:pointer;">'+result[i].name+'</a></td>';
		}
	   else if(type == "Mandal")
	   {
		  str+='<td>'+result[i].name+'</td>';
	   }
		if(result[i].totalVoters != null){
		  str+='<td>'+result[i].totalVoters+'</td>';
		}else{
		 str+='<td>-</td>';
		}
		if(result[i].totalCount == null){
		 str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalCount;
		  if(result[i].cadrePerc != null){
		    str+='('+result[i].cadrePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].registeredCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].registeredCadres;
		  if(result[i].percentage != null){
		    str+='('+result[i].percentage+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalAmount==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalAmount;
		  if(result[i].femalePerc != null){
		    str+='('+result[i].femalePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].targetCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].targetCadres;
		  if(result[i].totalYouthPerc != null){
		    str+='('+result[i].totalYouthPerc+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalRecords;
		  if(result[i].malePerc != null){
		    str+='('+result[i].malePerc+'%)';
		  }
		 str+='<span class="pull-right removeicon1"  id="iconDiv1'+result[i].id+'" onclick="closeDiv1('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span></td>';
		}
	  }
		str+='</tr>';
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
   }


   function buildSublocationInfoOfConstiYouthAndMahila(result,type,divId){
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
		str+='<th> Total Voters</th>';
		str+='<th> Total Registered Cadres(%)</th>';
		str+='<th> Registered Mahila Cadres(%)</th>';
		str+='<th> Registered Youth Cadres(%)</th>';
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
		if(result[i].totalVoters != null){
		  str+='<td>'+result[i].totalVoters+'</td>';
		}else{
		 str+='<td>-</td>';
		}
		if(result[i].totalCount == null){
		 str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalCount;
		  if(result[i].cadrePerc != null){
		    str+='('+result[i].cadrePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].registeredCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].registeredCadres;
		  if(result[i].percentage != null){
		    str+='('+result[i].percentage+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalAmount==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalAmount;
		  if(result[i].femalePerc != null){
		    str+='('+result[i].femalePerc+'%)';
		  }
		 str+='</td>';
		}
		/* if(result[i].targetCadres==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].targetCadres;
		  if(result[i].totalYouthPerc != null){
		    str+='('+result[i].totalYouthPerc+'%)';
		  }
		 str+='</td>';
		} */
		if(result[i].totalRecords==null){
			str+='<td>-</td>'
		}else{
		 str+='<td>'+result[i].totalRecords;
		  if(result[i].malePerc != null){
		    str+='('+result[i].malePerc+'%)';
		  }
		 str+='</td>';
		}
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);
			
   }

   </script>
   <script>
	
   </script>
</body>
</html>