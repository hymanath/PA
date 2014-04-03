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
	.parliamentDiv,.assemblyDiv{margin:10px;}
	.infectedPanchaytTable th{font-weight:bold;font-size:14px;}
	
</style>
</head>
<body>
	<h3 class="offset4">Infected Booths</h3>
	
	<div class="row span6 offset3" style="background:#f3f3f3">
		<div class="span4 parliamentDiv">
			<span style="font-weight:bold;font-size:15px;">Select Parliamet :</span> <s:select theme="simple" name="parliament" id="pConstituencyList" list="parlConstis" listKey="id" listValue="name" onChange="getAssemblyConstituencies()"/>
		</div>
		<div class="span4 assemblyDiv">
			<span style="font-weight:bold;font-size:15px;">Select Assembly :</span> <s:select theme="simple" name="assembly" id="assemblyId" list="constituencyList" listKey="id" listValue="name"/>
		</div>
		
		<span class="btn btn-info infectedBoothsBtn"> Get Infected Booths</span>
	</div>
	<div id="infectedBoothsId" class="span12 offset2"></div>
	
	<script>
	
		$(".infectedBoothsBtn").click(function(){
	$("#infectedBoothsId").html("");
			var constituencyDetails={constituencyId:""};
			constituencyDetails.constituencyId=$('#assemblyId option:selected').val();
			$.ajax({
				type:'POST',
				url: 'getInfectedBoothsForConstituency.action',
				dataType: 'json',
				data: {task:JSON.stringify(constituencyDetails)},			 
				success: function(data){  
				console.log('anilaaa')
					buildInfectedBoothsTable(data);
				},
				error:function(jqXHR, textStatus, errorThrown) { 
					console.log(textStatus, errorThrown);
				}
			});
		});
		
		function buildInfectedBoothsTable(result){
	
		
		if(result!=null){
			$("#infectedBoothsId").html("");
			var str ="";
			str+="<h4 class='offset4'> Infected Panchayats List </h4>";
			str+= "<table class='table table-bordered infectedPanchaytTable'>";
			str+="<thead>";
				str+="<th>Panchayat</th>";
				str+="<th>Booths</th>";
				str+="<th>Effected Count</th>";
			str+="</thead>";
			str+="<tbody>";
			var flag=false;
				for(var i in result.panchayats){
			
					if(result.panchayats[i].effected){
					flag=true;
					var booths ="";
					str+="<tr>";
						str+="<th>"+result.panchayats[i].panchayatName+"</th>";
						str+="<th>"+result.panchayats[i].booths.toString()+"</th>";
						str+="<th>"+result.panchayats[i].effectedCount+"</th>";
					str+="</tr>";
					}
				}
			str+="</tbody>";
			
			$("#infectedBoothsId").html(str);
			}else{
			$("#infectedBoothsId").html("<br/><legend>No Panchayats Available</legend>");
			}
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