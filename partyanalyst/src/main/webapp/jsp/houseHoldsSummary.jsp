<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
	<div id ="errorDiv" class="offset3" style="color:red"></div>
	<div class='offset3' style="margin-top:15px;">SELECT CONSTITUENCY <font style="margin-left:5px;color:#ff0020;">*</font> <s:select theme="simple" style="width: 169px;"
				 name="constituencyList" 
				id="constituencyId" list="hhConstituenies" 
				listKey="id" listValue="name" onChange="getQuestions(),getLeadersOfConstituency();"/>
	</div>
	
	<div class='offset3' style="margin-top:15px;display:none;" id="questionDivId">SELECT QUESTION <font style="color:#ff0020;">*</font> <select id="questionId" style="margin-left:33px;"><option val="0">Select</option></select></div>
	
	<div class='offset3' style="margin-top:15px;display:none;" id="leaderDivId">SELECT LEADER <font style="margin-left:5px;color:#ff0020;">*</font> <select id="leaderId" style="margin-left:36px;width:300px;" onchange="getBooksOfLeader()"><option val="0">Select</option></select></div> 
	
	<div class='offset3' style="margin-top:15px;display:none;" id="bookDivId">SELECT BOOK <font style="margin-left:5px;color:#ff0020;">*</font> <select id="bookId" style="margin-left:48px;" onclick=""><option val="0">Select</option></select></div>
	
	
	<div class="offset3" style="margin-top:10px;">
		<span class="btn btn-error" onclick="getSummary();">Constituency Summary</span>
		<span class="btn btn-error" onclick="getQuestionSummary()">Question Wise Summary</span>
		<span class="btn btn-error" onclick="getHouseHoldsOfBook()">Get Books Summary</span>
	</div>
	
	
	<div id="constiSummaryDiv" style="margin:20px;"></div>
	<div id="constiNonVotersSummaryDiv" style="margin:20px;"></div>
	<div id="ageRangeNonVotersSummaryDiv" style="margin:20px;"></div>
	<div id="consolidateReport" style="margin:20px;"></div>
	<div id="booksWiseSummaryDiv" style="margin:20px;overflow-x:scroll;"></div>
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
	
	<div class="offset5"><img id="ajaxImg" src="./images/Loading-data.gif" alt="Processing Image" style='height:65px;width:75px;display:none;' /></div>
	
	
	</div>
	
	
	<script>
	
	function getBooksSummary(){
		$("#panchayatSummaryDivId,#summariesId,#summariesId1,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3,#consolidateReport,#ageRangeNonVotersSummaryDiv,#constiSummaryDiv").html("");
		$("#questSummary,#questSummary1,#question,#questSummary2,#booksWiseSummaryDiv").html("");
		$("#leaderDivId,#bookDivId").css("display","block");
		$("#questionDivId").css("display","none");
		getLeadersOfConstituency();
	//	getBooksOfLeader();
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
			  
			  //getHouseHoldsOfBook();
			  }else{
				$("#summariesId2").html("");
				 $('#bookId').find('option').remove();
				 $('#bookId').append('<option value="0"> Select </option>');
			  }
			  
		});
	}
	
	function getHouseHoldsOfBook(){
		$("#leaderDivId,#bookDivId").css("display","block");
		$("#questionDivId").css("display","none");
		clearSummaryDivs();
	var bookId = $("#bookId").val();
	if($("#constituencyId").val() == 0)
	{
		$("#errorDiv").html("Please Select Constituency");
		return;
	}
	$("#errorDiv").html("");
	$("#ajaxImg").show();
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
		str +="<select id='questionId' onchange='' style='margin-left:33px;'>";
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
	if( constituencyId == 0)
	{
		$("#errorDiv").html("Please Select Constituency");
		return;
	}

	$("#errorDiv").html("");
	$("#ajaxImg").show();
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
	$("#constiSummaryDiv,#panchayatSummaryDivId,#summariesId,#summariesId1,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3,#consolidateReport,#ageRangeNonVotersSummaryDiv,#booksWiseSummaryDiv").html("");
	$("#questSummary,#questSummary1,#questSummary2,#question").html("");
	
}

function buildQuestionSummary(result){
	$("#questSummary,#questSummary1,#questSummary2,#questSummary3").html("");
	$("#ajaxImg").hide();
	$("#question").html("<h3>"+result.optionsList[0].question+"</h3>");
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
									str1 +="<td title='Click To See HouseHolds with option " + result.panchayatList[i].optionsList[j].option+"' onclick='getFamilies("+result.panchayatList[i].optionsList[j].optionId+","+result.panchayatList[i].panchayatId+")' style='cursor:pointer;'>"+result.panchayatList[i].optionsList[j].optsCount+"</td>";
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
	$("#questSummary2").html("");
	$("#ajaxImg").show();
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

	clearSummaryDivs();
	$("#questionDivId,#leaderDivId,#bookDivId").css('display','none');
	
	
	var constituencyId = $("#constituencyId").val();
	if( constituencyId == 0)
	{
		$("#errorDiv").html("Please Select Constituency");
		return;
	}
	$("#errorDiv").html("");
	$("#ajaxImg").show();
	var constnDtls={
             constituencyId:constituencyId,
			 task:"constituencySummary"
	};
	$.ajax({
          type:'POST',
          url: 'getPanchayatWiseDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){
			buildPanchayatSummaryReport(result);		  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function getBooksWiseSummary()
{
	$("#questSummary,#questSummary1,#questSummary2,#question,#summariesId2,#constiNonVotersSummaryDiv,#questSummary3,#ageRangeNonVotersSummaryDiv").html("");
	$("#questionDivId,#leaderDivId,#bookDivId").css('display','none');	
	$("#booksWiseSummaryDiv").html("");
	var constituencyId = $("#constituencyId option:selected").val();
	if(constituencyId==0)
	{
	 return;
	}
	$.ajax({
          type:'POST',
          url: 'getBooksWiseSummaryAction.action',
          dataType: 'json',
          data: {constituencyId:constituencyId},

          success: function(result){
		     
        	 buildBooksWiseSummary(result,$("#constituencyId option:selected").text());		  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
	
	
	
}

function getHouseHoldsUnderPanchayat(panchayatId){
	$("#summariesId1,#questSummary3,#summariesId").html("");
	$("#ajaxImg").show();
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
	$("#summariesId").html("");
	$("#summariesId1,#questSummary3").html("");
	$("#ajaxImg").show();
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
			$("#ajaxImg").hide();
			buildLeadersOfPanchayat(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getHouseHoldsUnderLeader(leaderId,panchayatId){
	$("#summariesId1").html("");
	$("#ajaxImg").show();
	var constnDtls={
             leaderId:leaderId,
			 panchayatId : panchayatId,
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
					str1 +="<th title='Click To See Non Voters Details' onClick='getNonVotersAgeRangeWiseCount("+result.constituencyId+")' style='cursor:pointer;'>"+result.constiNonVotersCount+"</th>";
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
	//getBooksOfHouseHold();
	//getBooksWiseSummary();
}
function buildBooksWiseSummary(result,constituency)
{   
	$("#booksWiseSummaryDiv").html("");
	var str='';
	str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> BOOKS WISE SUMMARY OF "+constituency+" CONSTITUENCY </h4>";
	str+= "<table class='table table-bordered summaryDivv'>";
		str +="<thead>";
			str +="<tr>";
				str +="<th>BookNo</th>";	
				str +="<th>MandalName</th>";	
				str +="<th>PanchayatName</th>";	
				str+="<th>Name</th>";
				str+="<th>VoterId</th>";
				str+="<th>VotersCount</th>";
				str+="<th>NonVotersCount</th>";
				str+="<th>FamiliesCount</th>";
				str +="</tr>";
		str +="</thead>";
		
		str +="<tbody>";
			for(var i in result)
			{
				str +="<tr>";
				str +="<td>"+result[i].bookNo+"</td>";
				str +="<td>"+result[i].message+"</td>";
				str +="<td>"+result[i].heading+"</td>";
				str +="<td>"+result[i].leaderName+"</td>";
				str +="<td>"+result[i].voterId+"</td>";
				str +="<td>"+result[i].votersCount+"</td>";
				if(result[i].nonVotersCount!=null)
				  str +="<td>"+result[i].nonVotersCount+"</td>";
				else
				  str+="<td>0</td>";
				if(result[i].familiesCount!=null)
				  str +="<td>"+result[i].familiesCount+"</td>";
                else
				  str+="<td>0</td>";
               str +="</tr>";
			}
		str +="</tbody>";
	str+= "</table>";
	
	$("#booksWiseSummaryDiv").html(str);
	$(".summaryDivv").dataTable({        
		   "aaSorting": [[ 5, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		   "aoColumns": [null,null,null,null,null,null,null,null] 
        });
	
	
}
function buildLeadersOfPanchayat(result){

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
						str +="<td title='Click To See HouseHolds Under This Leader' onclick='getHouseHoldsUnderLeader("+result.leadersOfPnchyt[i].leaderId+','+result.panchayatId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].voterName+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].voterCardNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].mobileNo+"</td>";
						str +="<td title='Click To See HouseHolds Under This Leader' onclick='getHouseHoldsUnderLeader("+result.leadersOfPnchyt[i].leaderId+','+result.panchayatId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].houseHoldsCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#summariesId").html(str);
		
		$('.summeriesTable').dataTable();
	}
}

function buildHouseHoldsUnderBook(result){
		
		$("#ajaxImg").hide();
		
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
	$("#ajaxImg").hide();
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
						str +="<td title='Click To See Family Members Under Family Head' onclick='getFamilyMembers(\""+result.leadersOfPnchyt[i].voterName+"\","+result.leadersOfPnchyt[i].houseHoldId+")' style='cursor:pointer;'>"+result.leadersOfPnchyt[i].voterName+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].voterCardNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].houseNo+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].votersCount+"</td>";
						str +="<td>"+result.leadersOfPnchyt[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#summariesId1").html(str);
		
		$('.summeriesTable1').dataTable({
		   "aaSorting": [[ 5, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		   "aoColumns": [null,null,null,null,null] 
        });

	}
}

function buildHouseHoldsUnderOptions(result){
	$("#ajaxImg").hide();
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
						if(result.familyHeadsUnderOption[i].nonVotersCount != null){
						str +="<td>"+result.familyHeadsUnderOption[i].nonVotersCount+"</td>";
						}
						else{
						str +="<td>--</td>";
						}
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#questSummary2").html(str);
		
		$('.questSummaryTbl').dataTable();
	}
}

function getFamilyMembers(name,householdId){
	$("#questSummary3").html("");
	$("#ajaxImg").show();
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
		  $("#ajaxImg").hide();
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
	$("#panchayatSummaryDivId").html("");
	$("#consolidateReport,#summariesId1,#questSummary3,#booksWiseSummaryDiv").html("");
	$("#ajaxImg").show();
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
	$("#ajaxImg").hide();
	
	if(result!=null && result.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'>NON VOTERS DETAILS</h4>";
		
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
						if(result[i].ageRangesList[j].ageRangeCount != null){
							str +="<td title='Click To See ("+ result[i].ageRangesList[j].ageRange+ ") Age Range Voters Details' onclick='getAgeWiseNonVotersDetails(\""+result[i].ageRangesList[j].ageRange+"\",\""+result[i].panchayatName+"\","+result[i].panchayatId+")' style='cursor:pointer;'>"+result[i].ageRangesList[j].ageRangeCount+"</td>";
						}
						else{
							str +="<td>--</td>";
						}
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

function getAgeWiseNonVotersDetails(range,name,panchayatId){
	$("#ageRangeNonVotersSummaryDiv").html("");
	$("#ajaxImg").show();
	var age = range.split("-");
	var details={
             panchayatId:panchayatId,
			 fromAge:age[0],
			 toAge:age[1],
			 type:1,
			 task:"nonVotersAgeWiseDetails"
	};
	$.ajax({
          type:'POST',
          url: 'getAgeWiseNonVotersDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(details)},

          success: function(result){ 
			buildAgeWiseNonVotersDetails(result,name,range);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildAgeWiseNonVotersDetails(result,name,range){
	$("#ajaxImg").hide();
	if(result!=null && result.length>0){
		var str = "";
			str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> ("+range+") AGE RANGE NON VOTERS DETAILS IN  "+ name.toUpperCase()+"</h4>";
		
		str+= "<table class='table table-bordered questSummaryTbl'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>NAME</th>";	
					str +="<th>HOUSE No</th>";	
					str +="<th>AGE</th>";	
					str +="<th>RELATIVE NAME</th>";
						
					
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result){
					str +="<tr>";
						str +="<td>"+result[i].name+"</td>";
						str +="<td>"+result[i].houseNo+"</td>";
						str +="<td>"+result[i].age+"</td>";
						str +="<td>"+result[i].relativeName+"</td>";
					
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		$("#ageRangeNonVotersSummaryDiv").html(str);
		
		$('.questSummaryTbl').dataTable();
	}

}

function buildPanchayatSummaryReport(result){
	$("#panchayatSummaryDivId").html("");
	$("#constiSummaryDiv").html("");
	$("#ajaxImg").hide();
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
					str1 +="<th>"+result.houseHoldsCount+"</th>";
					str1 +="<th>"+result.ledersCount+"</th>";
					str1 +="<th>"+result.votersCount+"</th>";
					str1 +="<th title='Click To See Non Voters Details' onClick='getNonVotersAgeRangeWiseCount("+result.constituencyId+")' style='cursor:pointer;'>"+result.nonVotersCount+"</th>";
				str1 +="</tr>";
			
		str1 +="</table>";
		
	$("#constiSummaryDiv").html(str1);
	
	if(result.panchayatList!=null && result.panchayatList.length>0){
		var str = "";
		str +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> PANCHAYAT WISE SUMMARY OF "+result.constituency+" CONSTITUENCY </h4>";
		str+= "<table class='table table-bordered' id= 'tableId'>";
			str +="<thead>";
				str +="<tr>";
					str +="<th>PANCHAYAT NAME</th>";	
					str +="<th>HOUSE HOLDS</th>";	
					str +="<th>LEADERS</th>";
					str +="<th>VOTERS</th>";
					str +="<th>NON-VOTERS</th>";
				str +="</tr>";
			str +="</thead>";
			
			str +="<tbody>";
				for(var i in result.panchayatList){
					str +="<tr>";
						str +="<td>"+result.panchayatList[i].panchayatName+"</td>";
						str +="<td title='Click To See HouseHolds In this Panchayat' onclick='getHouseHoldsUnderPanchayat("+result.panchayatList[i].panchayatId+") ' style='cursor:pointer;'>"+result.panchayatList[i].houseHoldsCount+"</td>";
						str +="<td title='Click To See Leaders In this Panchayat' onclick='getLeadersUnderPanchayat("+result.panchayatList[i].panchayatId+") ' style='cursor:pointer;'>"+result.panchayatList[i].ledersCount+"</td>";
						str +="<td>"+result.panchayatList[i].votersCount+"</td>";
						str +="<td>"+result.panchayatList[i].nonVotersCount+"</td>";
					str +="</tr>";
				}
			str +="</tbody>";
		str+= "</table>";
		
		
		}
	
	$("#panchayatSummaryDivId").html(str);
	
	$("#tableId").dataTable({        
		   "aaSorting": [[ 5, "desc" ]],
		   "aLengthMenu": [[10, 30, 90, -1], [15, 30, 90, "All"]],
		   "aoColumns": [null,null,null,null,null] 
        });
	if(result.hhListFinal!=null && result.hhListFinal.length>0){
	var str2='';
	str2 +="<h4 class='offset3' style='color:red;margin-down:20px;margin-up:20px;'> BOOK WISE SUMMARY OF "+result.constituency+" CONSTITUENCY </h4>";
	str2+= "<table class='table table-bordered summaryDivv'>";
		str2 +="<thead>";
			str2 +="<tr>";
				str2 +="<th>BookNo</th>";	
				//str2 +="<th>MandalName</th>";	
				str2 +="<th>PanchayatName</th>";	
				str2+="<th>Name</th>";
				str2+="<th>VoterId</th>";
				str2+="<th>VotersCount</th>";
				str2+="<th>NonVotersCount</th>";
				str2+="<th>FamiliesCount</th>";
				str2 +="</tr>";
		str2 +="</thead>";
		
		str +="<tbody>";
			for(var i in result.hhListFinal)
			{
				str2 +="<tr>";
				str2 +="<td>"+result.hhListFinal[i].bookNo+"</td>";
				if(result.hhListFinal[i].panchayatName!=null)
					str2 +="<td>"+result.hhListFinal[i].panchayatName+"</td>";
				else
					str2 +="<td>--</td>";
				str2 +="<td>"+result.hhListFinal[i].leaderName+"</td>";
				str2 +="<td>"+result.hhListFinal[i].leaderVoterId+"</td>";
				str2 +="<td>"+result.hhListFinal[i].votersCount+"</td>";
				if(result.hhListFinal[i].nonVotersCount!=null)
				  str2 +="<td>"+result.hhListFinal[i].nonVotersCount+"</td>";
				else
				  str2 +="<td>0</td>";
				if(result.hhListFinal[i].houseHoldsCount!=null)
				  str2 +="<td>"+result.hhListFinal[i].houseHoldsCount+"</td>";
                else
				  str2 +="<td>0</td>";
               str2 +="</tr>";
			}
		str2 +="</tbody>";
	str2 += "</table>";
	
	$("#booksWiseSummaryDiv").html(str2);
	$(".summaryDivv").dataTable({        
		   "aaSorting": [[ 5, "desc" ]],
		   "aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		   "aoColumns": [null,null,null,null,null,null,null] 
        });

	//getBooksOfHouseHold();
	//getBooksWiseSummary();
}
}
</script>
</body>
</html>