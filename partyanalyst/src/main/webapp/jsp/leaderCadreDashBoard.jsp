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
						<img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					  </td>
				  </tr>
			</table>
					  <div id="leaderDataDiv"></div>
					  <div id="constituencyDynamicDiv"></div>
					   <div id="tehsilDynamicDiv"></div>
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
		str+='<table class="table table-bordered" id="tabledata">';
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
		str+='<tr>';
		if(type == "District")
	str+='<td><a onclick="displaySublevelDetails('+result[i].id+',\'District\');">'+result[i].name+'</a></td>';
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
		str+='<td>'+result[i].difference+'</td>';
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#leaderDataDiv").html(str);
		$("#tabledata").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
		
   }
   
   function displaySublevelDetails(id,type)
   {
   var scope = "";
   if(type == "District")
	   {
	scope = "Constituency";
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
				
				buildData1(result,scope);
		});
   }
   
function buildData1(result,type)
   {
	  
	   var str='';
	    str+='<h4>Constituency Details In District</h4>';
		str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "District")
		str+='<th>District</th>';
		else if(type == "Constituency")
	   {
		//str+='<th>District</th>';
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
		str+='<tr>';
		if(type == "District")
		str+='<td><a  onclick="displaySublevelDetails('+result[i].id+',\'Constituency\');">'+result[i].name+'</a></td>';
		if(type == "Constituency"){
		//str+='<td>'+result[i].districtName+'</td>';	
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
		str+='<td>'+result[i].difference+'</td>';
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		if(type == "Constituency")
		$("#constituencyDynamicDiv").html(str);
		$("#tabledata1").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
		
   }
   </script>
</body>
</html>