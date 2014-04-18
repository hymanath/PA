<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>

<html>
<head>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<title>Infected Booths</title> 

<style>
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 250px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}
input, button, select, textarea {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}
	.parliamentDiv,.assemblyDiv{margin:10px;}

	.infectedPanchaytTable {
	font-family: arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	text-align:center !important;
}
.infectedPanchaytTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #DBEBFF;
}
.infectedPanchaytTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

</style>
</head>
<body>
 <div class="container m_top20">
    
	<div class="row well " style="margin-top: 15px;" align="center">
	<h3 style="color:#4A66B5;"> Infected Booths </h3>
	
	 <div>
		<div class="parliamentDiv">
			<span style="font-weight:bold;font-size:15px;">Select Parliamet :</span> <s:select theme="simple" name="parliament" id="pConstituencyList" list="parlConstis" listKey="id" listValue="name" onChange="getAssemblyConstituencies()"/>
		</div>
		<div class="assemblyDiv">
			<span style="font-weight:bold;font-size:15px;">Select Assembly :</span> <s:select theme="simple" name="assembly" id="assemblyId" list="constituencyList" listKey="id" listValue="name"/>
		</div>
		
		
	</div>
	<div align="center">
		<button class="btn btn-info infectedBoothsBtn"> Get Infected Booths</button>
		<span><img id="publicationAjaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none; margin-top: -20px; margin-left: 200px;"/></span>
	</div>
	</div>
	</div>
	
	<div id="infectedBoothsId" class=""></div>
	
	<script>
	
		$(".infectedBoothsBtn").click(function(){
		  document.getElementById("publicationAjaxImage").style.display="block";
		   
	       $("#infectedBoothsId").html("");
			var constituencyDetails={constituencyId:""};
			constituencyDetails.constituencyId=$('#assemblyId option:selected').val();
			$.ajax({
				type:'POST',
				url: 'getInfectedBoothsForConstituency.action',
				dataType: 'json',
				data: {task:JSON.stringify(constituencyDetails)},			 
				success: function(data){  
				//console.log('anilaaa')
				   document.getElementById("publicationAjaxImage").style.display="none";
					buildInfectedBoothsTable(data);
				},
				error:function(jqXHR, textStatus, errorThrown) { 
				   document.getElementById("publicationAjaxImage").style.display="none";
					//console.log(textStatus, errorThrown);
				}
			});
		});
		
		function buildInfectedBoothsTable(result){
	
		if(result!=null){
			$("#infectedBoothsId").html("");
			var str ="";
			
			str+="<div class='container m_top20'>";    
	        str+="<div class='row well ' style='margin-top: 15px;' >";
			
			str+="<legend class='boxHeading m_top10 text-center'  style='color:#4A66B5;' >Infected Booths</legend>";
			str+= "<table class='table-bordered infectedPanchaytTable' style='font-family: vardana;font-size:13px;' width='100%'>";
			str+="<thead>";
				str+="<th>Panchayat</th>";
				str+="<th>Polling Stations</th>";
				//str+="<th>Effected Count</th>";
			str+="</thead>";
			str+="<tbody>";
			var flag=false;
			var sortArr = new Array();
		
			for(var i in result.panchayats){
			flag=true;
			sortArr = [];
					if(result.panchayats[i]){						
						sortArr = result.panchayats[i].booths;
					}
					sortArr.sort(sortAscending); 
					$.unique(sortArr);
					sortArr.sort(sortAscending);
					
					    str+="<tr align='center'>";
						str+="<td width='25%'>"+result.panchayats[i].panchayatName+"</td>";
						str+="<td width='25%'>"+sortArr+"</td>";
						str+="</tr>";	
						
				}
				
				/*var flag=false;
				for(var i in result.panchayats){
			
					if(result.panchayats[i].effected){
					flag=true;
					var booths ="";
					str+="<tr align='center'>";
						str+="<td width='25%'>"+result.panchayats[i].panchayatName+"</td>";
						str+="<td width='40%' >"+result.panchayats[i].booths.toString()+"</td>";
						str+="<td width='15%' >"+result.panchayats[i].effectedCount+"</td>";
					str+="</tr>";
					}
				}*/
				
			str+="</tbody>";
				str+="</table>";
			str+="</div>";    
	        str+="</div>";
			
			$("#infectedBoothsId").html(str);
			}else{
			$("#infectedBoothsId").html("<br/><legend>No Panchayats Available</legend>");
			}
			window.open(result.url);
		}
		
		function sortAscending(data_A, data_B)
		{
			return (data_A - data_B);
		}
		
		function getAssemblyConstituencies(){
			
			var constituencyDetails={year:"",constituencyId:"",task:""};
		
			constituencyDetails.constituencyId= $('#pConstituencyList option:selected').val();
			constituencyDetails.year = 2009;
			$.ajax({
				type:'POST',
				url: 'getConstituenciesForInfectedBooths.action',
				dataType: 'json',
				data: {task:JSON.stringify(constituencyDetails)},			 
				success: function(data){  
					buildAssemblies(data);
				},
				error:function() { 
				}
			});
		}
		
		function buildAssemblies(results){
			$("#assemblyId").html("");
			
			var str="";
			
			str+="<select>";
				str+="<option value=0> Select </option>";
			for(var i in results){
				str+="<option value="+results[i].id+">"+results[i].name+"</option>";
			}
			str+="</select>";
			
			$("#assemblyId").html(str);
		}
		

	</script>
</body>
</html>