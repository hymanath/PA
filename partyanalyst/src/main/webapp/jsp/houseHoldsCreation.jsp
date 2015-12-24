<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" type="text/css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/blockui.js"></script>

</head>
<body>
<style>
textarea{
	background-color:#fff;
	resize:none;
}
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 200px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}
#contenttable{
	min-height:200px;
}

.boothSpan{
	width:60px;
	text-align:center;
	
}
h5{
color:#6E6E6E;
}
.mandatory,.requiredFont{
color:#ff0020;
}
.selectDiv{
margin-top:15px;
}
</style>

<script>
$(document).ready(function(){
	$('#mainTypeId').change(function(){
		getSubSurveyTypes(this.value);
	});
	$('#mainTypeId').change();
createNewLeader();
  $('#CreateLedrDiv').click(function(){
	createNewLeader();
	$('#leaderDivDialog').show();
	$('#assignBoothOrBooks').hide();
	$('#addBookId').hide();
	$('#AssignBoothDiv').removeClass('active');
	$('#addBooksId').removeClass('active');
	$('#CreateLedrDiv').addClass('active');
	$("#assgnBooths,#assgnBooks,#addBooksErrDiv,#lVoterId,#leaderNameId,#lmobileId,#luniqueId").html('');
  });
  
  $('#AssignBoothDiv').click(function(){
  	$("#assgnBooths,#assgnBooks,#addBooksErrDiv").html('');
  assignBoothOrBooks();
	$('#leaderDivDialog').hide();
	$('#addBookId').hide();
	$('#assignBoothOrBooks').show();
	$('#CreateLedrDiv').removeClass('active');
	$('#addBooksId').removeClass('active');
	$('#AssignBoothDiv').addClass('active');
  }); 
  
  $('#addBooksId').click(function(){
  	$("#assgnBooths,#assgnBooks,#addBooksErrDiv,#bookId").html('');
	showBooks();
	assignnewBooks();
	$('#leaderDivDialog').hide();
	$('#assignBoothOrBooks').hide();
	$('#addBookId').show();
	$('#CreateLedrDiv').removeClass('active');
	$('#AssignBoothDiv').removeClass('active');
	$('#addBooksId').addClass('active');
  });
 
		  
});
</script>

  <div class="container" id="btnDiv" align="center">
        
	<h4 style="background-color: #80d1f1; padding: 5px; border-radius: 5px; margin-top: 25px;"> LEADER CREATION FOR HOUSE HOLDS </h4>
	<div  style="margin-top:20px;" >
	
		<input type="button" class="btn btn-success"  style="margin-left:250px; " onClick="createQuestions();" value="Create Questions"></input>
		<input type="button" class="btn btn-success"  onClick="createMainQuestions();" value="Create Main Question"></input>
		<input type="button" class="btn btn-success"  onClick="createSubQuestions();" value="Create Sub Questions" />
		<a class="btn btn-success" href="houseHoldsSummaryReportAction.action"> House Holds Report</a>
		<!--
		<input type="button" class="btn btn-primary"  onclick="createNewLeader();" value ="Create Leader"></input>		
		<input type="button" class="btn btn-primary" onclick="assignBoothOrBooks();" value ="Assign Booth / Books"></input>
		-->
		
	</div>
	
	<ul class="nav nav-tabs" style="font-weight:bold;font-size:15px;">
		  <li class="active"  id="CreateLedrDiv" style="cursor:pointer;">
			<a>Create Leader</a>
		  </li>
		  <li  id="AssignBoothDiv"  style="cursor:pointer;"><a>Assign Booth / Books</a></li>
		  <li  id="addBooksId"  style="cursor:pointer;"><a> Add Books </a></li>
		  
		  <!-- 	<span onclick="showBooks()" class="span3 btn btn-success">Add Books</span>  -->
	</ul>

     
 </div>
 
 <div id="assignBoothOrBooks" class="offset3" style="display:none;font-size:13px;">
	<div id="assignBoothsErrDiv" style="color:#FF0020;"></div>
	
	<div id="constituencyDivAssgn" class="selectDiv">		
		 Select Constituency : <font class="requiredFont"   style="margin-left: 20px;">*</font>
		<select id="constituencyIdAssgn" onChange="getPublicationDatesAssgn()"></select>
	</div>
 
   <div id="publicationDivAssgn" class="selectDiv">		
		 Select Publication Date : <font class="requiredFont">*</font>
		<select id="publicationIdAssgn" onChange="getLeadersOfConstituency()"><option value="0">Select Publication Date</option></select>
		<span style='display:none;float: right;margin:7px 38px;' id='ajaxLoadAssgn'><img src='./images/icons/search.gif' /></span>
	</div>
	
	 <div id="LeaderSelectDiv" class="selectDiv" style="margin-bottom:15px;">		
		 Select Leader : <font class="requiredFont"  style="margin-left: 55px;">*</font>
		<select id="leaderId" onchange="assignBooths()"><option value="0">Select Leader</option></select>
		<span style='display:none;float: right;margin:7px 38px;' id='ajaxLoadAssgnLdr'><img src='./images/icons/search.gif' /></span>
	</div>
 
	<span onclick="assignBooths()" class="span3 btn btn-success">Assign Booths</span>
	<span onclick="assignBooks()" class="span3 btn btn-success">Assign Books</span>
	<!-- <span onclick="showBooks()" class="span3 btn btn-success">Add Books</span> -->
	
 </div>
 
 <div id="assgnBooths" class="span12 offset2"></div>
 <div id="assgnBooks" class="span12 offset2"></div>
 
 <div id="addBookId" style="display:none;font-size:13px;" class="offset3 span12">
	<div id="addBooksErrDiv" style="color:#FF0020;margin-bottom:10px;"></div>
	<div id="constituencyDivAssgn" class="selectDiv"  style="margin-bottom:15px;">		
		 Select Constituency : <font class="requiredFont"   style="margin-left: 20px;">*</font>
		<select id="constiList" onChange="getPublicationDatesForBook()"></select>
	</div>
 
<!-- 
 <div id="publicationDivAssgn" class="selectDiv"  style="margin-bottom:15px;">		
		 Select Publication Date : <font class="requiredFont">*</font>
		<select id="publicationList"><option value="0">Select Publication Date</option></select>
		<span style='display:none;float: right;margin:7px 38px;' id='ajaxLoadAssgn'><img src='./images/icons/search.gif' /></span>
	</div>
	-->
	<span>Give Book No : <font class="requiredFont" style="margin-right:55px;">*</font> </span><input type="text" id="bookId"/><span class="btn btn-mini btn-success" onclick="addBooks()" style="margin-left:20px;"> Add Book </span>
 </div>
 
 
<div id="mainQtnDivDialog" style="display:none">
<div id="errorQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv1"></div>
    <div id="mainQtnDivId" style="margin:15px">Create MainQuestion :<font class="mandatory">*</font><input id="mainQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateMainQtn();"></input>
</div>

<div id="subQtnDivDialog" style="display:none" class="well">
<div id="errorSubQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv2"></div>
     <div id="subQtnDivId" style="margin:15px 70px;" ><span style="margin-left: -14px;"> Select Main Type : <font class="mandatory">*</font> </span>
	 <s:select theme="simple" name="mainTypeId1" id="mainTypeId1" list="mainSurveyTypes" listKey="id" listValue="name"/></div>
    <div style="margin-left:36px;">Create SubQuestion : <font class="mandatory">*</font> <input id="subQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateSubQtn();"></input>
</div>

<div id="searchDiv">
 <div id="errorSearchDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
</div>

 <div class="container" id="createQstnDiv" style="display:none">
   <div id="surveyQuestion"  class="well">
     <div id="errorDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
	 <div id="statusDiv"></div>
     <div class="well">
     <span style="color:#6E6E6E;font-weight:bold;">Select Main Type:</span>
	 <s:select theme="simple" name="mainTypeId" id="mainTypeId" list="mainSurveyTypes" listKey="id" listValue="name"/>     	 
	  <span style="color:#6E6E6E;font-weight:bold;">   Select Sub Type:</span>
	   <select id="subTypeId">			
	  </select>
	  </div>

	<!--<div id="errorDiv"></div>
	<div id="statusDiv"></div>-->

		<div id="questionDiv"  class="well">

		<h5>Question:</h5>

		<!--<div class="note">Enter Question :
		<!-- EXAMPLE: Who will be the next C.M? :: Q1-->
		<!--</div>-->
		  <textarea id="questionId"></textarea>
		  <div id="qstnErrMsg"></div>
		</div>

		<div id="questionTypeDiv" class="well">
			<div style="margin:4px;">
			
			<span style="color:#6E6E6E;font-weight:bold;">Choose question type :</span>

			 <s:select theme="simple" name="optionTypeId" id="optionTypeId" list="optiontypes" listKey="id" listValue="name"/>  
			 </div>
		</div>
		<div id="locationTypeDiv" class="checkbox">
		</div>

    
		<div id="answersDiv"  class="well">
           <h5>Answer Options:</h5>
			<div>
			<div class="note" style="color:#ff0020;font-weight:bold;">NOTE :Enter options seperated by ::</div>
			<h5>FOR EXAMPLE: FIRST OPTION :: SECOND OPTION :: THIRD OPTION</h5>
			  <textarea  rows="3" cols="70" id="options"></textarea>

				<label><input type="checkbox" id="commentId"style="margin-top:-3px; margin-right: 5px;" >Comment Field</input></label>
			     <div id="optnErrMsg"></div>			  
			</div>
		
		</div>
			 <a class="btn pull-right" href="javascript:{saveQuestionDetails()}" style="margin-top:-15px;">Add Question</a>
	</div>
 </div>

<div id="leaderDivDialog" style="font-size:14px;">
 <div id="errorLeaderDiv" style="color:red;font-weight:bold;margin-bottom:10px;" class="offset3"></div>
  <div id="statusDiv3" class="offset3"></div>
    <div class="offset3">
	<div>Voter Id : <font class="mandatory" style="margin-right: 108px;">*</font><input id="lVoterId" type="text" maxlength="20" onblur="validateVoterId();"></div>
    <div>Name : <font class="mandatory" style="margin-right: 125px;">*</font><input id="leaderNameId" type="text" maxlength="100">
    <!--  <input type="button" class="btn" value="Search" style="margin:1px 8px 14px" onClick="searchNow1();"></input>-->
    </div>
	<div>Mobile No : <font class="mandatory" style="margin-right: 98px;">*</font><input id="lmobileId" type="text" maxlength="20"></div>	
	
	
	
	<div>is Active : <font class="mandatory" style="margin-right: 105px;">*</font><input id="yesId" style="margin-top: -2px;" type="radio" value="YES" name="radiobtn" checked="true"/>YES<input id="noId" type="radio" style="margin-top: -2px;margin-left:15px;" value="NO" name="radiobtn"/>NO
	</div>
 
	<div id="ConstituencyDiv" class="selectDiv">		
		 Select Constituency : <font class="requiredFont" style="margin-right: 30px;">*</font>
		<select id="constituencyId" onChange="getPublicationDates()">
		</select>
	</div>
 
   <div id="publicationDiv" class="selectDiv">		
		 Select Publication Date : <font class="requiredFont" >*</font>
		<select id="publicationId" style="margin:7px;"><option value="0">Select Publication Date</option>
		</select> <span  id='ajaxLoad'><img src='./images/icons/search.gif' style="display:none;"/></span>
	</div>

	<div id="BoothsDiv" class="selectDiv">		
		 Select Booth : <font class="requiredFont" style="margin-right: 74px;">*</font>
		<select id="boothsId"><option value="0">Select Booth</option></select>
	</div>	
	
	<div  class="selectDiv">Book No : <font class="mandatory" style="margin-right: 106px;">*</font>
	<input id="luniqueId" type="text" maxlength="20" onkeyup="checkBookAvail()"> <span id="avalStatus" style="font-weight:bold;margin-left:15px;"></span></div>
	
	<input type="button" class="btn btn-success" value="Create" style="margin-left: 150px;" onClick="validateLeaderDetails();"></input>
</div>
</div>


<script>

var isBookExist = false;
function checkBookAvail(){
	var consti = $("#constituencyId").val();
	var publication = $("#publicationId").val();
	var bookNo = $("#luniqueId").val();
	$('#avalStatus,#errorLeaderDiv').html('');
	 var numbers = /^[0-9]/;  
	 
	 if(bookNo.trim().length > 0){
	 
	 if(!(bookNo.match(numbers)))  
      {  
	  $("#luniqueId").val('');
	   $('#avalStatus').fadeIn()
	   $('#avalStatus').html('<span style="color:#FF0020;"> Only numbers allowed.</span>').fadeOut(2000);  
      return false;  
      }  
      
	if(consti == 0 || publication == 0){
		//alert(" Please Select Constituency & Publication");
		$("#errorLeaderDiv").html(' Please Select Constituency & Publication ');
		return;
	}
	else if(bookNo.trim() == 0){
		$("#errorLeaderDiv").html(' Please enter Book No. ');
		return;
	}
	
	var reqDtls={
             constituency:consti,
			 publication:publication,
			 bookNo:bookNo,
			 task:"checkBookAvail"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
		buildStatusOfBook(result);
	});
}      
}

function buildStatusOfBook(result){
	if(result.statusOfBook !=null){
		if(result.statusOfBook == "notExist"){
			isBookExist = true;
			$('#avalStatus').html('<span style="color:#66AF66;">You Can Create Book With This Number </span>');
		}else if(result.statusOfBook == "exist"){
			isBookExist = false;
			$('#avalStatus').html('<span style="color:red;"> Book Already Exist With This Number </span>');
		}
	}else{
		isBookExist = false;
		$('#avalStatus').html('<span style="color:red;"> Book Already Exist With This Number </span>');
	}

}

function getLeadersOfConstituency(){

	var consti = $("#constituencyIdAssgn").val();
	var publication = $("#constituencyIdAssgn").val();
	
	if(consti == 0 || publication == 0){
		alert(" Please Select Constituency & Publication");
		return;
	}
	
	

	var constiDtls={
             constituency:consti,
			 publication:publication,
			 task:"leadersOfConstituency"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(constiDtls)},
	}).done(function(result){
		$("#leaderId option").remove();
		if(result.leadersOfConsti !=null){
			for(var i in result.leadersOfConsti){
				$('#leaderId').append('<option value='+result.leadersOfConsti[i].leaderId+'>'+result.leadersOfConsti[i].voterId+' - '+result.leadersOfConsti[i].leaderName +'</option>');         
			}
		}
	
	});
}

function assignBoothOrBooks(){
	
	$("#assignBoothOrBooks").css("display","block");

	var districtDtls={
             electionTypeId:2,
			 stateId:1,
			 task:"getConstituencies"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getAllConstituenciesInState.action",
		  data :{task:JSON.stringify(districtDtls)},
	}).done(function(result){
        $("#publicationIdAssgn option").remove();
        $("#publicationIdAssgn,#publicationList").append('<option value="0"> Select Publication </option>');
        $("#leaderId option").remove();
        $("#leaderId").append('<option value="0"> Select Leader </option>');
        $("#constiList option").remove();
        $("#bookId").html('');
        $("#constituencyIdAssgn option").remove();
        for(var i in result)
        {
		   $('#constiList').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
		   $('#constituencyIdAssgn').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
        }
	});
}


function assignnewBooks(){
	
	$("#assignBoothOrBooks").css("display","block");

	var districtDtls={
             electionTypeId:2,
			 stateId:1,
			 task:"getConstituencies"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getAllConstituenciesInState.action",
		  data :{task:JSON.stringify(districtDtls)},
	}).done(function(result){
        $("#constiList option").remove();
        $("#bookId").val('');
        for(var i in result)
        {
		   $('#constiList').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
        }
	});
}

function getPublicationDatesForBook(){
var constnDtls={
             selected:$('#constiList').val(),
			 task:"getPublicationDate"
	};
	$.ajax({
          type:'POST',
          url: 'voterAnalysisAjaxAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			$('#publicationList').find('option').remove();
			  $.each(result,function(index,value){
				  $('#publicationList').append('<option value="'+value.id+'">'+value.name+'</option>');
			  });			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function assignBooths(){
	
	var consti = $("#constituencyIdAssgn").val();
	var publication = $("#publicationIdAssgn").val();
	var leader = $("#leaderId").val();
	$("#assignBoothsErrDiv").html('');
	if(consti == 0 || publication == 0 || leader == 0){
		//alert(" Please Select Constituency & Publication & Leader");		
		$("#assignBoothsErrDiv").html(' Please Select Constituency & Publication & Leader');
		return;
	}
	
	var reqDtls={
             constituency:consti,
			 publication:publication,
			 leader:leader,
			 task:"assignBooths"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
		buildBooths(result);
	});
}

function assignBooks(){

	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });

	var consti = $("#constituencyIdAssgn").val();
	var publication = $("#publicationIdAssgn").val();
	var leader = $("#leaderId").val();
	$("#assignBoothsErrDiv").html('');
	if(consti == 0 || publication == 0 || leader == 0){
		//alert(" Please Select Constituency & Publication & Leader");
		$("#assignBoothsErrDiv").html(' Please Select Constituency & Publication & Leader.');
		return;
	}
	
	var reqDtls={
             constituency:consti,
			 publication:publication,
			 leader:leader,
			 task:"assignBooks"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
        buildBooks(result);
	});
}

function buildBooths(result){

	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
	
	$("#assgnBooks").html("");
	$("#assgnBooths").html("");
	
	var str = "";
		str +="<h4 class='offset4' style='margin-top:10px;margin-bottom:10px;'> Booths In Constituency </h4>"
		str +="<div id='assignErrDiv' style='font-size: 13px; color: green;' class='offset1'></div>"
	if(result.boothsInConstituency!=null && result.boothsInConstituency.length > 0){
		for(var i in result.boothsInConstituency){
			if(!result.boothsInConstituency[i].enableBooth){
				str += "<span class='span1'><input type ='checkbox' value="+result.boothsInConstituency[i].boothId+" class='boothIds' style='margin:5px;'>"+result.boothsInConstituency[i].partNo+"</input></span>";
			}else{
				if(result.boothsInConstituency[i].disableBooth){
					str += "<span class='span1'><input type ='checkbox' value="+result.boothsInConstituency[i].boothId+" class='boothIds' style='margin:5px;' checked disabled='disabled'>"+result.boothsInConstituency[i].partNo+"</input></span>";
				}else{
					str += "<span class='span1'><input type ='checkbox' value="+result.boothsInConstituency[i].boothId+" class='boothIds' style='margin:5px;' checked >"+result.boothsInConstituency[i].partNo+"</input></span>";
				}
				
			}
		}
		str +="<br><span class='btn btn-success offset4' onclick='assignSelectedBooths()'  > Assign </span>";
	}
	else{
			str +="<h4 style='margin-left:100px;color:#FF0020;'> No Data Available...</h4>";
	}
	
	
	$("#assgnBooths").html(str);
	
	$.unblockUI();
}

function buildBooks(result){

	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
	
	$("#assgnBooks").html("");
	$("#assgnBooths").html("");

	var str = "";
		str +="<h4 class='offset4' style='margin-bottom:10px;margin-top:10px;'> Books In Constituency </h4>";
		str +="<div id='assignBooksErrDiv'></div>";
	if(result.booksInConstituency!=null && result.booksInConstituency.length > 0){
		for(var i in result.booksInConstituency){
			if(!result.booksInConstituency[i].enableBook){
				str += "<span class='span1'><input type ='checkbox' value="+result.booksInConstituency[i].bookId+" class='bookIds' style='margin:5px;'>"+result.booksInConstituency[i].bookNo+"</input></span>";
			}else{
				if(result.booksInConstituency[i].disableBook){
					str += "<span class='span1'><input type ='checkbox' value="+result.booksInConstituency[i].bookId+" class='bookIds' style='margin:5px;' checked disabled='disabled'>"+result.booksInConstituency[i].bookNo+"</input></span>";
				}else{
					str += "<span class='span1'><input type ='checkbox' value="+result.booksInConstituency[i].bookId+" class='bookIds' style='margin:5px;' checked >"+result.booksInConstituency[i].bookNo+"</input></span>";
				}
			}
		}		
		str +="<span class='btn btn-success' onclick='assignSelectedBooks()' style='float:right;'> Assign </span>";
	}
	else{
		str +="<h4 style='margin-left:100px;color:#FF0020;'> No Data Available...</h4>";
	}
	
	
	$("#assgnBooks").html(str);
	
	$.unblockUI();
}

function assignSelectedBooks(){
	var booksSlcted = [];
	$('.bookIds:checked').each(function(i){
        booksSlcted.push($(this).val());
    });
	
	var consti = $("#constituencyIdAssgn").val();
	var publication = $("#publicationIdAssgn").val();
	var leader = $("#leaderId").val();
	
	var reqDtls={
             constituency:consti,
			 publication:publication,
			 books:booksSlcted,
			 leader:leader,
			 task : "assignSelectedBooks"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
	//	console.log(result);
		$("#assignBooksErrDiv").html("<span style='color:#54A854;font-size:13px;font-weight:bold;'>Books are Assigned Successfully . ");
		setTimeout(function() {
			assignBooks();
		}, 2000);
		
	});
}

function assignSelectedBooths(){
	var boothsSlcted = [];
	$('.boothIds:checked').each(function(i){
        boothsSlcted.push($(this).val());
    });
	
	var consti = $("#constituencyIdAssgn").val();
	var publication = $("#publicationIdAssgn").val();
	var leader = $("#leaderId").val();
	
	var reqDtls={
             constituency:consti,
			 publication:publication,
			 booths:boothsSlcted,
			 leader:leader,
			 task : "assignSelectedBooths"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
		$.blockUI({ message: '<h6> Booths are Assigned Successfully..  Please wait.. <img src="images/icons/ajaxImg.gif"/></h6>' });
	//	$('#assignErrDiv').html(" Booths are Assigned Successfully .");
		
		setTimeout(function() {
			assignBooths();
		}, 5000);
		
		
		
	});
}

function showBooks(){
	$("#addBookId").css("display","block");
}
function addBooks(){
	var consti = $("#constiList").val();
	//var publication = $("#publicationList").val();
	var bookNo = $("#bookId").val();
		$('#addBooksErrDiv').html('');
	if( consti == 0 || bookNo.trim().length == 0 || bookNo == 0){
		//alert(" Please Select Constituency & Publication & Book");
		$('#addBooksErrDiv').html(' Please Select Constituency &  write Book No.');
		return;
	}
	
	var reqDtls={
             constituency:consti,
			 publication:0,
			 bookNo : bookNo,
			 task : "addGivenBookNo"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getBoothsOrBooksInConstituencyOfLeaderToAssign.action",
		  data :{task:JSON.stringify(reqDtls)},
	}).done(function(result){
		//console.log(result);
		if(result.statusOfBook !=null){
			if(result.statusOfBook == "success"){
				$('#addBooksErrDiv').html("<span style='color:#8BA870;;font-weight:bold;'> New Book added Successfully </span>");
			}
			else{
				$('#addBooksErrDiv').html("<span style='color:#56AE56;font-weight:bold;'>  Book Already exist </span>");		
			}
		}else{
			$('#addBooksErrDiv').html("<span style='color:#FF0020;;font-weight:bold;'> Book Already exist  </span>");
		}
		
	});
}

 function getSubSurveyTypes(mainTypeId)
 {
	  $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});
	    $.ajax({
		  type:'POST',
		  url: 'getSubSurveyTypes.action',
		  dataType: 'json',
		  data: {mainTypeId:mainTypeId},			 
		  success: function(data){  

			  buildSubTypeDetails(data);
		  },
		  error:function() { 
		  }
	});
 }
 function buildSubTypeDetails(result)
 {
    $('#subTypeId').find('option').remove();
	$('#subTypeId').append('<option value="0">Select</option>');
	 $.each(result,function(index,value){
       $('#subTypeId').append('<option value="'+value.id+'">'+value.name+'</option>');
	 });
	 
 }
 var questionDtls={
	 surveyTypeId:'',
     surveySubTypeId:'',
     question:'',
     options:'',
     optnTypeId:'',
	 commentInd:''
 };

 function saveQuestionDetails()
 {
	 $('#optnErrMsg').html('');

	 var errorStr = '';
$('#errorDiv').html('');
	 console.log($('#optionTypeId').val());
	 console.log($('#questionId').val());
if($('#optionTypeId').val() != 3)
	 if($('#options').val().trim() == "")
		 errorStr +='<span>Atleast one option is required</span><br>';

	if($('#questionId').val().trim() == "")
		 errorStr +='<span>Question is required</span>';

	if(errorStr.length > 0)
	 {
		$('#errorDiv').html(errorStr);
		return;
	 }

	questionDtls.commentInd   = $('#commentId').is(':checked')?"true":"false";
	questionDtls.surveyTypeId = $('#mainTypeId').val();
	questionDtls.surveySubTypeId = $('#subTypeId').val();
	questionDtls.question = $('#questionId').val();
	questionDtls.options = $('#options').val();
	questionDtls.optnTypeId = $('#optionTypeId').val();

	  $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	  });

	    $.ajax({
		  type:'POST',
		  url: 'saveQuestionDetails.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(questionDtls)},			 
		  success: function(data){  

		  },
		  error:function() { 
		  }
	});
 }

 function createQuestions(){
 $("#mainTypeId").val(1);
 $("#subTypeId").val(0);
 $("#questionId").val('');
 $("#options").val('');
 $("#optionTypeId").val(1);
 $("#commentId").prop('checked',false);
    $('#errorDiv').html('');
    $('#createQstnDiv').dialog({
	 title:'Create New Question ',
	 width:800
   });
 }

  function createSubQuestions(){
    $('#errorSubQtnDiv').html('');
    $('#subQtnDivDialog').dialog({
	 title:'Create Sub Question ',
	 width:500
   });
 }
 
 function createMainQuestions(){
     $('#errorQtnDiv').html('');
    $('#mainQtnDivDialog').dialog({
	 title:'Create Main Question',
	 width:500
   });
 }
 
 
 function validateMainQtn()
 {
	var mainQtnDtls={mainQtnType:''};
	
	mainQtnDtls.mainQtnType = $('#mainQtnId').val().trim();
	if(mainQtnDtls.mainQtnType == "")
	 {
       $("#errorQtnDiv").html("Please enter the Question");
	   return;
	 }
    
	   $.ajax({
		  type:'POST',
		  url: 'saveMainQuestion.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(mainQtnDtls)},			 
		  success: function(data){  
              $('#statusDiv1').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
		  },
		  error:function() { 
		  }
	   });
 }
 
 
 function validateSubQtn()
 {	 
	var subQtnDtls={mainQtnTypeId:'',subQtnType:''};
 
	subQtnDtls.mainQtnTypeId = $('#mainTypeId1 option:selected').val();;
    subQtnDtls.subQtnType=$('#subQtnId').val().trim();
	
    if(subQtnDtls.subQtnType == ""){
       $("#errorSubQtnDiv").html("Please enter the SubQuestion");
	   return;
	}
	if(subQtnDtls.mainQtnTypeId == 0){
		$("#errorSubQtnDiv").html("Please enter the MainQuestion");
		return;
	}
   
	  $.ajax({
		  type:'POST',
		  url: 'saveSubQuestion.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(subQtnDtls)},			 
		  success: function(data){  
              $('#statusDiv2').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
		  },
		  error:function() { 
		  }
	  });
 }
var voterIdsArray=[];
function createNewLeader()
{  
	$("#errorLeaderDiv").html("");
  
	var districtDtls={
             electionTypeId:2,
			 stateId:1,
			 task:"getConstituencies"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getAllConstituenciesInState.action",
		  data :{task:JSON.stringify(districtDtls)},
	}).done(function(result){
	    $("#publicationId option").remove();
        $("#publicationId").append('<option value="0"> Select Publication </option>');
        $("#boothsId option").remove();
        $("#boothsId").append('<option value="0"> Select Booth </option>');
		
        $("#constituencyId option").remove();
        for(var i in result)
        {
		   $('#constituencyId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
        }
	});
 }
 
 
 
 
  
  var voterValid = true;
 function validateVoterId()
 {
 
	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
	$('#errorLeaderDiv').html('');
      var voterIdDtls={task:"getvoterIds"}
	   $.ajax({
	   type:"POST",
	   url:"getVoterIdsAction.action",
       dataType: 'json',
	   data:{task:JSON.stringify(voterIdDtls)},
        }).done(function(result){
        voterIdsArray=result;
      });
    
      var voterDtls={voterIdCardNo:$('#lVoterId').val()};
	   $.ajax({
	   type:"POST",
	   url:"getAllVoterDetailsAction.action",
       dataType: 'json',
	   data:{task:JSON.stringify(voterDtls)},
        }).done(function(result){
		 $("#boothsId option").remove();
	     $('#publicationId option').remove();
		 
		 if(result.boothList== null || result.publicationDatesList==null || result.constyPublicationIds ==null){
			$("#errorLeaderDiv").html("<span style='color:red'>InValid VoterId Please Try Again</span>");
			voterValid = false;
			return;
		 }else{
			voterValid = true;
		 }
		 
		 for(var i in result.publicationDatesList)
          {
			  $('#publicationId').append('<option value="'+result.publicationDatesList[i].id+'">'+result.publicationDatesList[i].name+'</option>');
		  }
		  for(var i in result.boothList)
          {
		     $('#boothsId').append('<option value='+result.boothList[i].id+'>Booth-'+result.boothList[i].name+'</option>');         
          }
          $('#leaderNameId').val(result.constyPublicationIds[0].voterName);
          $('#constituencyId').val(result.constyPublicationIds[0].constituencyId);
		  $('#publicationId').val(result.constyPublicationIds[0].publicationDateId);
          $('#boothsId').val(result.constyPublicationIds[0].boothId);
		  
        
	 });
	 
	 $.unblockUI();
 }
 
 function validateLeaderDetails()
 {
   var leaderDtls={leaderName:'',mobileNo:'',voterId:'',uniqueCode:'',constituencyId:'',boothId:'',isActive:'YES'};
   
   leaderDtls.mobileNo = $("#lmobileId").val().trim();
   leaderDtls.voterId  = $("#lVoterId").val().trim();
   leaderDtls.leaderName = $("#leaderNameId").val().trim();
   leaderDtls.isActive  = $("input[name='radiobtn']:checked").val();
   leaderDtls.constituencyId = $("#constituencyId option:selected").val();
   leaderDtls.boothId = $("#boothsId option:selected").val();
   leaderDtls.uniqueCode = $("#luniqueId").val().trim();
   $('#errorLeaderDiv').html("");
   $('#statusDiv3').html('');
   var pattern1= /^\d{10}$/;
   
   if(!voterValid){
		$('#errorLeaderDiv').html("Invalid Voter Id<br>");	
	   return;
   }
   
   if(!isBookExist){
		$('#errorLeaderDiv').html("Book Already Exist With This Number");	
	   return;
   }

    if(leaderDtls.voterId.length == 0){
       $('#errorLeaderDiv').html("Please enter Voter Id<br>");
	   return;
   }
   if (leaderDtls.leaderName.length == 0){
      $("#errorLeaderDiv").html("Please enter the Name");
         return ;
    }

    if (leaderDtls.mobileNo.length == 0){
      $("#errorLeaderDiv").html("Please enter mobile number");
         return ;
    }

    if (leaderDtls.mobileNo.length > 0){
	  if( (leaderDtls.mobileNo.length !=10) || (!pattern1.test(leaderDtls.mobileNo))) {  
		$("#errorLeaderDiv").html("Please enter valid mobile number");
        return ;
		 } 
	}
	
   if(leaderDtls.voterId.length > 0){
     for(var i in voterIdsArray){
        if(leaderDtls.voterId.toUpperCase() == voterIdsArray[i])
		  {
		    $('#errorLeaderDiv').html("Voter Id already exists<br>");
	         return;
		  }	
     }
   }
   if(leaderDtls.uniqueCode.length == 0){
		if(leaderDtls.constituencyId == 0){
			 $('#errorLeaderDiv').html("Please Select Constituency<br>");
			return;
		}else{
			$('#errorLeaderDiv').html("Please enter Book No<br>");
			return;
		}
   }

   if(leaderDtls.constituencyId == 0)
   {
        $('#errorLeaderDiv').html("Please Select Constituency<br>");
	   return;
   }
   if($("#publicationId option:selected").val() ==0)
   {
        $('#errorLeaderDiv').html("Please Select Publication Date<br>");
	   return;
   }
   if(leaderDtls.boothId == 0)
   {
        $('#errorLeaderDiv').html("Please Select Booth<br>");
	   return;
   }
	if($("input[name='radiobtn']:checked").length == 0)
	{
		  $('#errorLeaderDiv').html("Please Select radio button<br>");
	   return;
	}
	
	$.ajax({
		  type:'POST',
		  url: 'saveLeaderDetails.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(leaderDtls)},	
          }).done(function(result){
		  if(result != null){
			  if(result.resultCode == 0){
				 
				$("#errorLeaderDiv").html('');
				$("#statusDiv3").css("visibility","visible");
				$('#statusDiv3').html('New Leader Saved Successfully...').css("color","green");
				//setTimeout('$("#statusDiv3").css("visibility","hidden")',2500);
				$('#lmobileId,#lmobileId,#lVoterId,#leaderNameId,#luniqueId,#avalStatus').val("");
				$('#constituencyId').find('option').remove();			
				$('#boothsId').find('option').remove();			
				$('#publicationId').find('option').remove();			
			  }
		      else if(result.resultCode == 1){
			     $('#errorLeaderDiv').html("Name already exists,Please Enter another<br>");
	             return;
		      }
		  }
	   });
	   
	
 }

function searchNow1(){

    $("#searchDiv").html("");
	
    if($("#leaderNameId").val().trim() == "")
	{
      $('#errorLeaderDiv').html("Please Select Name to search<br>");
	}
	else if($("#constituencyId").val() == 0)
	{
      $('#errorLeaderDiv').html("Please Select Constituency<br>");
	}
	else if($("#publicationId").val() == 0)
	{
      $('#errorLeaderDiv').html("Please Select Publication Date<br>");
	}
	else {
       $('#searchDiv').dialog({
		title:"search", 
		width:800,
		modal:true,
        buttons: {
			"OK":function() {$(this).dialog('close');}
		}
	   });
		
		var voterCardNo=$("#lVoterId").val();
		var voterName=$("#leaderNameId").val();
		if(voterName==""){
			$("#errorSearchDiv").html("<span style='color:red'> Please Enter Name to Search </span>");
			return;
		}
		//$("#ajaxImg").css("display","inline-block");
		var publicationDateId=$("#publicationId").val();
		var constituency =  $("#constituencyId option:selected").val()
		var jsObj =
		{
			voterCardNo:voterCardNo,
			voterName:voterName,
			publicationDateId:publicationDateId,
			constituency:constituency,
			task:"getVotersMatched"
		};
		$.ajax({
		   type: "POST",
		   url: "getVotersForSearchingAction.action",		
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(results) {
		   buildTableForSearchedVoters(results);
	    });
	}
}
function getPublicationDates()
{
	var constnDtls={
             selected:$('#constituencyId').val(),
			 task:"getPublicationDate"
	};
	$.ajax({
          type:'POST',
          url: 'voterAnalysisAjaxAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			$('#publicationId').find('option').remove();
			  $.each(result,function(index,value){
				  $('#publicationId').append('<option value="'+value.id+'">'+value.name+'</option>');
			  });			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getPublicationDatesAssgn()
{
	var constnDtls={
             selected:$('#constituencyIdAssgn').val(),
			 task:"getPublicationDate"
	};
	$.ajax({
          type:'POST',
          url: 'voterAnalysisAjaxAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			$('#publicationIdAssgn').find('option').remove();
			  $.each(result,function(index,value){
				  $('#publicationIdAssgn').append('<option value="'+value.id+'">'+value.name+'</option>');
			  });			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildTableForSearchedVoters(results){
		
		if(results!=null && results.length>0)
		{
		var str="";
		str+="<table class='table table-bordered' id='searchVotersTableId'>";
			str+="<thead>";
				str+="<tr>";
				//str+="<th>Select</th>";
				str+="<th>Name</th><th>Voter CardId</th><th>Serial No</th><th>Booth No</th></tr></thead><tbody>";
				for(var i in results){
					str+="<tr>";
						//str+="<td><input type='checkbox' id= 'check"+i+"'   name='searchedVoters' class= 'checkedCls' value="+results[i].voterId+" onclick='getChecked(this.id)'></input></td>";
						str+="<td>"+results[i].name+"</td>";
						str+="<td>"+results[i].voterIdCardNo+"</td>";
						str+="<td>"+results[i].toSno+"</td>";
						str+="<td>"+results[i].partNo+"</td>";						
					str+="</tr>";
				}
			str+="</tbody>";
		str+="</table>";
		temp = results;
		str+="<br><span onclick=addVoter() class='btn btn-info'>Add Voter</span>";
		$("#searchDiv").html(str);
		$('#searchVotersTableId').dataTable();
		}
		else
		{
			$("#searchDiv").html("<span style='color:red'>No Data Available For Given Search Details");
		}
	}  

function addVoter(){	
       
	$('input[name="searchedVoters"]:checked').each(function() {
		for(var i in temp){
				if(temp[i].voterId==this.value){
                  $("#lVoterId").attr("value",temp[i].voterIdCardNo);
				  $("#leaderNameId").attr("value",temp[i].name);
				  $('#boothsId').append('<option value="'+temp[i].boothId+'">'+temp[i].partNo+'</option>');
				}
		}$('#searchDiv').dialog( "close" );
	});
}
function getChecked(id){
             
		$(".checkedCls").removeAttr("checked");
	    $('#'+id).attr('checked',true);
}


function getSummary(){
	var constnDtls={
             constituency:constituency,
			 task:"getSummary"
	};
	$.ajax({
          type:'POST',
          url: 'getReportsOfHouseHolds.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			console.log(result);			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}


function getConstituencySummaryWithPanchayat(){
	var constnDtls={
             constituency:constituency,
			 task:"constituencySummary"
	};
	$.ajax({
          type:'POST',
          url: 'houseHoldsSummaryReportAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			console.log(result);			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}


</script>
</body>
</html>