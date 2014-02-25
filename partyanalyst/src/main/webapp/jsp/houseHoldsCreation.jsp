<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<style>
textarea{
	background-color:#fff;
}
#contenttable{
	min-height:200px;
}

</style>

<script>
$(document).ready(function(){
	$('#mainTypeId').change(function(){
		getSubSurveyTypes(this.value);
	});
	$('#mainTypeId').change();
});
</script>

  <div class="container" id="btnDiv">
        
		<div class="span" id="mainQtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createMainQuestions();" value="Create Main Question"></input></div>

		<div class="span" id="subQtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createSubQuestions();" value="Create Sub Questions" /></div>

		<div class="span" id="qtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createQuestions();" value="Create Questions"></input></div>

		<div class="span" id="leaderDiv"><input type="button" class="btn btn-primary" style="margin:20px 50px" onclick="createNewLeader();" value ="Create Leader"></input></div>
     
 </div>
<div id="mainQtnDivDialog" style="display:none">
<div id="errorQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv1"></div>
    <div id="mainQtnDivId" style="margin:15px">Create MainQuestion :<font class="mandatory">*</font><input id="mainQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateMainQtn();"></input>
</div>

<div id="subQtnDivDialog" style="display:none">
<div id="errorSubQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv2"></div>
     <div id="subQtnDivId" style="margin:15px 70px;">Select Main Type:
	 <s:select theme="simple" name="mainTypeId1" id="mainTypeId1" list="mainSurveyTypes" listKey="id" listValue="name"/></div>
    <div style="margin-left:36px;">Create SubQuestion :<font class="mandatory">*</font><input id="subQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateSubQtn();"></input>
</div>

 <div class="container" id="createQstnDiv" style="display:none">
   <div id="surveyQuestion">
     <div id="errorDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
	 <div id="statusDiv"></div>
     <div class="well">
     Select Main Type:
	 <s:select theme="simple" name="mainTypeId" id="mainTypeId" list="mainSurveyTypes" listKey="id" listValue="name"/>     	 
	     Select Sub Type:
	   <select id="subTypeId">			
	  </select>
	  </div>

	<!--<div id="errorDiv"></div>
	<div id="statusDiv"></div>-->

		<div id="questionDiv" class="well">

		<u><h5>Question:</h5></u>

		<!--<div class="note">Enter Question :
		<!-- EXAMPLE: Who will be the next C.M? :: Q1-->
		<!--</div>-->
		  <textarea id="questionId"></textarea>
		  <div id="qstnErrMsg"></div>
		</div>

		<div id="questionTypeDiv" class="well">
			<div style="margin:4px;">
			
			<b>Choose question type :</b>

			 <s:select theme="simple" name="optionTypeId" id="optionTypeId" list="optiontypes" listKey="id" listValue="name"/>  
			 </div>
		</div>
		<div id="locationTypeDiv" class="checkbox">
		</div>

    
		<div id="answersDiv" class="well">
           <u><h5>Answer Options:</h5></u>
			<div>
			<div class="note">NOTE :Enter options seperated by ::</div>
			<h5>FOR EXAMPLE: FIRST OPTION :: SECOND OPTION :: THIRD OPTION</h5>
			  <textarea  rows="3" cols="70" id="options"></textarea>

				<label><input type="checkbox" id="commentId">Comment Field</input></label>
			     <div id="optnErrMsg"></div>			  
			</div>
			 <a class="btn pull-right" href="javascript:{saveQuestionDetails()}">Add Question</a>

		</div>
	</div>
 </div>

<div id="leaderDivDialog" style="display:none">
 <div id="errorLeaderDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
  <div id="statusDiv3"></div>
    <div style="margin-left:36px;">
    <div>Name :<font class="mandatory">*</font><input id="leaderNameId" type="text" maxlength="100" style="margin-left:45px;"></div>
	<div>Mobile No :<font class="mandatory">*</font><input id="lmobileId" type="text" maxlength="20" style="margin-left:24px;"></div>
	<div>Voter Id:<font class="mandatory">*</font><input id="lVoterId" type="text" maxlength="20" style="margin-left:39px;"></div>
	<div>Unique Id:<font class="mandatory">*</font><input id="luniqueId" type="text" maxlength="20" style="margin-left:29px;"></div>

	<div>is Active:<font class="mandatory">*</font><input id="yesId" type="radio" value="YES" name="radiobtn" style="margin:0px 5px 0px 40px;"/>YES<input id="noId" type="radio" style="margin:0px 5px 0px 20px;" value="NO" name="radiobtn" style="margin-left:15px;"/>NO
	</div>
 
	<div id="ConstituencyDiv" class="selectDiv">		
		 Select Constituency<font class="requiredFont">*</font>
		<select id="constituencyId" onChange="getBooths()" style="margin:7px;">
		</select>
	</div>
	<div id="BoothsDiv" class="selectDiv">		
		 Select Booth<font class="requiredFont">*</font>
		<select id="boothId" style="margin:7px 52px;"></select>
	</div>	
	
	<input type="button" class="btn" value="Create" style="margin:20px 112px" onClick="validateLeaderDetails();"></input>
</div>

<script>
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
    $('#createQstnDiv').dialog({
	 title:'Enter Question Details',
	 width:800
   });
 }

  function createSubQuestions(){
    $('#subQtnDivDialog').dialog({
	 title:'Enter Question Details',
	 width:500,
		 height:300
   });
 }
 
 function createMainQuestions(){
    $('#mainQtnDivDialog').dialog({
	 title:'Enter Question Details',
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

	 $('#leaderDivDialog').dialog({
	 title:'Enter Leader Details',
	 width:500
     });

    var voterIdDtls={task:"getvoterIds"}
	$.ajax({
	   type:"POST",
	   url:"getVoterIdsAction.action",
       dataType: 'json',
	   data:{task:JSON.stringify(voterIdDtls)},
        }).done(function(result){
        voterIdsArray=result;
      });

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
        $("#constituencyId option").remove();
        for(var i in result)
        {
		   $('#constituencyId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
        }
	});
 }
 
 function validateLeaderDetails()
 {
   var leaderDtls={leaderName:'',mobileNo:'',voterId:'',uniqueCode:'',constituencyId:'',boothId:'',isActive:'NO'};
   
   leaderDtls.mobileNo = $("#lmobileId").val().trim();
   leaderDtls.voterId  = $("#lVoterId").val().trim();
   leaderDtls.uniqueCode = $("#luniqueId").val().trim();
   leaderDtls.leaderName = $("#leaderNameId").val().trim();
   leaderDtls.isActive  = $("input[name='radiobtn']:checked").val();
   leaderDtls.constituencyId = $("#constituencyId option:selected").val();
   leaderDtls.boothId = $("#boothId option:selected").val();
   var pattern1= /^\d{10}$/;

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
	 if(leaderDtls.voterId.length == 0){
       $('#errorLeaderDiv').html("Please enter Voter Id<br>");
	   return;
   }
   
   if(leaderDtls.voterId.length > 0){
     for(var i in voterIdsArray){
        if(leaderDtls.voterId == voterIdsArray[i])
		  {
		    $('#errorLeaderDiv').html("Voter Id already exists<br>");
	         return;
		  }	
     }
   }
   if(leaderDtls.uniqueCode.length == 0){
       $('#errorLeaderDiv').html("Please enter Unique Code<br>");
	   return;
   }

   if(leaderDtls.constituencyId == 0)
   {
        $('#errorLeaderDiv').html("Please Select Constituency<br>");
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
		    $('#statusDiv3').text('Saved Successfully..').css("color","green");
		    setTimeout('$("#statusDiv3").css("visibility","hidden")',2500);
			$('#lmobileId,#lmobileId,#lVoterId,#luniqueId,#leaderNameId').val("");
			$('#constituencyId').val(0);
			  }
		      else if(result.resultCode == 1){
			     $('#errorLeaderDiv').html("Name already exists,Please Enter another<br>");
	             return;
		      }
		  }
	   });
 }


 function getBooths()
 {
    var constDtls={
             constituencyId:$('#constituencyId').val()
	};

	$.ajax({
          type:'POST',
          url: 'getBoothsAjaxAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constDtls)},
     	  }).done(function(result){
             $("#boothId option").remove();
             for(var i in result)
             {
		        $('#boothId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
             }
	   });

 }

 </script>
</body>
</html>