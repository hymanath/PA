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
	
.questSummaryTab th {
    border-bottom: 1px dashed #69c;
    color: #039;
    font-size: 14px;
    font-weight: normal;
    padding: 12px 12px;
}
.questSummaryTab td {
    color: #669;
    padding: 7px 17px;
}

.questSummaryTab {
    border: 1px solid #69c;
    border-collapse: collapse;
    font-family: "Lucida Sans Unicode","Lucida Grande",Sans-Serif;
    font-size: 12px;
    margin: 20px;
    text-align: left;
    width: 90%;
}

.questSummaryTab tbody tr:hover td {
    background: none repeat scroll 0 0 #d0dafd;
    color: #339;
}
</style>

	<div id="mainDivId" class="container" style="padding:10px;">
	<h4 class="offset4" style="color:red">HOUSEHOLDS REPORT</h4>
	<div class='offset3' style="margin-top:15px;">SELECT CONSTITUENCY  <s:select theme="simple" style="width: 169px;"
				 name="constituencyList" 
				id="constituencyId" list="hhConstituenies" 
				listKey="id" listValue="name" onChange="getSummary();getQuestions();"/>
	</div>
	
	<div class='offset3' style="margin-top:15px;display:none;" id="questionDivId">SELECT QUESTION  <select id="questionId" style="margin-left:24px;"><option val="0">Select</option></select></div>
	
	<div class='offset3' style="margin-top:15px;display:none;" id="leaderDivId">SELECT LEADER  <select id="leaderId" style="margin-left:36px;width:300px;" onchange="getBooksOfLeader()"><option val="0">Select</option></select></div> 
	
	<div class='offset3' style="margin-top:15px;display:none;" id="bookDivId">SELECT BOOK  <select id="bookId" style="margin-left:48px;" onclick="getHouseHoldsOfBook()"><option val="0">Select</option></select></div>
	
	
	<div class="offset3" style="margin-top:10px;">
		<span class="btn btn-error" onclick="getSummary()">Constituency Summary</span>
		<span class="btn btn-error" onclick="getQuestionSummary()">Question Wise Summary</span>
		<span class="btn btn-error" onclick="getBooksSummary()">Get Books Summary</span>
	</div>
	
	
	<div id="constiSummaryDiv" style="margin:20px;"></div>
	<div id="constiNonVotersSummaryDiv" style="margin:20px;"></div>
	<div id="consolidateReport" style="margin:20px;"></div>
	<div id="panchayatSummaryDivId" style="margin:20px;"></div>
	
	<div id="summariesId" style="margin:20px;"></div>
	<div id="summariesId1" style="margin:20px;"></div>
	<div id="summariesId2" style="margin:20px;"></div>
	
	<div id="question" class='offset4'></div>
	<div id="questSummary" style="margin:20px;"></div>
	<div id="questSummary1" style="margin:20px;"></div>
	<div id="questSummary2" style="margin:20px;"></div>
	<div id="questSummary3" style="margin:20px;"></div>
	<div id="questionSummary" style="margin:20px;"></div>
	
	
	</div>
	
	
	<script>
	
	function getBooksSummary(){
		$("#panchayatSummaryDivId,#summariesId,#summariesId1,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3,#consolidateReport").html("");
		$("#questSummary,#questSummary1,#question,#questSummary2").html("");
		$("#leaderDivId,#bookDivId").css("display","block");
		$("#questionDivId").css("display","none");
		getLeadersOfConstituency();
		getBooksOfLeader();
	}
	
	function getLeadersOfConstituency(){

	var consti = $("#constituencyId").val();
	
	var constiDtls={
             constituency:consti,
			 publication:0,
			 task:"leadersOfConstituency"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(constiDtls)},
	}).done(function(result){
		if(result!=null && result.leadersOfConsti!=null && result.leadersOfConsti.length>0){
		$("#leaderId option").remove();
		if(result.leadersOfConsti !=null){
			for(var i in result.leadersOfConsti){
				$('#leaderId').append('<option value='+result.leadersOfConsti[i].leaderId+'>'+result.leadersOfConsti[i].voterId+' - '+result.leadersOfConsti[i].leaderName +'</option>');         
			}
		}
		getBooksOfLeader();
		}
	
	});
	}
	
	function getBooksOfLeader(){
		var constnDtls={
             leader:$('#leaderId').val(),
			 task:"getBooksOfLeader"
		};
	
		$.ajax({
			type:"POST",
			url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
			data :{task:JSON.stringify(constnDtls)},
		}).done(function(result){
			if(result.booksInConstituency!=null && result.booksInConstituency.length>0){
			  $('#bookId').find('option').remove();
			  if(result.booksInConstituency !=null){
				 $.each(result.booksInConstituency,function(index,value){
				 $('#bookId').append('<option value="'+value.bookId+'">'+value.bookNo+'</option>');
			  });
			  }
			  
			  getHouseHoldsOfBook();
			  }else{
				$("#summariesId2").html("");
				 $('#bookId').find('option').remove();
				 $('#bookId').append('<option value="0"> Select </option>');
			  }
			  
		});
	}
	
	function getHouseHoldsOfBook(){
			var bookId = $("#bookId").val();
		var constnDtls={
             bookId:bookId,
			 task:"familyHeadsUnderBook"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildHouseHoldsUnderBook(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
	}
		
function getQuestions(){

	var inputDtls={
             surveyId:1
	};
	$.ajax({
          type:'POST',
          url: 'getQuestionsForHHOfSurveyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(inputDtls)},

          success: function(result){ 
			buildQuestions(result);		  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}	

function buildQuestions(result){
	if(result!=null && result.length){
		var str ="";
		$("#questionDivId").html("");
		str +="SELECT QUESTION ";
		str +="<select id='questionId' onchange='getQuestionSummary()' style='margin-left:24px;'>";
			for(var i in result){
				str +="<option value="+result[i].questionId+">"+result[i].question+"</option>";
			}
		str +="</select>";
		
		$("#questionDivId").html(str);
		
		//getQuestionSummary();
	}
	
}	

function getQuestionSummary(){
	
	$("#questionDivId").css('display','block');
	$("#leaderDivId,#bookDivId").css('display','none');
	clearSummaryDivs();

	var constituencyId = $("#constituencyId").val();
	var questionId = $("#questionId").val();
	
	var constnDtls={
             constituencyId:constituencyId,
			 questionId:questionId
	};
	$.ajax({
          type:'POST',
          url: 'getQuestionsSummaryForHHOfSurveyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildQuestionSummary(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function clearSummaryDivs(){
	$("#constiSummaryDiv,#panchayatSummaryDivId,#summariesId,#summariesId1,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3,#consolidateReport").html("");
}

function buildQuestionSummary(result){
	$("#questSummary,#questSummary1,#questSummary2,#questSummary3").html("");
	
	$("#question").html("<h3>"+result.question+"</h3>");
	var str = "";
	str+="<table class='questSummaryTab'>";
		str +="<thead>";
			str +="<tr>";
					
				for(var i in result.optionsList){
					str +="<th>"+result.optionsList[i].option+"</th>";
				}
				
			str +="</tr>";
		str +="</thead>";
		
		str +="<tbody>";
			str +="<tr>";
				for(var i in result.optionsList){
					str +="<td>"+result.optionsList[i].optsCount+"</td>";
				}
				
			str +="</tr>";
		str +="</tbody>";
	str+="</table>";
	$("#questSummary").html(str);
	
	var str1 = "";
	str1 +="<h4 style='color:red'> PANCHAYAT WISE SUMMARY</h4>";
	str1+="<table class='table table-bordered questSummaryTable1'>";
		str1 +="<thead>";
			str1 +="<tr>";
				str1 += "<th> PANCHAYAT </th>";
				for(var i in result.panchayatList[0].optionsList){
					str1 +="<th>"+result.panchayatList[0].optionsList[i].option+"</th>";
				}
				
			str1 +="</tr>";
		str1 +="</thead>";
		
		str1 +="<tbody>";
			
				for(var i in result.panchayatList){
					str1 +="<tr>";
						str1 +="<td>"+result.panchayatList[i].panchayat+"</td>";
							
							for(var j in result.panchayatList[i].optionsList){
								if(result.panchayatList[i].optionsList[j].optsCount == null){
									str1 +="<td> - </td>";	
								}else{
									str1 +="<td onclick='getFamilies("+result.panchayatList[i].optionsList[j].optionId+","+result.panchayatList[i].panchayatId+")'>"+result.panchayatList[i].optionsList[j].optsCount+"</td>";
								}
							}
					str1 +="</tr>";	
				}
				
			
		str1 +="</tbody>";
	str1+="</table>";
	$("#questSummary1").html(str1);
	$('.questSummaryTable1').dataTable();
}

function getFamilies(optionId,panchayatId){
	//alert(optionId +" -- "+panchayatId);
	var constnDtls={
			 optionId:optionId,
             panchayatId:panchayatId,
			 task:"familyHeadsUnderOptions"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			buildHouseHoldsUnderOptions(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getSummary(){

	$("#questSummary,#questSummary1,#questSummary2,#question,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3").html("");
	$("#questionDivId,#leaderDivId,#bookDivId").css('display','none');
	
	
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
					str1 +="<th onClick='getNonVotersAgeRangeWiseCount("+result.constituencyId+")' style='cursor:pointer;'>"+result.constiNonVotersCount+"</th>";
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
	getBooksOfHouseHold();
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

function buildHouseHoldsUnderBook(result){

	$("#summariesId1,#summariesId2").html("");
	var bookNo = $("#bookId :selected").text();
	
	if(result!=null && result.familyHeadsUnderBook!=null && result.familyHeadsUnderBook.length>0){
		var str = "";
		
		str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> HOUSEHOLDS UNDER BOOK "+bookNo+"</h4>";
		
		str+= "<table class='table table-bordered summeriesTable2'>";
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
				for(var i in result.familyHeadsUnderBook){
					str +="<tr>";
						str +="<td>"+result.familyHeadsUnderBook[i].voterName+"</td>";
						str +="<td>"+result.familyHeadsUnderBook[i].voterCardNo+"</td>";
						str +="<td>"+result.familyHeadsUnderBook[i].houseNo+"</td>";
						str +="<td>"+result.familyHeadsUnderBook[i].votersCount+"</td>";
						str +="<td>"+result.familyHeadsUnderBook[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		
		$("#summariesId2").html(str);
		
		$('.summeriesTable2').dataTable();
	}else{
		$("#summariesId2").html("<span class='offset4' style='color:red;font-weight:bold;'> NO DATA FOUND </span>");
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
						str +="<td onclick='getFamilyMembers(\""+result.leadersOfPnchyt[i].voterName+"\","+result.leadersOfPnchyt[i].houseHoldId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].voterName+"</td>";
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

function buildHouseHoldsUnderOptions(result){
	
	if(result.familyHeadsUnderOption!=null && result.familyHeadsUnderOption.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> HOUSEHOLDS UNDER "+result.panchayatName+" PANCHAYAT WITH OPTION "+result.option+"</h4>";
		
		str+= "<table class='table table-bordered questSummaryTbl'>";
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
				for(var i in result.familyHeadsUnderOption){
					str +="<tr>";
						str +="<td>"+result.familyHeadsUnderOption[i].voterName+"</td>";
						str +="<td>"+result.familyHeadsUnderOption[i].voterCardNo+"</td>";
						str +="<td>"+result.familyHeadsUnderOption[i].houseNo+"</td>";
						str +="<td>"+result.familyHeadsUnderOption[i].votersCount+"</td>";
						str +="<td>"+result.familyHeadsUnderOption[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#questSummary2").html(str);
		
		$('.questSummaryTbl').dataTable();
	}
}

function getFamilyMembers(name,householdId){

	var details={
             householdId:householdId,
			 task:"familyMembersUnderHead"
	};
	$.ajax({
          type:'POST',
          url: 'familyMembersUnderFamilyHead.action',
          dataType: 'json',
          data: {task:JSON.stringify(details)},

          success: function(result){ 
			buildHouseHoldsUnderFamilyHead(result,name);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function buildHouseHoldsUnderFamilyHead(result,name){
	
	if(result!=null && result.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'>FAMILY MEMBERS UNDER "+name.toUpperCase()+"</h4>"; 
		
		str+= "<table class='table table-bordered questSummaryTbl'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>FAMILY MEMBER</th>";	
					str +="<th>VOTER ID</th>";	
					str +="<th>AGE</th>";	
					str +="<th>RELATIVE NAME</th>";	
					str +="<th>RELATION TYPE</th>";	
					
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result){
					str +="<tr>";
						str +="<td>"+result[i].name+"</td>";
						str +="<td>"+result[i].voterIDCardNo+"</td>";
						str +="<td>"+result[i].age+"</td>";
						str +="<td>"+result[i].relativeName+"</td>";
						str +="<td>"+result[i].relation+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#questSummary3").html(str);
		
		$('.questSummaryTbl').dataTable();
	}
}

function getNonVotersAgeRangeWiseCount(constiId){
	var details={
             constituencyId:constiId,
			 task:"NonVotersAgeRangeCount"
	};
	$.ajax({
          type:'POST',
          url: 'getNonVoterAgeRangeDetailsConstituencyWiseAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(details)},

          success: function(result){ 
			buildNonVotersAgeRangeWiseDetails(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildNonVotersAgeRangeWiseDetails(result){
	$("#panchayatSummaryDivId").html("");
	$("#consolidateReport").html("");
	if(result!=null && result.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'></h4>";
		
		str+= "<table class='table table-bordered questSummaryTbl'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>PANCHAYAT NAME</th>";	
					for(var i in result[0].ageRangesList){
					str +="<th>"+result[0].ageRangesList[i].ageRange+"</th>";
					}
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result){
					str +="<tr>";
						str +="<td>"+result[i].panchayatName+"</td>";
						for(var j in result[i].ageRangesList){
							str +="<td>"+result[i].ageRangesList[j].ageRangeCount+"</td>";
						}
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#constiNonVotersSummaryDiv").html(str);
		
		$('.questSummaryTbl').dataTable();
	}
}
function getBooksOfHouseHold(){
	var details={
             constituencyId:$("#constituencyId").val(),
			 task:"bookDetails"
	};
	$.ajax({
          type:'POST',
          url: 'getBooksDetailsOfHouseHoldsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(details)},

          success: function(result){ 
			buildBookDetailsOfHouseHolds(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildBookDetailsOfHouseHolds(result)
{
if(result!=null && result.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'>CONSOLIDATE REPORT</h4>";
		
		str+= "<table class='table table-bordered questSummaryTbl'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>MANDAL NAME</th>";	
					str +="<th>PANCHAYAT NAME</th>";	
					str +="<th>BOOK NO</th>";	
					str +="<th>LEADER NAME</th>";
					str +="<th>VOTER ID CARD NO</th>";
					str +="<th>FAMILIES COUNT</th>";						
					str +="<th>VOTERS COUNT</th>";	
					str +="<th>NON VOTERS COUNT</th>";	
					
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result){
					str +="<tr>";
						str +="<td>"+result[i].tehsilName+"</td>";
						str +="<td>"+result[i].panchayatName+"</td>";
						str +="<td>"+result[i].bookNo+"</td>";
						str +="<td>"+result[i].leaderName+"</td>";
						str +="<td>"+result[i].voterId+"</td>";
						str +="<td>"+result[i].familiesCount+"</td>";
						str +="<td>"+result[i].votersCount+"</td>";
						str +="<td>"+result[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#consolidateReport").html(str);
		
		$('.questSummaryTbl').dataTable();
	}
}

</script>
</body>
</html>