<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Analysis</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="js/surveyAnalysis/surveyAnalysis.js"></script>
<script type="text/javascript">
   function openSurveyQuestionAddWindow(){
      $( "#dialogWindowDiv" ).dialog({
	    height:445,
		width:612
	  });
   }
   var optionId = 0;
   var suboptionId = 0;
   function buildQuestion(){
      optionId = 0;
      var str = '';
      $("#questionOptionsDIV").html("");
	  $("#addNewOptionsDIV").html("");
	  
	  if($("#questionType option:selected").val() == 1){
	    str+='<div id="optionDIV0" ><div style="margin-bottom:5px;">Options : </div><div><input type="text" id="option0" /> <input type="checkbox" /> Check to show remark  <input type="checkbox" id="hassubDIV0" onclick="showSubQuestion('+optionId+',this.id);"/> Has sub options</div></div>';
		str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
		$("#questionOptionsDIV").html(str);
		
		str ='<div><input type="button" class="btn" onclick="buildOptions();" value="Add Option" > </div>';
	    $("#addNewOptionsDIV").html(str);
	  }
	  
   }
   
   function buildOptions(){
      optionId = optionId+1;
	  var str = '';
       str+='<div id="optionDIV'+optionId+'" style="margin-bottom:5px;"><div><input type="text" id="option0" /> <input type="checkbox" /> Check to show remark  <input type="checkbox"  id="hassubDIV'+optionId+'" onclick="showSubQuestion('+optionId+',this.id);" /> Has sub options <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeOption('+optionId+');" ></span></div></div>';
	   str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
		$("#questionOptionsDIV").append(str);
   }
   
   function removeOption(id){
    if(confirm("Do you want to delete this option?")){
      $('#optionDIV'+id).remove();
	  $('#subQuestionTypeDIV'+id).remove();
	  $('#subQuestionOptDIV'+id).remove();
	  $('#addNewSubQuestOptDIV'+id).remove();
	  
	}
   }
   
   function showSubQuestion(optionId,id){
     if($("#"+id).is(':checked')){
       var str ='';
       str+='<div>';
	   str+='  Select Sub Question Type : <select id="subquestionType'+optionId+'" onchange="buildSubQuestion('+optionId+');">';
	   str+='   <option value="0">Select</option>';
	   str+='   <option value="1">Multiple choice with single select</option>';
	   str+='  </select>';
	   str+=' </div>';
	   $('#subQuestionTypeDIV'+optionId).html(str);
	  }else{
	      $('#subQuestionTypeDIV'+optionId).html("");
		  $('#subQuestionOptDIV'+optionId).html("");
		  $('#addNewSubQuestOptDIV'+optionId).html("");
	  }
   }
   
   function buildSubQuestion(optionId){
      suboptionId = suboptionId+1;
      var str = '';
      $("#subQuestionOptDIV"+optionId).html("");
	  $("#addNewSubQuestOptDIV"+optionId).html("");
	  
	  if($("#subquestionType"+optionId+" option:selected").val() == 1){
	    str+='<div id="subquestDIV'+suboptionId+'" ><div style="margin-bottom:5px;">Sub Options : </div><div><input type="text" id="subquest'+suboptionId+'" /> <input type="checkbox" /> Check to show remark </div></div>';
		$("#subQuestionOptDIV"+optionId).html(str);
		str ='<div><input type="button" class="btn" onclick="buildSubOptions('+optionId+');" value="Add Sub Option" > </div>';
	    $("#addNewSubQuestOptDIV"+optionId).html(str);
	  }
	  
   }
   function buildSubOptions(id){
      suboptionId = suboptionId+1;
	  var str = '';
       str+='<div id="subquestDIV'+suboptionId+'" style="margin-bottom:5px;"><div><input type="text" id="subquest'+suboptionId+'" /> <input type="checkbox" /> Check to show remark  <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeSubOption('+suboptionId+');" ></span></div></div>';
		$("#subQuestionOptDIV"+id).append(str);
   }
   function removeSubOption(id){
     if(confirm("Do you want to delete this sub option?")){
      $('#subquestDIV'+id).remove();
	}
   }
</script>
</head>
<body>
     <!--  <input type="button" onclick="openSurveyQuestionAddWindow();"  value="Add Question" />-->
	 <div style="display:none;" id="dialogWindowDiv" title="Add New Question">
	    <div id="questionTitleDIV">Question : <input type="text" id="questionTitle" /></div>
	    <div>
	     Select Question Type : <select id="questionType" onchange="buildQuestion();">
	      <option value="0">Select</option>
	      <option value="1">Multiple choice with single select</option>
	     </select>
	    </div>
		<div style="margin-top:5px;" id="questionOptionsDIV"></div>
		<div style="margin-top:5px;" id="addNewOptionsDIV"></div>
	 </div>
</body>
</html>