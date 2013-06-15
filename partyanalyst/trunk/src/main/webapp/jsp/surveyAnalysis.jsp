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
   function saveQuestion(){
       var question = $("#questionTitle").val();
	   var questionType = $("#questionType option:selected").val();
	   var showRemark = $("#showRemark").is(':checked');
	   var mainOptionsArray = new Array();
       $('.mainoption').each(function() {
	      var obj = {};
		  var key = $(this).attr("key");
		  var subOptionsArray = new Array();
		  obj["option"] = $(this).val();
	      obj["showRemark"] = $("#optshowremark"+key).is(':checked');
		  obj["hasSubQuestion"] = $("#hassubDIV"+key).is(':checked');
            if($("#hassubDIV"+key).is(':checked')){
			  obj["question"] = $("#subquestionTitle"+key).val();
	          obj["questionType"] = $("#subquestionType"+key+" option:selected").val();
		      obj["showSubRemark"] = $("#showsubRemark"+key).is(':checked');
			  $('.subquestcls'+key).each(function() {
			      var subobj = {};
				  var subkey = $(this).attr("key");
				  subobj["option"] = $(this).val();
	              subobj["showRemark"] = $("#subquestremark"+subkey).is(':checked');
				  subOptionsArray.push(subobj);
			  });
			   obj["subOptions"] = subOptionsArray;
			}
			mainOptionsArray.push(obj);
        });
		console.log(mainOptionsArray);
   }
   
   function openSurveyQuestionAddWindow(){
      
      var browser1 = window.open("createNewQuestionAction.action","addnewquestionwindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
      browser1.focus();
   }
   var optionId = 0;
   var suboptionId = 0;
   function buildQuestion(){
      optionId = 0;
      var str = '';
      $("#questionOptionsDIV").html("");
	  $("#addNewOptionsDIV").html("");
	  
	  if($("#questionType option:selected").val() == 1){
	    str+='<fieldset id="optionDIV0" class="alert alert-info" >';
	    str+='<div><div style="margin-bottom:5px;">Options : </div><div><input  key="'+optionId+'" class="mainoption" type="text" id="option0" /> <input type="checkbox" id="optshowremark0"/> Check to show remark  <input type="checkbox" id="hassubDIV0" onclick="showSubQuestion('+optionId+',this.id);"/> Has sub options</div></div>';
		str+='<div class="span10 offset1">';
		str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	    str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	    str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
		str+='</div>';
		str+='</fieldset>';
		$("#questionOptionsDIV").html(str);
		str ='<span onclick="buildOptions();" class="btn btn-small"><i class="icon-th-list"> </i> Add More Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildOptions();" value="Add Option" > </div>';
	    $("#addNewOptionsDIV").html(str);
	  }
	  
   }
   
   function buildOptions(){
      optionId = optionId+1;
	  var str = '';
	   str+='<fieldset id="optionDIV'+optionId+'" class="alert alert-info" >';
       str+='<div  style="margin-bottom:5px;"><div><input  key="'+optionId+'" class="mainoption" type="text" id="optionDIV'+optionId+'" /> <input type="checkbox" id="optshowremark'+optionId+'" /> Check to show remark  <input type="checkbox"  id="hassubDIV'+optionId+'" onclick="showSubQuestion('+optionId+',this.id);" /> Has sub options <span class="icon-trash" style="cursor:pointer;" title="Remove Option" onclick="removeOption('+optionId+');" ></span></div></div>';
	   str+='<div class="span10 offset1">';
	   str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='</div>';
	   str+='</fieldset>';
		$("#questionOptionsDIV").append(str);
   }
   
   function removeOption(id){
    if(confirm("Do you want to delete this option?")){
      $('#optionDIV'+id).remove();
	  //$('#subQuestionTypeDIV'+id).remove();
	  //$('#subQuestionOptDIV'+id).remove();
	  //$('#addNewSubQuestOptDIV'+id).remove();
	  
	}
   }
   
   function showSubQuestion(optionId,id){
     if($("#"+id).is(':checked')){
       var str ='';
	   str+='<div><label>Question</label> <input type="text" class="span12" id="subquestionTitle'+optionId+'" /><input type="checkbox" id="showsubRemark'+optionId+'" /> Check to show remark </div>';
       str+='<div>';
	   str+='  <label>Select Question Type</label> <select class="span12" id="subquestionType'+optionId+'" onchange="buildSubQuestion('+optionId+');">';
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
	    str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div><div style="margin-bottom:5px;">Sub Options : </div><div><input type="text" key="'+suboptionId+'" class="subquestcls'+optionId+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Check to show remark </div></div></fieldset>';
		$("#subQuestionOptDIV"+optionId).html(str);
		str ='<span onclick="buildSubOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add More Sub Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildSubOptions('+optionId+');" value="Add Sub Option" > </div>';
	    $("#addNewSubQuestOptDIV"+optionId).html(str);
	  }
	  
   }
   function buildSubOptions(id){
      suboptionId = suboptionId+1;
	  var str = '';
       str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div style="margin-bottom:5px;"><div><input type="text" key="'+suboptionId+'" class="subquestcls'+id+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Check to show remark  <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeSubOption('+suboptionId+');" ></span></div></div></fieldset>';
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
<div>
     <input type="button" onclick="openSurveyQuestionAddWindow();"  value="Add Question" />
	 <div style="display:none;" id="dialogWindowDiv" title="Add New Question">
	   <div class="container">
	    <div class="row">
		  <div class="container row span8 offset2 well">
		    <form class="row-fluid">
			      <label>Question</label>
				  <input type="text" id="questionTitle" placeholder="Enter Question..." name="Question" class="span12">
					<div><input type="checkbox" id="showRemark" /> Check to show remark</div>
					
				  <label>Select Question Type </label> 
				  <select class="span12" id="questionType" onchange="buildQuestion();">
					  <option value="0">Select</option>
					  <option value="1">Multiple choice with single select</option>
				   </select>
					
					<div style="margin-top:5px;" id="questionOptionsDIV"></div>
					<div style="margin-top:5px;" id="addNewOptionsDIV"></div>
					<input type="button" class="btn" value="Add Question" onclick="saveQuestion();"/>
			</form>		
		  </div>			
		</div>			
      </div>
	 </div>
	<div style="text-align:center;margin-top:10px;margin-bottom:10px;"><input type="button" value="Creating Survey" id="surveyBtn" class="btn btn-info"/></div>
	 </div>
<script type="text/javascript">
$(document).ready(function(){
				
  $("#surveyBtn").click(function(){
	 var browser1 = window.open("surveyDetailsAction.action?","surveyDetails","scrollbars=yes,height=600,width=980,left=200,top=200");	
    browser1.focus();
  });

});//End of ready
</script>
</body>
</html>