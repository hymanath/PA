<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADRE DASHBOARD</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/test.js"></script> 
<style>
	
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   
		
	</style>
	<script>


	
	</script>
</head>
<body>
 <div class="container m_top10">


<div class="span12 well well-small border-radius-0 mb-0 " style="background:#ffffff;">
<h3 class="text-center text-uppercase">ASSIGN USER</h3>
</div>


 <div class="row-fluid span12 well well-small border-radius-0 mb-0 " style="background:#ffffff;">
  <table  style="margin-left: 270px;">
   <tr id="statedisplaydivid">
						<td><b>Select State</b></td>
						<td>
						  <select id="stateId" onchange="getConstituencies();">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
				     </tr>
					  <tr id="constdisplaydivid" onchange="getConstituencySurveyUsers();">
					   <td><b>Select Constituency </b></td>
					   <td><select id="constituencyId"></select></td>
				   </tr>
				    <tr id="selectuserIdDiv">
					   <td><b>Select User </b></td>
					   <td><select id="userId" onchange="getAssignedUsersForCadresurveyUser();"></select></td>
				   </tr>
  </table>
  <div id="ExistingUsersDiv"> </div>
	
  <div id="addUsersDiv" style="width: 800px; margin-right: auto; margin-left: auto;display:none;" class="widget" id="addUsersDiv">
  <h3 class="text-center text-uppercase">ADD USER</h3>
  <div id="errorDiv"></div>
    <form class="form-inline">
   Name <input type="text" class="input-small" placeholder="Name" id="name">
   Mobile <input type="text" class="input-small" placeholder="Mobile" id="mobile">
   Affected Date<input type="text" readonly="readonly" id="fromDate" style="width: 120px;">
   
    <button type="button" class="btn" onclick="addUser()">Add</button>
	<img id="addAjaxImg" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/>
    </form>
  </div>
 </div>

 </div>
 <script type="text/javascript">

   $(document).ready(function(){
		
	});

		function getConstituencies(){
		var stateId = $("#stateId").val();
		$("#constituencyId").html("");
		
		var jObj ={
			stateid:stateId,				  
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>Select Constituency</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#constituencyId").html(str);
		});
	}
	function getConstituencySurveyUsers()
	{
		var constiId = $("#constituencyId").val();
		$("#userId").html("");
		var jObj ={
			
			constituencyId:constiId,	
				
			task:"getUsers"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstituencySurveyUsersAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>Select User</option>";
		   for(var i in result){
				str +='<option value='+result[i].userId+'>'+result[i].name+'</option>';
			}
			$("#userId").html(str);
		});
	}

	function getAssignedUsersForCadresurveyUser()
	{
		
		$("#ExistingUsersDiv").html('');
		var constiId = $("#constituencyId").val();
		var userId =   $("#userId").val();
		
		var jObj ={
			
			constituencyId:constiId,
			userId:userId,
			task:""             
		}	
		$.ajax({
			type : "POST",
			url : "getAssignedUsersForCadresurveyUser.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
				
				
				if(result  != null)
				{
					buildUsers(result);
					
					
				}
				
				
		});
		
		
	}
	function addUser()
	{
	   $("#errorDiv").html('');
	   var flag = false;
		var name = $("#name").val().trim();
		var userId =   $("#userId").val();
		var mobileNo = $("#mobile").val().trim();
		var startDate = $("#fromDate").val();
		var inputArr = new Array();
		var erroStr = "";
		if(name == 0)
		{
			flag = true;
			erroStr +='Name is required..';
		}
		else if(userId == 0)
		{
				flag = true;
			erroStr +='User is required..';
		}
		$("#addAjaxImg").show();
		var obj = {
			
			startDate:startDate,
			userId:userId,
			mobileNo:mobileNo,
			name:name
		}
		inputArr.push(obj);
		var jObj = {
			inputArr:inputArr,
			task : "save"
		}
		$.ajax({
			type : "POST",
			url : "insertcadreSurveyUserAssignAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
				$("#addAjaxImg").hide();
				if(result.resultCode == 0)
				$("#errorDiv").html('User added successfully...').css("color","green");
				else if(result.resultCode == 121)
				$("#errorDiv").html('User already exists...').css("color","red");
				else
				$("#errorDiv").html('Error Occured,Try again...').css("color","red");
			getAssignedUsersForCadresurveyUser();	
		});
		
	}
	function buildUsers(result)
{
		var str = '';
					str+='<table class="table table-bordered">';
					str+='<tr>';
					str+='<th>Name</th>';
					str+='<th>MobileNo</th>';
					str+='<th>StartDate</th>';
					str+='<th>endDate</th>';
					str+='</tr>';
				 for(var i in result){
					   str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					if(result[i].number == "")
					str+='<td>-</td>';
					else
					str+='<td>'+result[i].number+'</td>';
					str+='<td>'+result[i].fromDate+'</td>';
					if(result[i].date == "")
					str+='<td>-</td>';
					else
					str+='<td>'+result[i].date+'</td>';
					str+='</tr>';
					  }
					str+='</table>';
					$("#ExistingUsersDiv").html(str);
					
					$("#addUsersDiv").show();
} 
	  $("#fromDate").datepicker({
		dateFormat: "dd-mm-yy",
		changeMonth: true,
        changeYear: true,
		maxDate: new Date()
	})
	$("#fromDate").datepicker("setDate", new Date());
	
	</script>
</body>
</html>