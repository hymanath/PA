<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Age Gender Analysis</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

<style>
#constituencyDivId{display:none;}
</style>
</head>
<body>
   <div class="container m_top10">
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase"></h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errDiv"  style="margin-left: 270px;" ></div>
			   <table  style="margin-left: 270px;">
					<tr>
					   <td><b>Select State : </b></td>
					   <td>
						 <select id="stateId" onChange="getConstituencies();">
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
					 </tr>
					 <tr>
					   <td><b>Select Scope : </b></td>
					   <td>
						  <select id="scopeId" onChange="showCorrespondingDivs(this.value);">
						    <option value="0">Select Scope</option>
							<option value="1">District</option>
							<option value="2">Constituency</option>
							<option value="3">Mandal/Muncipality</option>
							<option value="4">Panchayat</option>
							<option value="5">Booth</option>
						  </select>
						</td>
					 </tr>
					 <tr id="constituencyDivId">
					   <td><b>Select Constituency : </b></td>
					   <td><select id="constituencyId"></select></td>
					 </tr>
					 
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getLocationWiseGenderAgeCountsInfo();" value="Submit"/>
						<img id="ajaxImg" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					  </td>
				  </tr>
			</table>
			<div id="resultDiv" style="overflow-x:scroll;"></div>
			</div>			
		</div>
      </div>
   </div>
   
   <script>
   
   function showCorrespondingDivs(scopeId){
		
		if(scopeId == 3 || scopeId == 4 || scopeId == 5){		
			$("#constituencyDivId").show();
			getConstituencies();
		}else{
			$("#constituencyDivId").hide();
		}
   }
   
   function getConstituencies(){
		var stateId = $("#stateId").val();
		$("#displayconstbox2").html("");
		var str ="";
		var jObj ={
			stateid:stateId,				  
			task:"getConstituencies"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#constituencyId").html(str);
		});
	}
   
  function getLocationWiseGenderAgeCountsInfo()
   {
		$("#resultDiv").html("");
		var scopeId = $("#scopeId").val();
		var scopeVal = $("#scopeId option:selected").text();
		var stateId = $("#stateId").val();
		var constituencyId = 0;
		if(scopeId == 0)
		{
			$("#errDiv").html("Select Scope").css("color","red");
			return;
		}
		
		if(scopeId == 3 || scopeId == 4 || scopeId == 5){
		   constituencyId = $("#constituencyId").val();
		   if(constituencyId == 0)
			{
			$("#errDiv").html("Select Constituency").css("color","red");
			return;
			}
		}
		else{
		$("#errDiv").html("");
		}
	   if(scopeId == 3){
	   scopeVal = "Tehsil";
	   }
	   else{
	   scopeVal = scopeVal;
	   }
		$("#errDiv").html("");
		$("#ajaxImg").show();
		var jObj = {
		type : scopeVal,
		stateId : stateId,
		constituencyId : constituencyId
		}
		 $.ajax({
          type:'GET',
          url: 'getLocationWiseGenderAgeInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				
				$("#ajaxImg").hide();
				
				buildData(result,scopeId);
		});
   }
   
    function buildData(result,type)
   {
		$("#resultDiv").html("");
		var str='';
		str+='<table class="table table-bordered" id="tabledata">';
		str+='<thead>';
		str+='<tr>';
		
		if(type == 1)
		str+='<th rowspan="3">District</th>';
		else if(type ==  2)
		{
		str+='<th rowspan="3" >District</th>';
		str+='<th rowspan="3" >Parliament</th>';
		str+='<th rowspan="3" >Constituency</th>';
		}
		else if(type == 3)
		str+='<th rowspan="3">Mandal/Muncipality</th>';
		else if(type == 4)
		str+='<th rowspan="3">Panchayat</th>';
		else if(type == 5)
			str+='<th rowspan="3">Booth</th>';
		
		str+='<th rowspan="3">Total Voters</th>';
		str+='<th rowspan="3">Registered Cadres</th>';
		str+='<th colspan="2">Male</th>';
		str+='<th colspan="2">Female</th>';
		str+='<th colspan="6">Age</th>';
		
		str+='</tr>';
		
		str+='<tr>';
		  str+='<th rowspan="2"> Voters Count</th>';
		  str+='<th rowspan="2"> Cadre Count</th>';	
			  
		  str+='<th rowspan="2"> Voters Count</th>';
		  str+='<th rowspan="2"> Cadre Count</th>';	
				  		
			for(var i in result[0].allDetailsList){			
				str+='<th colspan="2">'+result[0].allDetailsList[i].name+'</th>';			
			}
			str+='</tr>';
			str+='<tr>';
			for(var i in result[0].allDetailsList){						
				str+='<th> Voters Count</th>';
				str+='<th> Cadre Count</th>';					
			}
			 str+='</tr>';	 
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
			str+='<tr >';
			if(type == 1)
				str+='<td>'+result[i].name+'</a></td>';
			if(type == 2){
				str+='<td>'+result[i].district+'</td>';	
				str+='<td>'+result[i].parliament+'</td>';
				str+='<td>'+result[i].name+'</td>';
			}
			else if(type == 3)
			str+='<td>'+result[i].name+'</th>';
			else if(type == 4)
			str+='<td>'+result[i].name+'</th>';
			else if(type == 5)
			str+='<td>Part-'+result[i].name+'</th>';
			
			str+='<td>'+result[i].votersCount+'</td>';
			str+='<td>'+result[i].registeredCount+'</td>';	
			if(result[i].number != null){
			str+='<td>'+result[i].femaleVotersCount+'('+result[i].number+'%)</td>';
			}
			else{
				str+='<td>'+result[i].femaleVotersCount+'(0%)</td>';
			}
			if(result[i].percentStr != null){
				str+='<td>'+result[i].femaleCadreCount+'('+result[i].percentStr+'%)</td>';
			}
			else{
				str+='<td>'+result[i].femaleCadreCount+'(0%)</td>';
			}
			if(result[i].uname != null){
				str+='<td>'+result[i].maleVotersCount+'('+result[i].uname+'%)</td>';
			}
			else{
				str+='<td>'+result[i].maleVotersCount+'(0%)</td>';
			}
			if(result[i].status != null){
			str+='<td>'+result[i].maleCadreCount+'('+result[i].status+'%)</td>';
			}
			else{
				str+='<td>'+result[i].maleCadreCount+'(0%)</td>';
			}
			for(var j in result[i].allDetailsList){
			if(result[i].number != null){
				str+='<td>'+result[i].allDetailsList[j].ageRangeVoterValues+'('+result[i].number+'%)</td>';
			}
			else{
				str+='<td>'+result[i].allDetailsList[j].ageRangeVoterValues+'(0%)</td>';
			}
			if(result[i].percentStr != null){
				str+='<td>'+result[i].allDetailsList[j].ageRangeCadreValues+'('+result[i].percentStr+'%)</td>';
				
			}
			else{
				str+='<td>'+result[i].allDetailsList[j].ageRangeCadreValues+'(0%)</td>';
			}
			}
			str+='</tr>';			
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#resultDiv").html(str);
		$("#tabledata").dataTable({
			         "iDisplayLength": 20,
			          "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			     });
		
	}

   </script>
</body>
</html>