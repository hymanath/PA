<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Booth Analysis</title>

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
					<h3 class="text-center text-uppercase">  Cadre Booth Analysis</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   
			
			   
			   
			   <table  style="margin-left: 270px;" id="tabularReport">
					
				     <tr id="statedisplaydivid">
						<td><b>Select State :</b></td>
						<td>
						  <select id="statesDivId">
							
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
				     </tr>
				  
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getConstituencyWiseBoothInfo();" value="Submit"/> 
						<div><img id="ajaxImgStyle" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
					  
					  
				  </tr>
				  
			</table>
			  
					  <div id="boothDataDiv"></div>
					  
			  </div>
			
			</div>
			
		</div>
   </div>
   <script>

   function getConstituencyWiseBoothInfo()
   {
		$("#errStatusDiv").html("");
	
		var stateId = $("#statesDivId").val();
		$("#boothDataDiv").html('');
		$("#ajaxImgStyle").show();
		var jObj = {
		
		stateId:stateId,
		task:""
		}
		 $.ajax({
          type:'GET',
          url: 'getCadreBoothAnalysisInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgStyle").hide();
				buildData(result);
		});
   }
   function buildData(result)
   {
	   var str='';
	  str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>Constituency</th>';
		str+='<th>Total Booths</th>';
		str+='<th>Registration Started Booths</th>';
		str+='<th>Booths Below 10</th>';
		//str+='<th>Male(30%)</th>';
		//str+='<th>FemMale(30%)</th>';
		str+='<th>Difference(30%)</th>';
	
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr>';
		
	   str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].totalBooths+'</td>';

		str+='<td>'+result[i].startedBooths+'</td>';
		str+='<td>'+result[i].belowCadres+'</td>';
		//str+='<td>'+result[i].mCount+'</td>';
		//str+='<td>'+result[i].fCount+'</td>';
		str+='<td>'+result[i].count+'</td>';
		
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#boothDataDiv").html(str);
		$("#tabledata1").dataTable();
   }
 </script>
</body>
</html>