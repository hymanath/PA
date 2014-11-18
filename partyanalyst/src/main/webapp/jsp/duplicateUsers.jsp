<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leader Cadre DashBoard</title>

 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"> 
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/sample.js"></script>
<style>

</style>
</head>
<body>
   <div class="container m_top10">
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> Duplicate Users</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDate" /></td></tr>
				  <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getDuplicateUsersInfo();" value="Submit"/> 
						<div><img id="ajaxImgStyle" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
				  </tr>
			</table>
			 <div id="userDataDiv"></div>
					 
			  </div>
			
			</div>
			
		</div>
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
   function getDuplicateUsersInfo()
   {
		$("#userDataDiv").html('');
	
		$("#ajaxImgStyle").show();
		var jObj = {
		
		fromDate:$("#fromDate").val(),
		toDate:$("#toDate").val(),
		task:"getUsers"
		}
		 $.ajax({
          type:'GET',
          url: 'duplicateUsersDetails.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgStyle").hide();
				buildData(result,$("#fromDate").val(),$("#toDate").val());
		});
   }
    function getUsersInfo(fromDate,toDate,userId,locationId,type,constituencyId)
   {
		
		var jObj = {
		
		fromDate:fromDate,
		toDate:toDate,
		userId:userId,
		locationId:locationId,
		type:type,
	    constituencyId:constituencyId,
		task:"usersData"
		}
		 $.ajax({
          type:'GET',
          url: 'duplicateUsersDetails.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				
				buildData1(result);
		});
   }
function buildData(resultList,fromDate,toDate)
   {
	  
	
		var result = resultList.infoList;
		var str='';
		str+='<table class="table table-bordered" id="tabledataTab">';
		str+='<thead>';
		str+='<tr>';
		
		//str+='<th>District</th>';
		//str+='<th>Parliament</th>';
		str+='<th>Constituency</th>';
		str+='<th>Mandal</th>';
		str+='<th>User</th>';
		str+='<th>Count</th>';
		
		str+='<th>Name</th>';
		str+='<th>Mobile</th>';
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		
		
	   if(result != null)
		{
			for(var i in result)
			{
				str += '<tr>';

				 var totalUsers = result[i].totalAmount;
				str += '<td rowspan='+totalUsers+'>'+result[i].name+'</td>';
				if(result[i].infoList.length > 0)
				{
				for(var j in result[i].infoList)
				{

					str += '<td rowspan='+result[i].infoList[j].infoList.length+'>'+result[i].infoList[j].name+'</td>';
					for(var k in result[i].infoList[j].infoList)
					{
						str += '<td><a onclick="getUsersInfo(\''+fromDate+'\',\''+toDate+'\',\''+result[i].infoList[j].infoList[k].id+'\',\''+result[i].infoList[j].id+'\',\''+result[i].infoList[j].userType+'\',\''+result[i].id+'\');">'+result[i].infoList[j].infoList[k].userName+'</a></td>';
						str += '<td>'+result[i].infoList[j].infoList[k].totalCount+'</td>';
						str += '<td>'+result[i].infoList[j].infoList[k].userType+'</td>';
						str += '<td>'+result[i].infoList[j].infoList[k].mobileNo+'</td>';
						
						str += '</tr>';	
					}
				}
				}

				
			}
	   }



		str+='</tbody>';
		str+='</table>';
		$("#userDataDiv").html(str);
		$("#tabledataTab").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
				 
		
   }
   
  
  
   </script>
</body>
</html>