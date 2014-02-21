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
     
 </div>
<div id="mainQtnDivDialog" style="display:none">
<div id="errorQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv2"></div>
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
              $('#statusMsg1').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
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
              $('#statusMsg2').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
		  },
		  error:function() { 
		  }
	  });
 }

 </script>
</body>
</html>