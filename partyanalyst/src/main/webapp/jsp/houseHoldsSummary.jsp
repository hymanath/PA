<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

</head>
<body>

<style>
	table.dataTable thead th{font-size:14px;}
	table.dataTable td{font-size:12px;font-weight:bold;}
</style>

	<div id="mainDivId" class="container well">
	<h4 class="offset4" style="color:red">HOUSEHOLDS REPORT</h4>
	<div class='offset3 span4' style="margin-top:15px;">SELECT CONSTITUENCY : <s:select theme="simple" style="width: 169px;"
				 name="constituencyList" 
				id="constituencyId" list="hhConstituenies" 
				listKey="id" listValue="name" onChange="getSummary()"/>
	</div>
		<div id="constiSummaryDiv" style="margin:20px;"></div>
		<div id="panchayatSummaryDivId" style="margin:20px;"></div>
		
		<div id="summariesId" style="margin:20px;"></div>
		<div id="summariesId1" style="margin:20px;"></div>
	</div>
	
	
	<script>
		
function getSummary(){
	var constituencyId = $("#constituencyId").val();
	var constnDtls={
             constituencyId:constituencyId,
			 task:"constituencySummary"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildPanchayatSummary(result);		  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getHouseHoldsUnderPanchayat(panchayatId){
	var constnDtls={
             panchayatId:panchayatId,
			 task:"familyHeadsUnderPanchayat"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildHouseHolds(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getLeadersUnderPanchayat(panchayatId){
	var constnDtls={
             panchayatId:panchayatId,
			 task:"leaderOfPanchayat"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildLeadersOfPanchayat(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getHouseHoldsUnderLeader(leaderId){
	
	var constnDtls={
             leaderId:leaderId,
			 task:"familyHeadsUnderLeader"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildHouseHolds(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildPanchayatSummary(result){
	$("#panchayatSummaryDivId").html("");
	$("#constiSummaryDiv").html("");
	
	var str1 = "";
		
		str1 +="<table class='table'>";
			str1 +="<tr>";
				str1 +="<th>CONSTITUENCY</th>";
				str1 +="<th>HOUSE HOLDS</th>";
				str1 +="<th>LEADERS</th>";
				str1 +="<th>VOTERS COVERED</th>";
				str1 +="<th>NON-VOTERS</th>";
			str1 +="</tr>";
			
				str1 +="<tr>";
					str1 +="<th>"+result.constituency+"</th>";
					str1 +="<th>"+result.constiHouseHoldsCount+"</th>";
					str1 +="<th>"+result.activeLeadersCount+"</th>";
					str1 +="<th>"+result.constiVotersCount+"</th>";
					str1 +="<th>"+result.constiNonVotersCount+"</th>";
				str1 +="</tr>";
			
		str1 +="</table>";	
	$("#constiSummaryDiv").html(str1);
	
	if(result.panchayatList!=null && result.panchayatList.length>0){
		var str = "";
		str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> PANCHAYAT WISE SUMMARY OF "+result.constituency+" CONSTITUENCY </h4>";
		str+= "<table class='table table-bordered summaryDiv'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>PANCHAYAT NAME</th>";	
					str +="<th>HOUSE HOLDS</th>";	
					str +="<th>LEADERS</th>";	
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result.panchayatList){
					str +="<tr>";
						str +="<td>"+result.panchayatList[i].panchayatName+"</td>";
						str +="<td title='Click To See HouseHolds In this Panchayat' onclick='getHouseHoldsUnderPanchayat("+result.panchayatList[i].panchayatId+") ' style='cursor:pointer;'>"+result.panchayatList[i].houseHoldsCount+"</td>";
						str +="<td title='Click To See Leaders In this Panchayat' onclick='getLeadersUnderPanchayat("+result.panchayatList[i].panchayatId+") ' style='cursor:pointer;'>"+result.panchayatList[i].leadersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		
		
		}
		
	
	
	$("#panchayatSummaryDivId").html(str);
	
	$('.summaryDiv').dataTable();
}

function buildLeadersOfPanchayat(result){
	$("#summariesId").html("");
	$("#summariesId1").html("");
	
	var str = "";
	if(result.leadersOfPnchyt!=null && result.leadersOfPnchyt.length>0){
		str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> LEADERS IN "+result.panchayatName+" PANCHAYAT</h4>";
		
		str+= "<table class='table table-bordered summeriesTable'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>LEADER NAME</th>";	
					str +="<th>VOTER ID</th>";	
					str +="<th>MOBILE NUMBER</th>";	
					str +="<th>HOUSE HOLDS</th>";	
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result.leadersOfPnchyt){
					str +="<tr>";
						str +="<td title='Click To See HouseHolds Under This Leader' onclick='getHouseHoldsUnderLeader("+result.leadersOfPnchyt[i].leaderId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].voterName+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].voterCardNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].mobileNo+"</td>";
						str +="<td title='Click To See HouseHolds Under This Leader' onclick='getHouseHoldsUnderLeader("+result.leadersOfPnchyt[i].leaderId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].houseHoldsCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#summariesId").html(str);
		
		$('.summeriesTable').dataTable();
	}
}

function buildHouseHolds(result){
	$("#summariesId1").html("");
	if(result.panchayatName!=null){
		$("#summariesId").html("");
	}	
	
	if(result.leadersOfPnchyt!=null && result.leadersOfPnchyt.length>0){
		var str = "";
		if(result.panchayatName!=null){
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> HOUSEHOLDS UNDER "+result.panchayatName+" PANCHAYAT</h4>";
		}else{
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> HOUSEHOLDS UNDER "+result.leaderName+"</h4>"
		}
		str+= "<table class='table table-bordered summeriesTable1'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>FAMILY HEAD</th>";	
					str +="<th>VOTER ID</th>";	
					str +="<th>HOUSE NO</th>";	
					str +="<th>VOTERS COUNT</th>";	
					str +="<th>NON-VOTERS COUNT</th>";	
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result.leadersOfPnchyt){
					str +="<tr>";
						str +="<td>"+result.leadersOfPnchyt[i].voterName+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].voterCardNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].houseNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].votersCount+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#summariesId1").html(str);
		
		$('.summeriesTable1').dataTable();
	}
}
	</script>
</body>
</html>